/*
 * Copyright (c) Harald Glab-Plhak, 2025
 */

package io.examples.fakes;

import java.io.*;
import java.util.Objects;

public class FakeInputStream extends InputStream {

    public boolean closed = false;

    private final InputStream internalInput;

    public FakeInputStream() {
        this((InputStream) null);
    }
    public FakeInputStream(InputStream inputStream) {
        this.internalInput = inputStream;
    }

    private void ensureOpen() {

    }

    @Override
    public int available () throws IOException {
        ensureOpen();
        return 0;
    }


    @Override
    public int read() throws IOException {
        ensureOpen();
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        Objects.checkFromIndexSize(off, len, b.length);
        if (len == 0) {
            return 0;
        }
        ensureOpen();
        return -1;
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        ensureOpen();
        return new byte[0];
    }

    @Override
    public int readNBytes(byte[] b, int off, int len)
            throws IOException {
        ensureOpen();
        Objects.checkFromIndexSize(off, len, b.length);

        return 0;
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        if (len < 0) {
            throw new IllegalArgumentException("len < 0");
        }

        return new byte[0];
    }

    @Override
    public long skip(long n) throws IOException {
        ensureOpen();
        return 0L;
    }

    @Override
    public void skipNBytes(long n) throws IOException {
        ensureOpen();
        if (n > 0) {
            throw new EOFException();
        }
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        ensureOpen();
        Objects.requireNonNull(out);
        ensureOpen();
        return 0L;
    }

    @Override
    public void close() throws IOException {
        closed = true;
    }
}
