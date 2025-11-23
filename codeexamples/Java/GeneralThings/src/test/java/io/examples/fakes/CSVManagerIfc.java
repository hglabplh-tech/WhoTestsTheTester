/*
 * Copyright (c) Harald Glab-Plhak, 2025
 */

package io.examples.fakes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface CSVManagerIfc {

    Integer getSize();

    BufferedWriter getBufferedWriter();

    StringBuilder getBuilder();

    void incrementSize();

    void incrementIndex();

    void appendToCSV(List<String> typeValList);

    void addElementToCSV(String element);

    String getNextElementFromCSV();

    void flushLineToSink(boolean endOfWork) throws IOException;

    static BufferedWriter getWriterFromFile(File theFile) throws IOException {
        FileWriter fWriter = new FileWriter(theFile);
        return  new BufferedWriter(fWriter);
    }
}
