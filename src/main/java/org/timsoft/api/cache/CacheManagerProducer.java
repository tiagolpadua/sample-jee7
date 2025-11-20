package org.timsoft.api.cache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import lombok.extern.slf4j.Slf4j;

/**
 * CDI Producer that creates and manages the JCache CacheManager. Configures default caches for the
 * application.
 */
@ApplicationScoped
@Slf4j
public class CacheManagerProducer {

  @Produces
  @ApplicationScoped
  public CacheManager createCacheManager() {
    log.info("Creating JCache CacheManager");

    // Explicitly use Ehcache as the JCache provider
    CachingProvider cachingProvider =
        Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
    CacheManager cacheManager = cachingProvider.getCacheManager();

    log.info("Using CachingProvider: {}", cachingProvider.getClass().getName());

    // Create caches with different expiration times
    createCache(cacheManager, "cache1Min", Duration.ONE_MINUTE);
    createCache(cacheManager, "cache2Min", new Duration(java.util.concurrent.TimeUnit.MINUTES, 2));

    log.info("CacheManager created successfully with {} caches", cacheManager.getCacheNames());
    return cacheManager;
  }

  private void createCache(CacheManager cacheManager, String cacheName, Duration duration) {
    Cache<Object, Object> cache = cacheManager.getCache(cacheName);
    if (cache == null) {
      MutableConfiguration<Object, Object> config =
          new MutableConfiguration<Object, Object>()
              .setTypes(Object.class, Object.class)
              .setStoreByValue(false)
              .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(duration));

      cacheManager.createCache(cacheName, config);
      log.info("Created cache: {} with expiration: {}", cacheName, duration);
    } else {
      log.info("Cache already exists: {}", cacheName);
    }
  }

  public void disposeCacheManager(@Disposes CacheManager cacheManager) {
    log.info("Disposing CacheManager");
    if (cacheManager != null && !cacheManager.isClosed()) {
      cacheManager.close();
    }
  }
}
