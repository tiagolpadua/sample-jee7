package org.timsoft.api.test;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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

	public static final String NOT_DEFINED = "N/D";

	static final Counter tests = Counter.build().name("tests_total").labelNames("userAgent", "host", "referer")
			.help("Total requests.").register();
	
	static final Counter starts = Counter.build().name("starts_total").labelNames("userAgent", "host", "referer")
			.help("Total requests.").register();

	// http://localhost:8080/sample-jee7/api/monitor/test
	@GET
	@Path("test")
	public Response test(@Context HttpServletRequest request) {
		String userAgent = valueOrNotDefined(request.getHeader("User-Agent"));
		String host = valueOrNotDefined(request.getRemoteHost());
		String referer = valueOrNotDefined(request.getHeader("referer"));
		tests.labels(userAgent, host, referer).inc();
		return Response.ok("Test Ok").build();
	}
	
	// http://localhost:8080/sample-jee7/api/monitor/start
	@GET
	@Path("start")
	public Response start(@Context HttpServletRequest request) {
		String userAgent = valueOrNotDefined(request.getHeader("User-Agent"));
		String host = valueOrNotDefined(request.getRemoteHost());
		String referer = valueOrNotDefined(request.getHeader("referer"));
		starts.labels(userAgent, host, referer).inc();
		return Response.ok("Start Ok").build();
	}

	private String valueOrNotDefined(String value) {
		if (value != null) {
			return value;
		} else {
			return NOT_DEFINED;
		}
	}
}