
/*
 * Copyright (c) Harald Glab-Plhak 2025
 */

package io.examples.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for show how mocking works and what things we have to think about
 */
class MockedSampleTest {


    private MockedSample mockedVersion;
    private File tempFile;

    private final List<String> content = Arrays.asList("blubber plapper blah hurz furz",
            "das letzte °Schaf hieß Harald", "und d's Abby ist 100 Jahr alt",
            "Der Manu der ist Flash",
            "Jerry hüpft rum wie Raffiki",
            "Joshi der beste Kumpel vom kommunistischen Känguru"
    );


    /**
     * Here the mock is created
     */
    @BeforeEach
    public  void beforeEach() throws IOException {
        this.mockedVersion = Mockito.mock(MockedSample.class, Mockito.CALLS_REAL_METHODS);
        this.tempFile = File.createTempFile("mockfake", "blubb");
    }


    /**
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void readTestTextFile() throws URISyntaxException, IOException {
        URL inputURL = this.getClass().getResource("/test.txt");
        File in = new File(inputURL.toURI());
        List<String> result = mockedVersion.readTestTextFile(in);
        // verify(mockedVersion, times(1)).getFileInStream(in);
        verify(mockedVersion, times(2)).getFileInStream(any());
    }


    /***
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void readTestTextFileCorrectFMock() throws URISyntaxException, IOException {
        URL inputURL = this.getClass().getResource("/test.txt");
        File in = new File(inputURL.toURI());
        List<String> result = mockedVersion.readTestTextFileCorrectFMock(in);
        verify(mockedVersion, times(1)).getFileInStream(any());
    }

    /**
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void readTestTextFileDataDep() throws URISyntaxException, IOException {
        MockedSample mockedVersion2 = Mockito.mock(MockedSample.class, Mockito.CALLS_REAL_METHODS);
        URL inputURL = this.getClass().getResource("/test.txt");
        File in = new File(inputURL.toURI());
        List<String> result = mockedVersion.readTestTextFileCorrectFMock(in);
        verify(mockedVersion, times(1)).getFileInStream(any());
        verify(mockedVersion, times(5)).readOneLine(any());

        inputURL = this.getClass().getResource("/testTwo.txt");
        in = new File(inputURL.toURI());
        result = mockedVersion2.readTestTextFileCorrectFMock(in);
        verify(mockedVersion2, times(1)).getFileInStream(any());
        verify(mockedVersion2, times(9)).readOneLine(any());
    }

    /**
     * Seems that all mocks with functions containing member variables of the object fail
     * @throws IOException error case
     */
    public void writeTestTextFileIncorrect() throws IOException{
        boolean ok = mockedVersion.writeTempTestTextfile();
        List<String> result =  mockedVersion.readTempTestTextfile();
        result.forEach(e -> System.out.println(e));
        int countRead = result.size() + 1; // now we are all times ok with it
        verify(mockedVersion, times(countRead)).readOneLine(any());
    }

    /**
     * Seems that all mocks with functions containing member variables of the object fail
     * @throws IOException error case
     */
    public void writeTestTextFileAlsoCorrect() throws IOException{
        MockedSample sample = new MockedSample();
        boolean ok = mockedVersion.writeTestTextFile(sample.getTempFile(),
                sample.getContent());
        List<String> result =  mockedVersion.readTestTextFile(sample.getTempFile());
        result.forEach(e -> System.out.println(e));
        int countRead = result.size() + 1; // now we are all times ok with it
        verify(mockedVersion, times(countRead)).readOneLine(any());
    }

    @Test
    public void writeTestTextFileAndRead() throws IOException{
        boolean ok = mockedVersion.writeTestTextFile(this.tempFile,
                this.content);
        List<String> result =  mockedVersion.readTestTextFileCorrectFMock(this.tempFile);
        result.forEach(System.out::println);
        int countLines = this.content.size(); // now we are all times ok with it
        verify(mockedVersion, times(countLines)).writeOneLine(any(), any());
        verify(mockedVersion, times(countLines + 1)).readOneLine(any());
    }

    @Test
    public void readWriteReadTestTextFile() {
    }
}