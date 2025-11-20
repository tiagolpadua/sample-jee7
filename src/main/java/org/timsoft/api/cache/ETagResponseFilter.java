package org.timsoft.api.cache;

import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/** Response filter that adds ETag header and handles 304 Not Modified. */
@Provider
@ETag
public class ETagResponseFilter implements ContainerResponseFilter {
  private Logger logger = Logger.getLogger(ETagResponseFilter.class.getName());

  @Override
  public void filter(
      ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
    logger.info("ETagResponseFilter invoked...");
    Object entity = responseContext.getEntity();
    if (entity != null && responseContext.getStatus() == 200) {
      logger.info("Calculating ETag for the response entity...");
      String hash = Integer.toHexString(entity.hashCode());
      String etag = "\"" + hash + "\"";

      String ifNoneMatch = (String) requestContext.getProperty("If-None-Match");
      logger.info("If-None-Match from request: " + ifNoneMatch);
      logger.info("Calculated ETag: " + etag);
      if (ifNoneMatch != null && ifNoneMatch.equals(etag)) {
        logger.info("ETag matches If-None-Match. Setting response to 304 Not Modified.");
        responseContext.setStatus(Response.Status.NOT_MODIFIED.getStatusCode());
        responseContext.setEntity(null);
      } else {
        logger.info("ETag does not match If-None-Match. Proceeding with response.");
      }

      responseContext.getHeaders().putSingle("ETag", etag);

      CacheControl cacheControl = new CacheControl();
      cacheControl.setMaxAge(0);
      cacheControl.setMustRevalidate(true);
      cacheControl.setPrivate(false);
      responseContext.getHeaders().putSingle("Cache-Control", cacheControl.toString());
    }
  }
}
