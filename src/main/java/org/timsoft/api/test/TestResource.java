package org.timsoft.api.test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.timsoft.api.services.MetricsService;

// WildFly
// http://localhost:8080/sample-jee7/api/test

// Weblogic
// http://localhost:7001/sample-jee7/api/test

@RequestScoped
@Path("monitor")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {
	private static final String NOT_DEFINED = "N/D";

	@Inject
	private MetricsService metricsService;

	// http://localhost:8080/sample-jee7/api/monitor/test
	@GET
	@Path("test")
	public Response test(@Context HttpServletRequest request) {
		metricsService.countTest(getUserAgent(request), getHost(request), getReferer(request));
		return Response.ok("Test Ok").build();
	}
	
	// http://localhost:8080/sample-jee7/api/monitor/start
	@GET
	@Path("start")
	public Response start(@Context HttpServletRequest request) {
		metricsService.countStart(getUserAgent(request), getHost(request), getReferer(request));
		return Response.ok("Start Ok").build();
	}

	private String getReferer(HttpServletRequest request) {
		return valueOrNotDefined(request.getHeader("referer"));
	}

	private String getHost(HttpServletRequest request) {
		return valueOrNotDefined(request.getRemoteHost());
	}

	private String getUserAgent(HttpServletRequest request) {
		return valueOrNotDefined(request.getHeader(HttpHeaders.USER_AGENT));
	}

	private String valueOrNotDefined(String value) {
		if (value != null) {
			return value;
		} else {
			return NOT_DEFINED;
		}
	}
}