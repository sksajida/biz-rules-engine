package com.tuplescale.graph.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;

import com.tuplescale.graph.model.CmTimeseriesParameters;
import com.tuplescale.graph.model.TimeseriesData;
import com.tuplescale.graph.reader.CSVToBeanIn;
import com.tuplescale.graph.reader.CSVToBeanIn2;

@Configuration
public class TimeseriesConfiguration {

    @Autowired
    ResourceLoader resourceLoader;

    @Bean("paramIn")
    @Scope("prototype")
    public CSVToBeanIn<CmTimeseriesParameters> paramIn() {
        String filePath = getClass().getClassLoader().getResource("ts/CM_TIMESERIES_PARAMETERS_202106221924.csv").getFile();
        return new CSVToBeanIn<CmTimeseriesParameters>(new File(filePath), CmTimeseriesParameters.class);
    }

    @Bean("timeseriesDataIn")
    @Scope("prototype")
    public CSVToBeanIn<TimeseriesData> timeseriesDataIn() {
        String filePath = getClass().getClassLoader().getResource("ts/timeseries_new_data.csv").getFile();
        return new CSVToBeanIn<TimeseriesData>(new File(filePath), TimeseriesData.class);
    }
    
    
    @Bean("timeseriesDataIn1")
    @Scope("prototype")
    public CSVToBeanIn2<TimeseriesData> timeseriesDataIn1() {
        String filePath = getClass().getClassLoader().getResource("ts/timeseries_new_data.csv").getFile();
        return new CSVToBeanIn2<TimeseriesData>(new File(filePath), TimeseriesData.class);
    }
}
