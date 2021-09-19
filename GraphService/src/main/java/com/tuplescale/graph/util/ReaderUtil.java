package com.tuplescale.graph.util;

import java.io.File;

public class ReaderUtil {
    public static File read(String filename) {
        String filePath = ReaderUtil.class.getClassLoader().getResource(String.format("ts/%s", filename)).getFile();
        return new File(filePath);
    }
}
