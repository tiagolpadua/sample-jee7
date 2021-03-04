package org.timsoft.api.test;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.prometheus.client.Counter;

// WildFly
// http://localhost:8080/sample-jee7/api/test

// Weblogic
// http://localhost:7001/sample-jee7/api/test

@RequestScoped
@Path("monitor")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {
	private static final String NOT_DEFINED = "N/D";

	private static final String REFERER = "referer";
	private static final String HOST = "host";
	private static final String USER_AGENT = "user_agent";

	static final Counter tests = Counter.build().name("tests_total").labelNames(USER_AGENT, HOST, REFERER)
			.help("Total tests.").register();
	
	static final Counter starts = Counter.build().name("starts_total").labelNames(USER_AGENT, HOST, REFERER)
			.help("Total starts.").register();

	// http://localhost:8080/sample-jee7/api/monitor/test
	@GET
	@Path("test")
	public Response test(@Context HttpServletRequest request) {
		tests.labels(getUserAgent(request), getHost(request), getReferer(request)).inc();
		return Response.ok("Test Ok").build();
	}
	
	// http://localhost:8080/sample-jee7/api/monitor/start
	@GET
	@Path("start")
	public Response start(@Context HttpServletRequest request) {
		starts.labels(getUserAgent(request), getHost(request), getReferer(request)).inc();
		return Response.ok("Start Ok").build();
	}

	private String getReferer(HttpServletRequest request) {
		return valueOrNotDefined(request.getHeader(REFERER));
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