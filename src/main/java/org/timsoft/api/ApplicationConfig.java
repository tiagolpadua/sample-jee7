package org.timsoft.api;

import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.prometheus.client.hotspot.DefaultExports;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

	private static final Logger LOG = getLogger(ApplicationConfig.class.getName());

	public ApplicationConfig() {
		LOG.fine("Prometheus DefaultExports");
		DefaultExports.initialize();
	}

}