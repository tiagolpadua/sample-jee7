package org.timsoft.api.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/** Debug endpoint to inspect cache status and contents. */
@Path("/cache-debug")
public class CacheDebugResource {

  @Inject private CacheManager cacheManager;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Object> getCacheStatus() {
    Map<String, Object> status = new HashMap<>();

    // Check if CacheManager is available
    status.put("cacheManagerAvailable", cacheManager != null);
    if (cacheManager == null) {
      status.put("error", "CacheManager not injected");
      return status;
    }

    status.put("cacheManagerClass", cacheManager.getClass().getName());
    status.put("cacheManagerClosed", cacheManager.isClosed());

    // List all cache names
    List<String> cacheNames = new ArrayList<>();
    cacheManager.getCacheNames().forEach(cacheNames::add);
    status.put("cacheNames", cacheNames);

    // Check bookCache specifically
    Cache<Object, Object> cache = cacheManager.getCache("cache");
    Map<String, Object> cacheInfo = new HashMap<>();
    cacheInfo.put("exists", cache != null);

    if (cache != null) {
      // Count entries
      int count = 0;
      List<String> keys = new ArrayList<>();
      for (Cache.Entry<Object, Object> entry : cache) {
        count++;
        keys.add(entry.getKey().toString());
      }
      cacheInfo.put("entryCount", count);
      cacheInfo.put("keys", keys);
    }

    status.put("cache", cacheInfo);

    return status;
  }
}
