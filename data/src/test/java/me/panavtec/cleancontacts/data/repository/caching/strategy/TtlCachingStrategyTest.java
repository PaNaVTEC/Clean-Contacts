package me.panavtec.cleancontacts.data.repository.caching.strategy;

import java.util.concurrent.TimeUnit;
import me.panavtec.cleancontacts.data.repository.caching.strategy.ttl.TtlCachingStrategy;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class TtlCachingStrategyTest {

  private TtlCachingStrategy<BddContact> ttlCachingStrategy;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    ttlCachingStrategy = new TtlCachingStrategy<>(1, TimeUnit.MINUTES);
  }
}
