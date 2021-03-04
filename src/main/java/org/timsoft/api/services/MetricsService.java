package org.timsoft.api.services;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

import io.prometheus.client.Counter;

@ApplicationScoped
@Startup
public class MetricsService {
	private static final String REFERER = "referer";
	private static final String HOST = "host";
	private static final String USER_AGENT = "user_agent";

	private Counter tests;
	private Counter starts;
	
	@PostConstruct
	public void init() {
		tests = Counter.build().name("tests_total").labelNames(USER_AGENT, HOST, REFERER)
				.help("Total tests.").register();
		
		starts = Counter.build().name("starts_total").labelNames(USER_AGENT, HOST, REFERER)
				.help("Total starts.").register();	
	}

	public void countTest(String userAgent, String host, String referer) {
		tests.labels(userAgent, host, referer).inc();
	}
	
	public void countStart(String userAgent, String host, String referer) {
		starts.labels(userAgent, host, referer).inc();
	}
}
