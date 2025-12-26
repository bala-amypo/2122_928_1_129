package com.example.demo.servlet;

import jakarta.servlet.http.*;
import java.io.IOException;

public class HealthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setStatus(200);
        res.setContentType("text/plain");
        res.getWriter().write("BUNDLE-OK");
        res.getWriter().flush();
    }
}
