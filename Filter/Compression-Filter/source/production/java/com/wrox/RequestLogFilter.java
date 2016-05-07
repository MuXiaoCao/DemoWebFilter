package com.wrox;

import org.apache.commons.lang3.time.StopWatch;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

/**
 * 简单的日志过滤器
 *
 */
public class RequestLogFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Instant time = Instant.now();
		StopWatch timer = new StopWatch();
		try {
			timer.start();
			chain.doFilter(request, response);
		} finally {
			// 将日志记录动作放入finally中，这样过滤器链中抛出的任何异常都不会组织日志语句的执行
			timer.stop();
			HttpServletRequest in = (HttpServletRequest) request;
			HttpServletResponse out = (HttpServletResponse) response;
			String length = out.getHeader("Content-Length");
			if (length == null || length.length() == 0)
				length = "-";
			System.out.println(
					in.getRemoteAddr() + " - - [" + time + "]" + " \"" + in.getMethod() + " " + in.getRequestURI() + " "
							+ in.getProtocol() + "\" " + out.getStatus() + " " + length + " " + timer);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
