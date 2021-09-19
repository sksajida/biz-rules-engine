package com.tuplescale.graph.model;

import java.util.List;

public class TimeseriesMeta  {
    public int TS_ID;
    public String EXTERNAL_TIMESERIES_NAME;
    public String EXTERNAL_TIMESERIES_DESCRIPTION;
    public String TS_TYPE;
    public String TS_CONTEXT;
    public String TS_DOMAIN;
    public String TS_ASPECT;
    public String OP_CODE;
    public String SOURCE_PLANE;
    public String IMPORT_PLANE;
    public String RECONCILIATION_TYPE;
    public String RECONCILIATION_GRANULARITY;
    public String RECONCILIATION_ANCHOR_TO;
    public String IS_FORECASTED;
    public String IS_HISTORICAL_OVERRIDE;
    public String IS_FUTURE_OVERRIDE;
    public String APPROPRIATE_LEVEL;
    public String OVERRIDABLE_LEVEL;
    public String AGGREGATION_STRATEGY;
    public String AGGREGATION_RULE;
    public String IS_EXCEL_CALCULATED;
    public String IS_METRIC;
    public String IS_REASON_CODE;
    public int TS_PRECISION;
    public String ROUNDING_STRATEGY;
    public int SOURCE_PLANE_ID;
    public int DESTINATION_PLANE_ID;
    public String HISTORICAL_OVERRIDE_HORIZON;
    public String FUTURE_OVERRIDE_HORIZON;
    public String SOURCE_PLANE_1;
    public String SOURCE_PLANE_2;
    public String SOURCE_PLANE_3;
    public String SOURCE_PLANE_4;
    public String SOURCE_PLANE_5;
    public String IMPORT_PLANE_1;
    public String IMPORT_PLANE_2;
    public String IMPORT_PLANE_3;
    public String IMPORT_PLANE_4;
    public String IMPORT_PLANE_5;
    public String APPROPRIATE_LEVEL_1;
    public String APPROPRIATE_LEVEL_2;
    public String APPROPRIATE_LEVEL_3;
    public String APPROPRIATE_LEVEL_4;
    public String APPROPRIATE_LEVEL_5;
    public String OVERRIDABLE_LEVEL_1;
    public String OVERRIDABLE_LEVEL_2;
    public String OVERRIDABLE_LEVEL_3;
    public String OVERRIDABLE_LEVEL_4;
    public String OVERRIDABLE_LEVEL_5;
    public String TIME_SERIES_NAME;
    public String TIME_SERIES_DESC;
    public int CONFIG_VERSION_ID;
    public int TENANT_ID;
    public int AUDIT_ID;

	public String ATTRVALUENAME;
	public List<String> ATTRVALUES_STR;
	public List<Integer> ATTRVALUES_INT;

    public int getTS_ID() {
        return TS_ID;
    }

    public void setTS_ID(int TS_ID) {
        this.TS_ID = TS_ID;
    }

    public String getEXTERNAL_TIMESERIES_NAME() {
        return EXTERNAL_TIMESERIES_NAME;
    }

    public void setEXTERNAL_TIMESERIES_NAME(String EXTERNAL_TIMESERIES_NAME) {
        this.EXTERNAL_TIMESERIES_NAME = EXTERNAL_TIMESERIES_NAME;
    }

    public String getEXTERNAL_TIMESERIES_DESCRIPTION() {
        return EXTERNAL_TIMESERIES_DESCRIPTION;
    }

    public void setEXTERNAL_TIMESERIES_DESCRIPTION(String EXTERNAL_TIMESERIES_DESCRIPTION) {
        this.EXTERNAL_TIMESERIES_DESCRIPTION = EXTERNAL_TIMESERIES_DESCRIPTION;
    }

    public String getTS_TYPE() {
        return TS_TYPE;
    }

    public void setTS_TYPE(String TS_TYPE) {
        this.TS_TYPE = TS_TYPE;
    }

    public String getTS_CONTEXT() {
        return TS_CONTEXT;
    }

    public void setTS_CONTEXT(String TS_CONTEXT) {
        this.TS_CONTEXT = TS_CONTEXT;
    }

    public String getTS_DOMAIN() {
        return TS_DOMAIN;
    }

    public void setTS_DOMAIN(String TS_DOMAIN) {
        this.TS_DOMAIN = TS_DOMAIN;
    }

