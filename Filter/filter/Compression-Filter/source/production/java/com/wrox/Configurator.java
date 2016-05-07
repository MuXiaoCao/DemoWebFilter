package com.wrox;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
/**
 * 以编程的形式配置过滤器
 *
 */
public class Configurator implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

		FilterRegistration.Dynamic registration = context.addFilter("requestLogFilter", new RequestLogFilter());
		/**
		 * 第一个参数是派发类型
		 * 可以是一个也可以是多个或者0个，默认是REQUEST
		 * 总共有5中派发类型，分别是：
		 * DispatcherType：REQUEST,FORWAR,INCLUDE,ERROR,ASYNC
		 * 具体含义可以查看：JavaWeb高级编程第214页
		 */
		registration.addMappingForUrlPatterns(null, false, "/*");

		registration = context.addFilter("compressionFilter", new CompressionFilter());
		registration.setAsyncSupported(true);
		registration.addMappingForUrlPatterns(null, false, "/*");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
