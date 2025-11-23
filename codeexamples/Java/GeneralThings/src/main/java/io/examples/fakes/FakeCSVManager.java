package io.examples.fakes;

/*
 * Copyright (c)
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FakeCSVManager implements CSVManagerIfc {

    private StringBuilder builder = new StringBuilder();

    private Integer size = 0;

    private final  IOBlock ioBlock;

    public FakeCSVManager(File inFile) {
        try {
            this.ioBlock = new IOBlock(inFile);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
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
    public void flushLineToSink(IOBlock writeTo, boolean endOfWork) throws IOException {

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
    public void writeToTarget(Format format, StoreType storeType, IOBlock writeTo) {

    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    @Override
    public IOBlock getIOBlock() {
        return null;
    }


    @Override
    public StringBuilder getBuilder() {
        return builder;
    }
}