    public String getTS_ASPECT() {
        return TS_ASPECT;
    }

    public void setTS_ASPECT(String TS_ASPECT) {
        this.TS_ASPECT = TS_ASPECT;
    }

    public String getOP_CODE() {
        return OP_CODE;
    }

    public void setOP_CODE(String OP_CODE) {
        this.OP_CODE = OP_CODE;
    }

    public String getSOURCE_PLANE() {
        return SOURCE_PLANE;
    }

    public void setSOURCE_PLANE(String SOURCE_PLANE) {
        this.SOURCE_PLANE = SOURCE_PLANE;
    }

    public String getIMPORT_PLANE() {
        return IMPORT_PLANE;
    }

    public void setIMPORT_PLANE(String IMPORT_PLANE) {
        this.IMPORT_PLANE = IMPORT_PLANE;
    }

    public String getRECONCILIATION_TYPE() {
        return RECONCILIATION_TYPE;
    }

    public void setRECONCILIATION_TYPE(String RECONCILIATION_TYPE) {
        this.RECONCILIATION_TYPE = RECONCILIATION_TYPE;
    }

    public String getRECONCILIATION_GRANULARITY() {
        return RECONCILIATION_GRANULARITY;
    }

    public void setRECONCILIATION_GRANULARITY(String RECONCILIATION_GRANULARITY) {
        this.RECONCILIATION_GRANULARITY = RECONCILIATION_GRANULARITY;
    }

    public String getRECONCILIATION_ANCHOR_TO() {
        return RECONCILIATION_ANCHOR_TO;
    }

    public void setRECONCILIATION_ANCHOR_TO(String RECONCILIATION_ANCHOR_TO) {
        this.RECONCILIATION_ANCHOR_TO = RECONCILIATION_ANCHOR_TO;
    }

    public String getIS_FORECASTED() {
        return IS_FORECASTED;
    }

    public void setIS_FORECASTED(String IS_FORECASTED) {
        this.IS_FORECASTED = IS_FORECASTED;
    }

    public String getIS_HISTORICAL_OVERRIDE() {
        return IS_HISTORICAL_OVERRIDE;
    }

    public void setIS_HISTORICAL_OVERRIDE(String IS_HISTORICAL_OVERRIDE) {
        this.IS_HISTORICAL_OVERRIDE = IS_HISTORICAL_OVERRIDE;
    }

    public String getIS_FUTURE_OVERRIDE() {
        return IS_FUTURE_OVERRIDE;
    }

    public void setIS_FUTURE_OVERRIDE(String IS_FUTURE_OVERRIDE) {
        this.IS_FUTURE_OVERRIDE = IS_FUTURE_OVERRIDE;
    }

    public String getAPPROPRIATE_LEVEL() {
        return APPROPRIATE_LEVEL;
    }

    public void setAPPROPRIATE_LEVEL(String APPROPRIATE_LEVEL) {
        this.APPROPRIATE_LEVEL = APPROPRIATE_LEVEL;
    }

    public String getOVERRIDABLE_LEVEL() {
        return OVERRIDABLE_LEVEL;
    }

    public void setOVERRIDABLE_LEVEL(String OVERRIDABLE_LEVEL) {
        this.OVERRIDABLE_LEVEL = OVERRIDABLE_LEVEL;
    }

    public String getAGGREGATION_STRATEGY() {
        return AGGREGATION_STRATEGY;
    }

    public void setAGGREGATION_STRATEGY(String AGGREGATION_STRATEGY) {
        this.AGGREGATION_STRATEGY = AGGREGATION_STRATEGY;
    }

    public String getAGGREGATION_RULE() {
        return AGGREGATION_RULE;
    }

    public void setAGGREGATION_RULE(String AGGREGATION_RULE) {
        this.AGGREGATION_RULE = AGGREGATION_RULE;
    }

    public String getIS_EXCEL_CALCULATED() {
        return IS_EXCEL_CALCULATED;
    }

    public void setIS_EXCEL_CALCULATED(String IS_EXCEL_CALCULATED) {
        this.IS_EXCEL_CALCULATED = IS_EXCEL_CALCULATED;
    }

    public String getIS_METRIC() {
        return IS_METRIC;
    }

    public void setIS_METRIC(String IS_METRIC) {
        this.IS_METRIC = IS_METRIC;
    }

