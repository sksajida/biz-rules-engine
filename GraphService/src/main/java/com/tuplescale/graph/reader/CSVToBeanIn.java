package com.tuplescale.graph.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.opencsv.bean.CsvToBeanBuilder;

public class CSVToBeanIn<T> extends In {

    Iterator<T> itr;

    public CSVToBeanIn(File file, Class<T> clazz) {
        if (file == null) throw new IllegalArgumentException("file argument is null");
        try {
            itr = new CsvToBeanBuilder<T>(new InputStreamReader(new FileInputStream(file)))
                    .withType(clazz)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
//                    .withThrowExceptions(false)
                    .build()
                    .stream().iterator();
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file, ioe);
        }
    }

    public boolean hasNext() {
        return itr.hasNext();
    }

    public T readNext() {
        T row;
        try {
            row = itr.next();
        }
        catch (NoSuchElementException e) {
            row = null;
        }
        return row;
    }

    public List<T> readAll() {
        List<T> l = new ArrayList<>();
        while (itr.hasNext()) l.add(itr.next());
        return l;
    }
}
