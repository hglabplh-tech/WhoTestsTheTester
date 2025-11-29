package io.examples.fakes;

/*
 * Copyright (c)
 */

import jakarta.activation.UnsupportedDataTypeException;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class RealWorldCSVManager implements CSVManagerIfc {

    private final StringBuilder builder = new StringBuilder();

    private Integer size = 0;

    private final  IOBlock ioBlock;

    public RealWorldCSVManager(File inFile) {
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
    public void incrementSize() {
        this.size++;
    }

    @Override
    public void incrementIndex() {
        throw new UnsupportedOperationException("the index incremental is not supported in OutputStream");
    }

    @Override
    public void writeToTarget(Format formatType, StoreType storeType, IOBlock writeTo)
            throws IOException{
        switch (formatType) {
            case KEYVAL -> {
                String [] splitted = getBuilder().toString().split(",");
                Map<String, String> storeMap =  new HashMap<>();
                if ((splitted.length % 2) == 0) {
                    for (int index = 0; splitted.length > index; index+=2 ) {
                        String key = splitted[index];
                        String value   = splitted[index + 1];
                        storeMap.put(key, value);
                    }
                    storeAsKeyVal(storeType, ioBlock, storeMap);
                } else {
                    throw new IllegalStateException(
                            format("No pair building possible with %d values count",
                                    splitted.length));
                }
            }
            case PLAINLIST -> {

            }
            default -> throw new UnsupportedDataTypeException();
        }


    }

    private void storeAsKeyVal (StoreType storeType, IOBlock writeTo,Map<String, String> data ) throws IOException {
        switch (storeType) {
            case XML -> {}
            case CSVPLAIN -> flushKVLinesToSink(writeTo, data, false);
            default ->  throw new UnsupportedDataTypeException();

        }
    }

    private void flushKVLinesToSink(IOBlock writeTo, Map<String, String> data, boolean endOfWork) {
        data.keySet().forEach(key -> {
            try {
                writeTo.getWriter().write(new StringBuilder()
                        .append(key).append(",")
                        .append(data.get(key))
                        .toString());
                writeTo.getWriter().newLine();
                writeTo.getWriter().flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        if (endOfWork) {
            try {
                writeTo.getWriter().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    @Override
    public void flushLineToSink(IOBlock writeTo, boolean endOfWork) throws IOException
    {
        writeTo.getWriter().write(getBuilder().toString());
        writeTo.getWriter().newLine();
        writeTo.getWriter().flush();
        if (endOfWork) {
            writeTo.getWriter().close();
        }
    }


    @Override
    public StringBuilder getBuilder() {
        return builder;
    }

    @Override
    public IOBlock getIOBlock() {
        return this.ioBlock;
    }
}