    public String getIS_REASON_CODE() {
        return IS_REASON_CODE;
    }

    public void setIS_REASON_CODE(String IS_REASON_CODE) {
        this.IS_REASON_CODE = IS_REASON_CODE;
    }

    public int getTS_PRECISION() {
        return TS_PRECISION;
    }

    public void setTS_PRECISION(int TS_PRECISION) {
        this.TS_PRECISION = TS_PRECISION;
    }

    public String getROUNDING_STRATEGY() {
        return ROUNDING_STRATEGY;
    }

    public void setROUNDING_STRATEGY(String ROUNDING_STRATEGY) {
        this.ROUNDING_STRATEGY = ROUNDING_STRATEGY;
    }

    public int getSOURCE_PLANE_ID() {
        return SOURCE_PLANE_ID;
    }

    public void setSOURCE_PLANE_ID(int SOURCE_PLANE_ID) {
        this.SOURCE_PLANE_ID = SOURCE_PLANE_ID;
    }

    public int getDESTINATION_PLANE_ID() {
        return DESTINATION_PLANE_ID;
    }

    public void setDESTINATION_PLANE_ID(int DESTINATION_PLANE_ID) {
        this.DESTINATION_PLANE_ID = DESTINATION_PLANE_ID;
    }

    public String getHISTORICAL_OVERRIDE_HORIZON() {
        return HISTORICAL_OVERRIDE_HORIZON;
    }

    public void setHISTORICAL_OVERRIDE_HORIZON(String HISTORICAL_OVERRIDE_HORIZON) {
        this.HISTORICAL_OVERRIDE_HORIZON = HISTORICAL_OVERRIDE_HORIZON;
    }

    public String getFUTURE_OVERRIDE_HORIZON() {
        return FUTURE_OVERRIDE_HORIZON;
    }

    public void setFUTURE_OVERRIDE_HORIZON(String FUTURE_OVERRIDE_HORIZON) {
        this.FUTURE_OVERRIDE_HORIZON = FUTURE_OVERRIDE_HORIZON;
    }

    public String getSOURCE_PLANE_1() {
        return SOURCE_PLANE_1;
    }

    public void setSOURCE_PLANE_1(String SOURCE_PLANE_1) {
        this.SOURCE_PLANE_1 = SOURCE_PLANE_1;
    }

    public String getSOURCE_PLANE_2() {
        return SOURCE_PLANE_2;
    }

    public void setSOURCE_PLANE_2(String SOURCE_PLANE_2) {
        this.SOURCE_PLANE_2 = SOURCE_PLANE_2;
    }

    public String getSOURCE_PLANE_3() {
        return SOURCE_PLANE_3;
    }

    public void setSOURCE_PLANE_3(String SOURCE_PLANE_3) {
        this.SOURCE_PLANE_3 = SOURCE_PLANE_3;
    }

    public String getSOURCE_PLANE_4() {
        return SOURCE_PLANE_4;
    }

    public void setSOURCE_PLANE_4(String SOURCE_PLANE_4) {
        this.SOURCE_PLANE_4 = SOURCE_PLANE_4;
    }

    public String getSOURCE_PLANE_5() {
        return SOURCE_PLANE_5;
    }

    public void setSOURCE_PLANE_5(String SOURCE_PLANE_5) {
        this.SOURCE_PLANE_5 = SOURCE_PLANE_5;
    }

    public String getIMPORT_PLANE_1() {
        return IMPORT_PLANE_1;
    }

    public void setIMPORT_PLANE_1(String IMPORT_PLANE_1) {
        this.IMPORT_PLANE_1 = IMPORT_PLANE_1;
    }

    public String getIMPORT_PLANE_2() {
        return IMPORT_PLANE_2;
    }

    public void setIMPORT_PLANE_2(String IMPORT_PLANE_2) {
        this.IMPORT_PLANE_2 = IMPORT_PLANE_2;
    }

    public String getIMPORT_PLANE_3() {
        return IMPORT_PLANE_3;
    }

    public void setIMPORT_PLANE_3(String IMPORT_PLANE_3) {
        this.IMPORT_PLANE_3 = IMPORT_PLANE_3;
    }

    public String getIMPORT_PLANE_4() {
        return IMPORT_PLANE_4;
    }

