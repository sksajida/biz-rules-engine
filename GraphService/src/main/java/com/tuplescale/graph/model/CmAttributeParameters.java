package com.tuplescale.graph.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CmAttributeParameters {
	private static final Logger logger = LoggerFactory.getLogger(CmAttributeParameters.class);
    public int CM_PARAM_ATTRIB_ID;
    public int CM_PARAM_ID;
    public String ATTRIBUTE_NAME;
    public String IS_DETAIL;
    public String JOIN_COLUMN_NAME;
    public String TABLE_NAME;
    public int AFFECTED_TS_ID;
    public String TIME_SERIES_NAME;
    public String IS_CHANGE_IGNORABLE;
    public int CONFIG_VERSION_ID;
    public int TENANT_ID;
    public int AUDIT_ID;
  
	public String ATTRVALUENAME;
	public List<String> ATTRVALUES_STR;
	public List<Integer> ATTRVALUES_INT;

    public int getCM_PARAM_ATTRIB_ID() {
        return CM_PARAM_ATTRIB_ID;
    }

    public void setCM_PARAM_ATTRIB_ID(int CM_PARAM_ATTRIB_ID) {
        this.CM_PARAM_ATTRIB_ID = CM_PARAM_ATTRIB_ID;
    }

    public int getCM_PARAM_ID() {
        return CM_PARAM_ID;
    }

    public void setCM_PARAM_ID(int CM_PARAM_ID) {
        this.CM_PARAM_ID = CM_PARAM_ID;
    }

    public String getATTRIBUTE_NAME() {
        return ATTRIBUTE_NAME;
    }

    public void setATTRIBUTE_NAME(String ATTRIBUTE_NAME) {
        this.ATTRIBUTE_NAME = ATTRIBUTE_NAME;
    }

    public String getIS_DETAIL() {
        return IS_DETAIL;
    }

    public void setIS_DETAIL(String IS_DETAIL) {
        this.IS_DETAIL = IS_DETAIL;
    }

    public String getJOIN_COLUMN_NAME() {
        return JOIN_COLUMN_NAME;
    }

    public void setJOIN_COLUMN_NAME(String JOIN_COLUMN_NAME) {
        this.JOIN_COLUMN_NAME = JOIN_COLUMN_NAME;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public int getAFFECTED_TS_ID() {
        return AFFECTED_TS_ID;
    }

    public void setAFFECTED_TS_ID(int AFFECTED_TS_ID) {
        this.AFFECTED_TS_ID = AFFECTED_TS_ID;
    }

    public String getTIME_SERIES_NAME() {
        return TIME_SERIES_NAME;
    }

    public void setTIME_SERIES_NAME(String TIME_SERIES_NAME) {
        this.TIME_SERIES_NAME = TIME_SERIES_NAME;
    }

    public String getIS_CHANGE_IGNORABLE() {
        return IS_CHANGE_IGNORABLE;
    }

    public void setIS_CHANGE_IGNORABLE(String IS_CHANGE_IGNORABLE) {
        this.IS_CHANGE_IGNORABLE = IS_CHANGE_IGNORABLE;
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
}