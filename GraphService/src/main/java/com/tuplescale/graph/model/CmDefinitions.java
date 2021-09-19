package com.tuplescale.graph.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CmDefinitions {
	private static final Logger logger = LoggerFactory.getLogger(CmDefinitions.class);
    public int ID;
    public int CM_ID;
    public String TIME_SERIES_NAME;
    public int TS_ID;
    public int RELATIVE_ORDER;
    public String CM_ASSOCIATION;
    public String CM_CALCULATION_HORIZON;
    public String IS_OVERRIDE;
    public String EXPRESSION;
    public String EXPRESSION_NAME;
    public int RELATIVE_PERIOD_POSITION;
    public String CM_CALCULATION_PLANE;
    public String RECURSES_ON;
    public String MATH_EXPRESSION_TYPE;
    public String IS_DEPENDENT_ON_ETL;
    public String IS_NDCOUNT_PRESENT;
    public String IS_IPERIOD_PRESENT;
    public int CALENDAR_SUM_USED;
    public int PLANE_ID;
    public String TRIGGERING_EVENT;
    public int CONFIG_VERSION_ID;
    public int TENANT_ID;
    public int AUDIT_ID;
  
	public String ATTRVALUENAME;
	public List<String> ATTRVALUES_STR;
	public List<Integer> ATTRVALUES_INT;

    public static Logger getLogger() {
        return logger;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCM_ID() {
        return CM_ID;
    }

    public void setCM_ID(int CM_ID) {
        this.CM_ID = CM_ID;
    }

    public String getTIME_SERIES_NAME() {
        return TIME_SERIES_NAME;
    }

    public void setTIME_SERIES_NAME(String TIME_SERIES_NAME) {
        this.TIME_SERIES_NAME = TIME_SERIES_NAME;
    }

    public int getTS_ID() {
        return TS_ID;
    }

    public void setTS_ID(int TS_ID) {
        this.TS_ID = TS_ID;
    }

    public int getRELATIVE_ORDER() {
        return RELATIVE_ORDER;
    }

    public void setRELATIVE_ORDER(int RELATIVE_ORDER) {
        this.RELATIVE_ORDER = RELATIVE_ORDER;
    }

    public String getCM_ASSOCIATION() {
        return CM_ASSOCIATION;
    }

    public void setCM_ASSOCIATION(String CM_ASSOCIATION) {
        this.CM_ASSOCIATION = CM_ASSOCIATION;
    }

    public String getCM_CALCULATION_HORIZON() {
        return CM_CALCULATION_HORIZON;
    }

    public void setCM_CALCULATION_HORIZON(String CM_CALCULATION_HORIZON) {
        this.CM_CALCULATION_HORIZON = CM_CALCULATION_HORIZON;
    }

    public String getIS_OVERRIDE() {
        return IS_OVERRIDE;
    }

    public void setIS_OVERRIDE(String IS_OVERRIDE) {
        this.IS_OVERRIDE = IS_OVERRIDE;
    }

    public String getEXPRESSION() {
        return EXPRESSION;
    }

    public void setEXPRESSION(String EXPRESSION) {
        this.EXPRESSION = EXPRESSION;
    }

    public String getEXPRESSION_NAME() {
        return EXPRESSION_NAME;
    }

    public void setEXPRESSION_NAME(String EXPRESSION_NAME) {
        this.EXPRESSION_NAME = EXPRESSION_NAME;
    }

    public int getRELATIVE_PERIOD_POSITION() {
        return RELATIVE_PERIOD_POSITION;
    }

    public void setRELATIVE_PERIOD_POSITION(int RELATIVE_PERIOD_POSITION) {
        this.RELATIVE_PERIOD_POSITION = RELATIVE_PERIOD_POSITION;
    }

    public String getCM_CALCULATION_PLANE() {
        return CM_CALCULATION_PLANE;
    }

    public void setCM_CALCULATION_PLANE(String CM_CALCULATION_PLANE) {
        this.CM_CALCULATION_PLANE = CM_CALCULATION_PLANE;
    }

    public String getRECURSES_ON() {
        return RECURSES_ON;
    }

    public void setRECURSES_ON(String RECURSES_ON) {
        this.RECURSES_ON = RECURSES_ON;
    }

    public String getMATH_EXPRESSION_TYPE() {
        return MATH_EXPRESSION_TYPE;
    }

    public void setMATH_EXPRESSION_TYPE(String MATH_EXPRESSION_TYPE) {
        this.MATH_EXPRESSION_TYPE = MATH_EXPRESSION_TYPE;
    }

    public String getIS_DEPENDENT_ON_ETL() {
        return IS_DEPENDENT_ON_ETL;
    }

    public void setIS_DEPENDENT_ON_ETL(String IS_DEPENDENT_ON_ETL) {
        this.IS_DEPENDENT_ON_ETL = IS_DEPENDENT_ON_ETL;
    }

    public String getIS_NDCOUNT_PRESENT() {
        return IS_NDCOUNT_PRESENT;
    }

    public void setIS_NDCOUNT_PRESENT(String IS_NDCOUNT_PRESENT) {
        this.IS_NDCOUNT_PRESENT = IS_NDCOUNT_PRESENT;
    }

    public String getIS_IPERIOD_PRESENT() {
        return IS_IPERIOD_PRESENT;
    }

    public void setIS_IPERIOD_PRESENT(String IS_IPERIOD_PRESENT) {
        this.IS_IPERIOD_PRESENT = IS_IPERIOD_PRESENT;
    }

    public int getCALENDAR_SUM_USED() {
        return CALENDAR_SUM_USED;
    }

    public void setCALENDAR_SUM_USED(int CALENDAR_SUM_USED) {
        this.CALENDAR_SUM_USED = CALENDAR_SUM_USED;
    }

    public int getPLANE_ID() {
        return PLANE_ID;
    }

    public void setPLANE_ID(int PLANE_ID) {
        this.PLANE_ID = PLANE_ID;
    }

    public String getTRIGGERING_EVENT() {
        return TRIGGERING_EVENT;
    }

    public void setTRIGGERING_EVENT(String TRIGGERING_EVENT) {
        this.TRIGGERING_EVENT = TRIGGERING_EVENT;
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