    public void setIMPORT_PLANE_4(String IMPORT_PLANE_4) {
        this.IMPORT_PLANE_4 = IMPORT_PLANE_4;
    }

    public String getIMPORT_PLANE_5() {
        return IMPORT_PLANE_5;
    }

    public void setIMPORT_PLANE_5(String IMPORT_PLANE_5) {
        this.IMPORT_PLANE_5 = IMPORT_PLANE_5;
    }

    public String getAPPROPRIATE_LEVEL_1() {
        return APPROPRIATE_LEVEL_1;
    }

    public void setAPPROPRIATE_LEVEL_1(String APPROPRIATE_LEVEL_1) {
        this.APPROPRIATE_LEVEL_1 = APPROPRIATE_LEVEL_1;
    }

    public String getAPPROPRIATE_LEVEL_2() {
        return APPROPRIATE_LEVEL_2;
    }

    public void setAPPROPRIATE_LEVEL_2(String APPROPRIATE_LEVEL_2) {
        this.APPROPRIATE_LEVEL_2 = APPROPRIATE_LEVEL_2;
    }

    public String getAPPROPRIATE_LEVEL_3() {
        return APPROPRIATE_LEVEL_3;
    }

    public void setAPPROPRIATE_LEVEL_3(String APPROPRIATE_LEVEL_3) {
        this.APPROPRIATE_LEVEL_3 = APPROPRIATE_LEVEL_3;
    }

    public String getAPPROPRIATE_LEVEL_4() {
        return APPROPRIATE_LEVEL_4;
    }

    public void setAPPROPRIATE_LEVEL_4(String APPROPRIATE_LEVEL_4) {
        this.APPROPRIATE_LEVEL_4 = APPROPRIATE_LEVEL_4;
    }

    public String getAPPROPRIATE_LEVEL_5() {
        return APPROPRIATE_LEVEL_5;
    }

    public void setAPPROPRIATE_LEVEL_5(String APPROPRIATE_LEVEL_5) {
        this.APPROPRIATE_LEVEL_5 = APPROPRIATE_LEVEL_5;
    }

    public String getOVERRIDABLE_LEVEL_1() {
        return OVERRIDABLE_LEVEL_1;
    }

    public void setOVERRIDABLE_LEVEL_1(String OVERRIDABLE_LEVEL_1) {
        this.OVERRIDABLE_LEVEL_1 = OVERRIDABLE_LEVEL_1;
    }

    public String getOVERRIDABLE_LEVEL_2() {
        return OVERRIDABLE_LEVEL_2;
    }

    public void setOVERRIDABLE_LEVEL_2(String OVERRIDABLE_LEVEL_2) {
        this.OVERRIDABLE_LEVEL_2 = OVERRIDABLE_LEVEL_2;
    }

    public String getOVERRIDABLE_LEVEL_3() {
        return OVERRIDABLE_LEVEL_3;
    }

    public void setOVERRIDABLE_LEVEL_3(String OVERRIDABLE_LEVEL_3) {
        this.OVERRIDABLE_LEVEL_3 = OVERRIDABLE_LEVEL_3;
    }

    public String getOVERRIDABLE_LEVEL_4() {
        return OVERRIDABLE_LEVEL_4;
    }

    public void setOVERRIDABLE_LEVEL_4(String OVERRIDABLE_LEVEL_4) {
        this.OVERRIDABLE_LEVEL_4 = OVERRIDABLE_LEVEL_4;
    }

    public String getOVERRIDABLE_LEVEL_5() {
        return OVERRIDABLE_LEVEL_5;
    }

    public void setOVERRIDABLE_LEVEL_5(String OVERRIDABLE_LEVEL_5) {
        this.OVERRIDABLE_LEVEL_5 = OVERRIDABLE_LEVEL_5;
    }

    public String getTIME_SERIES_NAME() {
        return TIME_SERIES_NAME;
    }

    public void setTIME_SERIES_NAME(String TIME_SERIES_NAME) {
        this.TIME_SERIES_NAME = TIME_SERIES_NAME;
    }

    public String getTIME_SERIES_DESC() {
        return TIME_SERIES_DESC;
    }

    public void setTIME_SERIES_DESC(String TIME_SERIES_DESC) {
        this.TIME_SERIES_DESC = TIME_SERIES_DESC;
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