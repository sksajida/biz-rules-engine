package com.tuplescale.graph.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tuplescale.graph.model.AffectedTSPath;
import com.tuplescale.graph.model.PlanningVertex;
import com.tuplescale.graph.model.Response;
import com.tuplescale.graph.model.TimeseriesData;
import com.tuplescale.graph.model.TimeseriesRequest;
import com.tuplescale.graph.model.TimeseriesUpdateReq;
import com.tuplescale.graph.model.TimeseriesUpdateResponse;
import com.tuplescale.graph.processing.AffectedTSPathFinder;
import com.tuplescale.graph.reader.CSVToBeanIn2;
import com.tuplescale.graph.service.TimeSeriesService;

@RestController
@RequestMapping("/timeseries")
public class TimeSeriesController {

    private static final Logger logger = LoggerFactory.getLogger(TimeSeriesController.class);

    @Autowired
    TimeSeriesService timeSeriesService;

  //  @Autowired
    //OverrideService overrideService;

    @Autowired
    AffectedTSPathFinder affectedTSPathFinder;
    @Autowired
	@Qualifier("timeseriesDataIn1")
	private CSVToBeanIn2<TimeseriesData> in;


    @GetMapping("/affectedTs")
    public @ResponseBody
    Response<?> getAffectedTs(@RequestBody TimeseriesRequest timeseriesRequest) {
        try {
            AffectedTSPath affectedTSPath = affectedTSPathFinder.affectedTSPath(timeseriesRequest);
            return new Response<>(HttpStatus.OK, affectedTSPath);
        } catch (Exception e) {
            logger.error("Failed to get affected TS", e);
            return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage());
        }
    }
    //For multiple node override
    @GetMapping("/override/{max}")
   	public @ResponseBody Response<?> override(@PathVariable int max) {
   		try {
   			int totalValuesThere = 0;
   			in.getSteam().limit(max).forEach((td) -> {
   				TimeseriesRequest req = TimeseriesRequest.of(td);
   				try {
   					affectedTSPathFinder.overrideAffectedNodes(req);
   				} catch (Exception e) {
   					e.printStackTrace();
   				}
   			});
   			logger.info("Num affectedPaths available are: {}", totalValuesThere);
   			return new Response<>(HttpStatus.OK, "");
   		} catch (Exception e) {
   			logger.error("Failed to override and post job", e);
   			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage());
   		}
   	}

//    @PostMapping("/override")
//    public @ResponseBody
//    Response<?> override(@RequestBody TimeseriesRequest timeseriesRequest) {
//        try {
//        	
//            JobPostVo jobPostVo = overrideService.override(timeseriesRequest);
//            
//            return new Response<>(HttpStatus.OK, jobPostVo);
//        } catch (Exception e) {
//            logger.error("Failed to override and post job", e);
//            return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage());
//        }
//    }

    @PutMapping("/update")
    public @ResponseBody
    Response<TimeseriesUpdateResponse> update(@RequestBody List<TimeseriesUpdateReq> timeseriesUpdateReqs) {
    	logger.info("update request:"+timeseriesUpdateReqs);
        TimeseriesUpdateResponse response = timeSeriesService.update(timeseriesUpdateReqs);
        return new Response<>(HttpStatus.OK, response);
    }

    @GetMapping("/{vertexId}")
    public @ResponseBody
    Response<PlanningVertex> findTs(@PathVariable long vertexId) {
        PlanningVertex pv = timeSeriesService.findTs(vertexId);
        if (Objects.nonNull(pv)) {
            return new Response<>(HttpStatus.OK, pv);
        } else {
            return new Response<>(HttpStatus.NOT_FOUND, null, String.format("Given timeseries %d is not found", vertexId));
        }
    }
}
