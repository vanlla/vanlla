package com.github.vanlla.core.web;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * HttpRequestWrapper
 *
 * @author Vanlla
 * @since 1.0
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {
    private byte[] buf;

    public HttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        ServletInputStream is = request.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] b = new byte[1024];

        int len;
        while ((len = is.read(b)) > 0) {
            os.write(b, 0, len);
        }

        this.buf = os.toByteArray();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ExtendInputStream(this.buf);
    }

    @Override
    public String toString() {
        return new String(this.buf);
    }
}