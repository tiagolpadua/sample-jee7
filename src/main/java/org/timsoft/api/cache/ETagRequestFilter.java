package org.timsoft.api.cache;

import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 * Request filter that stores If-None-Match header for later comparison. The actual 304 response is
 * handled by ETagResponseFilter after entity hash is calculated.
 */
@Provider
@ETag
public class ETagRequestFilter implements ContainerRequestFilter {
  private Logger logger = Logger.getLogger(ETagRequestFilter.class.getName());

  @Override
  public void filter(ContainerRequestContext requestContext) {
    logger.info("ETagRequestFilter invoked...");
    String ifNoneMatch = requestContext.getHeaderString("If-None-Match");
    if (ifNoneMatch != null) {
      logger.info("If-None-Match header present: " + ifNoneMatch);
      requestContext.setProperty("If-None-Match", ifNoneMatch);
    } else {
      logger.info("No If-None-Match header present in the request.");
    }
  }
}
