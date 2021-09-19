package com.tuplescale.graph.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuplescale.graph.controller.TimeSeriesController;
import com.tuplescale.graph.model.AffectedTSPath;
import com.tuplescale.graph.model.JobPostVo;
import com.tuplescale.graph.model.TimeseriesRequest;
import com.tuplescale.graph.model.TimeseriesUpdateReq;
import com.tuplescale.graph.processing.AffectedTSPathFinder;
import com.tuplescale.graph.util.GraphSequenceIDver2;
import com.tuplescale.graph.util.RestTemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OverrideService {

    private static final Logger logger = LoggerFactory.getLogger(TimeSeriesController.class);

    @Autowired
    AffectedTSPathFinder affectedTSPathFinder;

    @Autowired
    TimeSeriesService timeSeriesService;

    @Autowired
    RestTemplateUtil restTemplateUtil;

    @Value("${job.post.url}")
    String POST_JOB_URL;

	/*
	 * public JobPostVo override(TimeseriesRequest request) throws Exception { long
	 * graphSeqId = GraphSequenceIDver2.toGraphSequence(request.getRelationId(),
	 * (short) request.getTsId(), (short) request.getTbId(),
	 * TimeseriesRequest.dataType, TimeseriesRequest.parameterType);
	 * TimeseriesUpdateReq updateReq = new TimeseriesUpdateReq(graphSeqId,
	 * request.getValue()); timeSeriesService.update(Arrays.asList(updateReq));
	 * AffectedTSPath affectedTSPath =
	 * affectedTSPathFinder.overrideAffectedNodes(request);
	 * logger.info("Affected calc node: {} for request: {}",new
	 * ObjectMapper().writeValueAsString(affectedTSPath.getTsCalcNodes()), request);
	 * ResponseEntity<JobPostVo> respEntity =
	 * restTemplateUtil.postRequest(POST_JOB_URL, null,
	 * affectedTSPath.getTsCalcNodes(), JobPostVo.class); if
	 * (respEntity.getStatusCode().is2xxSuccessful()) { return respEntity.getBody();
	 * } else { throw new RuntimeException(respEntity.getBody().getError()); } }
	 */

}
