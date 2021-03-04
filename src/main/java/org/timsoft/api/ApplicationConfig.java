package org.timsoft.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.prometheus.client.hotspot.DefaultExports;

@ApplicationPath("api")
public class ApplicationConfig extends Application {
	public ApplicationConfig() {
		DefaultExports.initialize();
	}
}