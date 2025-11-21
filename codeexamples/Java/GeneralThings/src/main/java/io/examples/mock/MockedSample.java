/*
 * Copyright (c) Harald Glab-Plhak, 2025
 */

package io.examples.mock;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class contains functionality which is written for show how mocking in tests works and which problems
 * we have to fight if too much or the wrong things are mocked
 */
public class MockedSample {

    private final File tempFile;

    private final List<String> content = Arrays.asList("blubber plapper blah hurz furz",
            "das letzte °Schaf hieß Harald", "und d's Abby ist 100 Jahr alt",
            "Der Manu der ist Flash",
            "Jerry hüpft rum wie Raffiki",
            "Joshi der beste Kumpel vom kommunistischen Känguru"
    );

    public MockedSample() {
        try {
            this.tempFile = File.createTempFile("mockfake", "blubb");
            this.tempFile.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getTempFile() {
        return this.tempFile;
    }


    public List<String> getContent() {
        return Collections.unmodifiableList(this.content);
    }

    public StringWriter getStringWriter() {
        return new StringWriter(80);
    }

    public StringReader getStringReader() {
        StringBuilder buffer = new StringBuilder();
        for (String part : this.content) {
            buffer.append(part).append(" ");
        }
        return new StringReader(buffer.toString());
    }


    public String[] writeContentToString() {
        StringWriter writer = this.getStringWriter();
        getContent().forEach(e -> {
            writer.write(e + " ");
        });
        String strCont = writer.getBuffer().toString();
        return strCont.split(" ");
    }

    public BufferedWriter getTempFileOutStream() throws IOException {
        return new BufferedWriter(new FileWriter(tempFile));
    }

    public BufferedReader getTempFileInStream() throws IOException {
        return new BufferedReader(new FileReader(tempFile));
    }

    public BufferedWriter getFileOutStream(File outFile) throws IOException {
        return new BufferedWriter(new FileWriter(outFile));
    }

    public BufferedReader getFileInStream(File inFile) throws IOException {
        return new BufferedReader(new FileReader(inFile));
    }

    public List<String> readTempTestTextfile() {
        return this.readTestTextFileCorrectFMock(this.getTempFile());
    }

    public boolean writeTempTestTextfile() {
        return this.writeTestTextFile(this.getTempFile(), this.getContent());
    }

    public List<String> readTestTextFile(File inFile) {
        List<String> fileCollection = new ArrayList<>();
        try {
            BufferedReader reader = this.getFileInStream(inFile);
            if (inFile != null) {
                reader = this.getFileInStream(inFile);
            } else {
                reader = this.getTempFileInStream();
            }
            String line = this.readOneLine(reader);
            while (line != null) {
                fileCollection.add(line);
                line = this.readOneLine(reader);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("error", ex);
        }
        return fileCollection;
    }


    public String readOneLine(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    public void writeOneLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }


    public List<String> readTestTextFileCorrectFMock(File inFile) {
        List<String> fileCollection = new ArrayList<>();
        try {
            BufferedReader reader;
            if (inFile != null) {
                reader = this.getFileInStream(inFile);
            } else {
                reader = this.getTempFileInStream();
            }
            String line = this.readOneLine(reader);
            while (line != null) {
                fileCollection.add(line);
                line = this.readOneLine(reader);
            }
            reader.close();
        } catch (IOException ex) {
            throw new IllegalStateException("error", ex);
        }
        return fileCollection;
    }

    public boolean writeTestTextFile(File outFile, List<String> fileCollection) {
        BufferedWriter writer = null;
        try {
            if (outFile != null) {
                writer = this.getFileOutStream(outFile);
            } else {
                writer = this.getTempFileOutStream();
            }
        } catch (IOException e) {
            return false;
        }
        BufferedWriter finalWriter = writer;
        AtomicBoolean ok = new AtomicBoolean();
        ok.set(true);
        fileCollection.forEach(line -> {
            try {
                System.out.println(line);
                this.writeOneLine(finalWriter, line);
            } catch (IOException e) {
                ok.set(false);
            }
        });
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            ok.set(false);
        }
        return ok.get();
    }
}

