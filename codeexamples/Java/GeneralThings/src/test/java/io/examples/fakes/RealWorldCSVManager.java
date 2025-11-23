package io.examples.fakes;

/*
 * Copyright (c)
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RealWorldCSVManager implements CSVManagerIfc {

    private StringBuilder builder = new StringBuilder();

    private Integer size = 0;

    private final BufferedWriter bufferedWriter;

    public RealWorldCSVManager(BufferedWriter writer) {
        this.bufferedWriter = writer;
    }

    @Override
    public void appendToCSV(List<String> typeValList) {
        typeValList.forEach(e -> {
            if (!getBuilder().isEmpty()) {
                getBuilder().append(",");
            }
            getBuilder().append(e);
            incrementSize();
        });
    }

    @Override
    public void addElementToCSV(String element) {
        List<String> temp = new ArrayList<>();
        temp.add(element);
        appendToCSV(temp);
    }

    @Override
    public String getNextElementFromCSV() {
        throw new UnsupportedOperationException("the element retrieval is not implemented in OutputStream");
    }

    @Override
    public void incrementSize() {
        this.size++;
    }

    @Override
    public void incrementIndex() {
        throw new UnsupportedOperationException("the index incremental is not supported in OutputStream");
    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    @Override
    public void flushLineToSink(boolean endOfWork) throws IOException
    {
        getBufferedWriter().write(getBuilder().toString());
        getBufferedWriter().newLine();
        getBufferedWriter().flush();
        if (endOfWork) {
            getBufferedWriter().close();
        }
    }


    @Override
    public StringBuilder getBuilder() {
        return builder;
    }

    @Override
    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
}
