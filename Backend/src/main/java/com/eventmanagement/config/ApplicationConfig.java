// This package is part of the application's configuration layer
package com.eventmanagement.config;

// Importing Spring’s annotation to mark this class as a source of bean definitions
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

// Importing interfaces for customizing Spring MVC behavior
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Importing the custom interceptor that will process incoming HTTP requests
import com.eventmanagement.interceptor.RequestHeaderInterceptor;

/*
 * At the core of Spring's design is the concept of centralized configuration.
 * This class is annotated with @Configuration to indicate that it defines
 * beans or settings related to the application's infrastructure.
 */
@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

	/*
	 * Dependency Injection (DI) is a key principle of Spring.
	 * Instead of creating the interceptor manually, we let Spring manage its lifecycle
	 * and dependencies, injecting it here with @Autowired.
	 * This promotes modularity and testability.
	 */
	@Autowired
	private RequestHeaderInterceptor requestHeaderInterceptor;

	/*
	 * The WebMvcConfigurer interface provides callback methods to customize
	 * Spring MVC configuration without overriding everything.
	 * 
	 * Interceptors are used to pre-process and post-process HTTP requests.
	 * By overriding addInterceptors(), we register our custom interceptor
	 * to hook into the lifecycle of every web request handled by the DispatcherServlet.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// We register our custom interceptor here.
		// This allows us to execute logic (e.g., logging, header validation) before the request reaches a controller.
		
		registry.addInterceptor(requestHeaderInterceptor);
	}
}
