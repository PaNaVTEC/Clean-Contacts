package me.panavtec.cleancontacts.data.repository.caching.strategy;

import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import me.panavtec.cleancontacts.data.repository.caching.strategy.ttl.TtlCachingStrategy;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import org.junit.Test;

public class TtlCachingStrategyTest {

  private BddContact bddContact = new BddContact();
  private TtlCachingStrategy<BddContact> ttlCachingStrategy =
      new TtlCachingStrategy<>(1, TimeUnit.MINUTES);

  @Test public void testValidCache() {
    bddContact.setPersistedTime(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(59));
    Assert.assertTrue(ttlCachingStrategy.isValid(bddContact));
  }

  @Test public void testInvalidCache() {
    bddContact.setPersistedTime(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(61));
    Assert.assertFalse(ttlCachingStrategy.isValid(bddContact));
  }
}
