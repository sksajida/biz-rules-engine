package com.tuplescale.graph.reader;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class In {

    private static final String CHARSET_NAME = "UTF-8";

    private Scanner scanner;

    public In() {
        scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public Object readNext() {
        String line;
        try {
            line = scanner.nextLine();
        }
        catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }
}
