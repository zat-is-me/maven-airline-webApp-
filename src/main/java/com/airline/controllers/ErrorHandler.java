
package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ErrorHandler")
public class ErrorHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processError(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
	// Analyze the servlet exception
	Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
	Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
	String type = (String) request.getAttribute("javax.servlet.error.exception_type");
	String message = (String) request.getAttribute("javax.servlet.error.message");

	if (servletName == null) {
	    servletName = "Unknown servlet name";
	}

	String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

	if (requestUri == null) {
	    requestUri = "Unknown uri";
	}

	// Set response content type
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();

	out.write("<html><head><title>Exception/Error Details</title></head><body>");

	if (statusCode != 500) {
	    out.write("<h3>Error Details</h3>");
	    out.write("<strong>Status Code</strong>: " + statusCode + "<br>");
	    out.write("<strong>Requested URI</strong>: " + requestUri + "<br>");
	    out.write("<strong>Error Message</strong>: " + message + "<br>");
	    out.write("<strong>Error type</strong>: " + type + "<br>");

	} else {
	    out.write("<h3>Exception Details</h3>");
	    out.write("<ul><li><strong>Status Code:</strong> " + statusCode + "</li>");
	    out.write("<li><strong>Requested URI</strong>: " + requestUri + "</li>");
	    out.write("<li><strong>Error by </strong>: " + throwable + "</li>");
	    out.write("<li><strong>Servlet name</strong>: " + servletName + "</li>");
	    out.write("<li><strong>Error Message</strong>: " + message + "</li>");
	    out.write("<li><strong>Error type</strong>: " + type + "</li>");
	    out.write("</ul>");
	}
	out.write("<br><br>");
	out.write("<a href=\"AddPassenger\">Home</a>");
	out.write("</body></html>");
    }
}
