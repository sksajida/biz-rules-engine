package com.tuplescale.graph.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 */
public class CmTimeseriesParameters{
	private static final Logger logger = LoggerFactory.getLogger(CmTimeseriesParameters.class);
    public int CM_TS_PARAM_ID;
    public int CM_PARAM_ID;
    public int TS_ID;
    public int RELATIVE_PERIOD_POSITION;
    public int INPUT_ORDER;
    public String ANCHOR_TO;
    public String ANCHOR_TO_VERSION;
    public int RELATIVE_VERSION_POSITION;
    public String TIME_SERIES_NAME;
    public int AFFECTED_TS_ID;
    public String AFFECTED_TIME_SERIES_NAME;
    public String IS_DETAIL_COUNT;
    public int CONFIG_VERSION_ID;
    public int TENANT_ID;
    public int AUDIT_ID;
	public String ATTRVALUENAME;
	public List<String> ATTRVALUES_STR;
	public List<Integer> ATTRVALUES_INT;
	public String PARAMETER_TYPE;

    public int getCM_TS_PARAM_ID() {
        return CM_TS_PARAM_ID;
    }

    public void setCM_TS_PARAM_ID(int CM_TS_PARAM_ID) {
        this.CM_TS_PARAM_ID = CM_TS_PARAM_ID;
    }

    public int getCM_PARAM_ID() {
        return CM_PARAM_ID;
    }

    public void setCM_PARAM_ID(int CM_PARAM_ID) {
        this.CM_PARAM_ID = CM_PARAM_ID;
    }

    public int getTS_ID() {
        return TS_ID;
    }

    public void setTS_ID(int TS_ID) {
        this.TS_ID = TS_ID;
    }

    public int getRELATIVE_PERIOD_POSITION() {
        return RELATIVE_PERIOD_POSITION;
    }

    public void setRELATIVE_PERIOD_POSITION(int RELATIVE_PERIOD_POSITION) {
        this.RELATIVE_PERIOD_POSITION = RELATIVE_PERIOD_POSITION;
    }

    public int getINPUT_ORDER() {
        return INPUT_ORDER;
    }

    public void setINPUT_ORDER(int INPUT_ORDER) {
        this.INPUT_ORDER = INPUT_ORDER;
    }

    public String getANCHOR_TO() {
        return ANCHOR_TO;
    }

    public void setANCHOR_TO(String ANCHOR_TO) {
        this.ANCHOR_TO = ANCHOR_TO;
    }

    public String getANCHOR_TO_VERSION() {
        return ANCHOR_TO_VERSION;
    }

    public void setANCHOR_TO_VERSION(String ANCHOR_TO_VERSION) {
        this.ANCHOR_TO_VERSION = ANCHOR_TO_VERSION;
    }

    public int getRELATIVE_VERSION_POSITION() {
        return RELATIVE_VERSION_POSITION;
    }

    public void setRELATIVE_VERSION_POSITION(int RELATIVE_VERSION_POSITION) {
        this.RELATIVE_VERSION_POSITION = RELATIVE_VERSION_POSITION;
    }

    public String getTIME_SERIES_NAME() {
        return TIME_SERIES_NAME;
    }

    public void setTIME_SERIES_NAME(String TIME_SERIES_NAME) {
        this.TIME_SERIES_NAME = TIME_SERIES_NAME;
    }

    public int getAFFECTED_TS_ID() {
        return AFFECTED_TS_ID;
    }

    public void setAFFECTED_TS_ID(int AFFECTED_TS_ID) {
        this.AFFECTED_TS_ID = AFFECTED_TS_ID;
    }

    public String getAFFECTED_TIME_SERIES_NAME() {
        return AFFECTED_TIME_SERIES_NAME;
    }

    public void setAFFECTED_TIME_SERIES_NAME(String AFFECTED_TIME_SERIES_NAME) {
        this.AFFECTED_TIME_SERIES_NAME = AFFECTED_TIME_SERIES_NAME;
    }

    public String getIS_DETAIL_COUNT() {
        return IS_DETAIL_COUNT;
    }

    public void setIS_DETAIL_COUNT(String IS_DETAIL_COUNT) {
        this.IS_DETAIL_COUNT = IS_DETAIL_COUNT;
    }

    public int getCONFIG_VERSION_ID() {
        return CONFIG_VERSION_ID;
    }

    public void setCONFIG_VERSION_ID(int CONFIG_VERSION_ID) {
        this.CONFIG_VERSION_ID = CONFIG_VERSION_ID;
    }

    public int getTENANT_ID() {
        return TENANT_ID;
    }

    public void setTENANT_ID(int TENANT_ID) {
        this.TENANT_ID = TENANT_ID;
    }

    public int getAUDIT_ID() {
        return AUDIT_ID;
    }

    public void setAUDIT_ID(int AUDIT_ID) {
        this.AUDIT_ID = AUDIT_ID;
    }

    public String getATTRVALUENAME() {
        return ATTRVALUENAME;
    }

    public void setATTRVALUENAME(String ATTRVALUENAME) {
        this.ATTRVALUENAME = ATTRVALUENAME;
    }

    public List<String> getATTRVALUES_STR() {
        return ATTRVALUES_STR;
    }

    public void setATTRVALUES_STR(List<String> ATTRVALUES_STR) {
        this.ATTRVALUES_STR = ATTRVALUES_STR;
    }

    public List<Integer> getATTRVALUES_INT() {
        return ATTRVALUES_INT;
    }

    public void setATTRVALUES_INT(List<Integer> ATTRVALUES_INT) {
        this.ATTRVALUES_INT = ATTRVALUES_INT;
    }

    public String getPARAMETER_TYPE() {
        return PARAMETER_TYPE;
    }

    public void setPARAMETER_TYPE(String PARAMETER_TYPE) {
        this.PARAMETER_TYPE = PARAMETER_TYPE;
    }

    @Override
    public String toString() {
        return  CM_TS_PARAM_ID  + " - " +   CM_PARAM_ID  + " - " +   TS_ID  + " - " +   RELATIVE_PERIOD_POSITION  + " - " +   INPUT_ORDER  + " - " +   ANCHOR_TO  + " - " +   ANCHOR_TO_VERSION  + " - " +   RELATIVE_VERSION_POSITION  + " - " +   TIME_SERIES_NAME  + " - " +   AFFECTED_TS_ID  + " - " +   AFFECTED_TIME_SERIES_NAME  + " - " +   IS_DETAIL_COUNT  + " - " +   CONFIG_VERSION_ID  ;
    }



}