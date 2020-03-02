package com.github.vanlla.core.web;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;


/**
 * ExtendInputStream
 *
 * @author Vanlla
 * @since 1.0
 */
public class ExtendInputStream extends ServletInputStream {
    private ByteArrayInputStream is;

    public ExtendInputStream(byte[] buffer) {
        this.is = new ByteArrayInputStream(buffer);
    }

    @Override
    public int read() throws IOException {
        return this.is.read();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener arg0) {
    }
}