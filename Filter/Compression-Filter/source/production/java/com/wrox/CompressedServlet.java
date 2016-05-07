package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "compressedServlet", urlPatterns = "/servlet")
/**
 * 处理请求，形成响应
 * 此时的response已经被filter封装了
 */
public class CompressedServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().println("This Servlet response may be compressed.");
	}
}
