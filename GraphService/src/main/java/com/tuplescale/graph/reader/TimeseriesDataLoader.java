package com.tuplescale.graph.reader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tuplescale.graph.model.TimeseriesData;
import com.tuplescale.graph.model.TimeseriesRequest;
import com.tuplescale.graph.processing.AffectedTSPathFinder;

@Component
public class TimeseriesDataLoader {

	private static Logger logger = LoggerFactory.getLogger(TimeseriesDataLoader.class);

	@Autowired
	@Qualifier("timeseriesDataIn1")
	CSVToBeanIn2<TimeseriesData> in;

	@Autowired
	AffectedTSPathFinder affectedTSPathFinder;

	@Value("${kafka.topic.job.calculation}")
	private String jobCalcTopic;

	//@PostConstruct
//	public void init() {
//		int max = 400000;
//		int totalValuesThere = 0;
//		
//		in.getSteam().limit(max).forEach((td)-> 
//		 {
//			 TimeseriesRequest req = TimeseriesRequest.of(td);
//				try {
//						affectedTSPathFinder.overrideAffectedNodes(req);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//		 });
//		
//	
//		logger.info("Num affectedPaths available are: {}", totalValuesThere);
//	}
}
