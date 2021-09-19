package com.tuplescale.graph.util;


import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class GraphSequenceIDver2 {
	private static Logger logger = LoggerFactory.getLogger(GraphSequenceIDver2.class);
	private static int detailMin = (int)1;
	private static int detailMax = (int)2147483647;
	private static int tsIdMin = (int)1;
	private static int tsIdMax = (int)767;
	private static int periodIdMin = (int)1;
	private static int periodIdMax = (int)32767;
	private static int inputTypeMin = (int) 1; //1 - Numeric, 2- String, 3- Date 
	private static int inputTypeMax = (int) 3;
	private static int parameterTypeMin = (int) 1; //1 - Timeseries, 2- Attribute
	private static int parameterTypeMax = (int) 2;

	public GraphSequenceIDver2() {
		// 8 bytes -> long
		runLoop(1000);
		runLoop(10000);
		runLoop(100000);
		runLoop(1000000);
		runLoop(10000000);
		runLoop(100000000);
		runLoop(1000000000);
	}

	public void runLoop(int loopsize) {
		Stopwatch stopwatch = Stopwatch.createUnstarted();
		stopwatch.reset();
		stopwatch.start();
		for (int i=1;i<=loopsize;i++) {
			int detailId = getRandomNumber(detailMin,detailMax);
			short tsId = (short) getRandomNumber(tsIdMin,tsIdMax);
			short periodId = (short) getRandomNumber(periodIdMin,periodIdMax);
			short inputType = (short) getRandomNumber(inputTypeMin, inputTypeMax);
			short parameterType = (short) getRandomNumber(parameterTypeMin, parameterTypeMax);

			long graphSeqId;

			graphSeqId = toGraphSequence(detailId, tsId, periodId, inputType, parameterType);

			//System.out.println("graphSeqId =" + graphSeqId);
			
			
			// Seq -> (detaild, tsId, periodId)
			getDetailId(graphSeqId);
			getTsId(graphSeqId);
			getPeriodId(graphSeqId);
			getInputTypeId(graphSeqId);
			getParameterTypeId(graphSeqId);
		}
		stopwatch.stop();
		long elapsedTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		double calcPerMilli = (double)elapsedTime/(double)loopsize;
		logger.debug("loop size:" + loopsize + ":" +
				Long.toString(elapsedTime) + " milliseconds " + ":calcperMilli:" + calcPerMilli);
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static String toString(long graphSeqId) {
		return getDetailId(graphSeqId) + ":" + getTsId(graphSeqId) + ":" +
			getPeriodId(graphSeqId) + ":" + getInputTypeId(graphSeqId) + ":" +
			getParameterTypeId(graphSeqId);
	}
	
	public static long toGraphSequence (int detailId, short tsId, short periodId, short dataType, short typeId) {
		
			//Use Assertions for safety
			
			tsId = (short) ((typeId * 10000) + (dataType * 1000) + tsId);
			
			byte [] longBytes = new byte [] {
					(byte)(detailId >> 24),
					(byte)(detailId >> 16),
					(byte)(detailId >> 8),
					(byte)detailId,
					(byte)(tsId >> 8),
					(byte)tsId,
					(byte)(periodId >> 8),
					(byte)periodId,
					
			};
			long result = 0;
			for (int i = 0; i < Long.BYTES; i++) {
				result <<= Long.BYTES;
				result |= (longBytes[i] & 0xFF);
			}
			return result;
	
	}
	

	public static int getDetailId (long pptSeq) {
			return (((byte)(pptSeq >> 56) & 0xFF) << 24) |
					(((byte)(pptSeq >> 48) & 0xFF) << 16) |
					(((byte)(pptSeq >> 40) & 0xFF) << 8) |
					(((byte)(pptSeq >> 32) & 0xFF) << 0);
	}
		
	public static short getTsId (long pptSeq) {
			return (short) (((((byte)(pptSeq >> 24) & 0xFF) << 8) |
					(((byte)(pptSeq >> 16) & 0xFF) << 0)) % 1000);
	}
		
	public static short getInputTypeId (long pptSeq) {
			return (short) (((((byte)(pptSeq >> 24) & 0xFF) << 8) |
					(((byte)(pptSeq >> 16) & 0xFF) << 0))/1000 % 10);
	}
		
	public static short getParameterTypeId (long pptSeq) {
			return (short) (((((byte)(pptSeq >> 24) & 0xFF) << 8) |
					(((byte)(pptSeq >> 16) & 0xFF) << 0))/10000);
	}
		
	public static short getPeriodId (long pptSeq) {
			return (short) ((((byte)(pptSeq >> 8) & 0xFF) << 8) |
					(((byte)(pptSeq >> 0) & 0xFF) << 0));
	}

	/*public static void main(String[] args) throws Exception{
        Options options = new Options();
		Option.Builder builder;
		builder = Option.builder("p").required(true).longOpt("proeprtyfile")
		.argName("propertyfile").hasArg().desc("Property File");
		options.addOption(builder.build());
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "GraphSequenceID", options );
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse( options, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String propertyFile = null;
		propertyFile = cmd.getOptionValue("p");
		File pFile = new File(propertyFile);
		if (!pFile.exists()) {
			System.err.println("Property file not found " + pFile.getPath());
			return;
		}
        GraphSequenceIDver2 epm = new GraphSequenceIDver2();
    }*/

}
