/*
 * Copyright (c) Harald Glab-Plhak, 2025
 */

package io.examples.fakes;

import java.io.*;
import java.util.List;

public interface CSVManagerIfc {

    Integer getSize();

    IOBlock getIOBlock();

    StringBuilder getBuilder();

    void incrementSize();

    void incrementIndex();

    void writeToTarget(Format format, StoreType storeType, IOBlock writeTo) throws IOException;

    void appendToCSV(List<String> typeValList);

    void addElementToCSV(String element);

    String getNextElementFromCSV();

    void flushLineToSink(IOBlock writeTo, boolean endOfWork) throws IOException;

    static BufferedWriter getWriterFromFile(File theFile) throws IOException {
        FileWriter fWriter = new FileWriter(theFile);
        return  new BufferedWriter(fWriter);
    }

    static OutputStream getOutputStreamFromFile(File theFile) throws IOException {
        return  new BufferedOutputStream(new FileOutputStream(theFile));
    }

    static BufferedReader getReaderFromFile(File theFile) throws IOException {
        FileReader fReader = new FileReader(theFile);
        return  new BufferedReader(fReader);
    }

    static InputStream getInputStreamFromFile(File theFile) throws IOException {
        return  new BufferedInputStream(new FileInputStream(theFile));
    }

    enum Format {
        KEYVAL,
        PLAINLIST,
    }

    enum StoreType {
        XML,
        CSVPLAIN,
        BOTH
    }
    class IOBlock  {
        private final BufferedReader reader;
        private final BufferedWriter writer;
        private final OutputStream outStream;
        private final  InputStream inStream;
        public IOBlock(File inFile) throws IOException {
            this.reader = getReaderFromFile(inFile);
            this.writer = getWriterFromFile(inFile);
            this.outStream = getOutputStreamFromFile(inFile);
            this.inStream = getInputStreamFromFile(inFile);
        }

        public BufferedReader getReader() {
            return this.reader;
        }

        public BufferedWriter getWriter() {
            return this.writer;
        }

        public OutputStream getOutStream() {
            return this.outStream;
        }

        public InputStream getInStream() {
            return this.inStream;
        }
    }
}
