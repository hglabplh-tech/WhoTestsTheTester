/*
 * Copyright (c)
 */

package io.examples.fakes;

import io.examples.fakes.CSVManagerIfc.IOBlock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static io.examples.fakes.CSVManagerIfc.Format.KEYVAL;
import static io.examples.fakes.CSVManagerIfc.Format.PLAINLIST;
import static io.examples.fakes.CSVManagerIfc.StoreType.CSVPLAIN;
import static org.junit.jupiter.api.Assertions.*;

class RealWorldCSVManagerTest {

    private RealWorldCSVManager manager;


    @BeforeEach
    void setUp() throws IOException  {
        File inFile = File.createTempFile("test", ".csv");
        this.manager = new RealWorldCSVManager(inFile);
        manager.appendToCSV(Arrays.asList("key", "value", "key_two", "val_two", "key_three", "val_three"));
        IOBlock ioBlock= manager.getIOBlock();
        manager.writeToTarget(KEYVAL, CSVPLAIN, ioBlock);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void appendToCSV() {
    }

    @Test
    void addElementToCSV() {
    }

    @Test
    void getNextElementFromCSV() {
    }

    @Test
    void incrementSize() {
    }

    @Test
    void incrementIndex() {
    }

    @Test
    void writeToTarget() {
    }

    @Test
    void flushLineToSink() {
    }
}