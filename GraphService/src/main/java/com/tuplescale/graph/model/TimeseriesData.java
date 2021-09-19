package com.tuplescale.graph.model;

import com.opencsv.bean.CsvBindByName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TimeseriesData {
	private static final Logger logger = LoggerFactory.getLogger(TimeseriesData.class);
    public int ID;
    public int RELATION_ID;
    public int TS_ID;
    public int TB_ID;
    public double VALUE;
    public String IS_USER_EDIT;
    public String PLANNING_POINT_SEQ;
    public String VERSION_ID;
//    @CsvBindByName(column = "DATA_IDENTIFIER")
    public String DATA_IDENTIFIER;
    public int TENANT_ID;
    public int AUDIT_ID;
  
	public String ATTRVALUENAME;
	public List<String> ATTRVALUES_STR;
	public List<Integer> ATTRVALUES_INT;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRELATION_ID() {
        return RELATION_ID;
    }

    public void setRELATION_ID(int RELATION_ID) {
        this.RELATION_ID = RELATION_ID;
    }

    public int getTS_ID() {
        return TS_ID;
    }

    public void setTS_ID(int TS_ID) {
        this.TS_ID = TS_ID;
    }

    public int getTB_ID() {
        return TB_ID;
    }

    public void setTB_ID(int TB_ID) {
        this.TB_ID = TB_ID;
    }

    public double getVALUE() {
        return VALUE;
    }

    public void setVALUE(double VALUE) {
        this.VALUE = VALUE;
    }

    public String getIS_USER_EDIT() {
        return IS_USER_EDIT;
    }

    public void setIS_USER_EDIT(String IS_USER_EDIT) {
        this.IS_USER_EDIT = IS_USER_EDIT;
    }

    public String getPLANNING_POINT_SEQ() {
        return PLANNING_POINT_SEQ;
    }

    public void setPLANNING_POINT_SEQ(String PLANNING_POINT_SEQ) {
        this.PLANNING_POINT_SEQ = PLANNING_POINT_SEQ;
    }

    public String getVERSION_ID() {
        return VERSION_ID;
    }

    public void setVERSION_ID(String VERSION_ID) {
        this.VERSION_ID = VERSION_ID;
    }

    public String getDATA_IDENTIFIER() {
        return DATA_IDENTIFIER;
    }

    public void setDATA_IDENTIFIER(String DATA_IDENTIFIER) {
        if (DATA_IDENTIFIER == null) this.DATA_IDENTIFIER = null;
        this.DATA_IDENTIFIER = DATA_IDENTIFIER;
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

    @Override
    public String toString() {
        return "TimeseriesData{" +
                "ID=" + ID +
                ", RELATION_ID=" + RELATION_ID +
                ", TS_ID=" + TS_ID +
                ", TB_ID=" + TB_ID +
                ", VALUE=" + VALUE +
                ", IS_USER_EDIT='" + IS_USER_EDIT + '\'' +
                ", PLANNING_POINT_SEQ='" + PLANNING_POINT_SEQ + '\'' +
                ", VERSION_ID=" + VERSION_ID +
                ", DATA_IDENTIFIER=" + DATA_IDENTIFIER +
                ", TENANT_ID=" + TENANT_ID +
                ", AUDIT_ID=" + AUDIT_ID +
                ", ATTRVALUENAME='" + ATTRVALUENAME + '\'' +
                ", ATTRVALUES_STR=" + ATTRVALUES_STR +
                ", ATTRVALUES_INT=" + ATTRVALUES_INT +
                '}';
    }
}