package pl.info.rkluszczynski.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rafal on 28.02.14.
 */

//@WebServlet(name = "helloServlet", urlPatterns = "/")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().write("Hello, Servlet!".getBytes());
    }
}
