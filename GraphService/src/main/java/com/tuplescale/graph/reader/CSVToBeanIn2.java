package com.tuplescale.graph.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import org.springframework.context.annotation.Scope;

import com.opencsv.bean.CsvToBeanBuilder;

public class CSVToBeanIn2<T> extends In {

    Stream<T> stream;
	private File file;
	private Class<T> clazz; 
    

    public CSVToBeanIn2(File file, Class<T> clazz) {
    	this.file = file;
    	this.clazz = clazz;
       
    }
    
    
    public Stream<T> getSteam(){
    	 if (file == null) throw new IllegalArgumentException("file argument is null");
         try {
             stream = new CsvToBeanBuilder<T>(new InputStreamReader(new FileInputStream(file)))
                     .withType(clazz)
                     .withSeparator(',')
                     .withIgnoreLeadingWhiteSpace(true)
//                     .withThrowExceptions(false)
                     .build()
                     .stream().parallel();
         }
         catch (IOException ioe) {
             throw new IllegalArgumentException("Could not open " + file, ioe);
         }
         return stream;
    }
   
}
