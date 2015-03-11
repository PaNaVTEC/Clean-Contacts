package me.panavtec.cleancontacts.data.repository.caching.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import me.panavtec.cleancontacts.data.repository.caching.strategy.list.ListCachingStrategy;
import me.panavtec.cleancontacts.data.repository.caching.strategy.ttl.TtlCachingStrategy;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import org.junit.Test;

public class TtlListCachingStrategyTest {

  private List<BddContact> bddContacts = new ArrayList<>();

  private TtlCachingStrategy<BddContact> ttlCachingStrategy =
      new TtlCachingStrategy<>(1, TimeUnit.MINUTES);
  private ListCachingStrategy<BddContact> listCachingStrategy =
      new ListCachingStrategy<>(ttlCachingStrategy);

  @Test public void testValidCache() {
    BddContact bddContact1 = new BddContact();
    bddContact1.setPersistedTime(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(59));
    BddContact bddContact2 = new BddContact();
    bddContact2.setPersistedTime(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(59));
    bddContacts.add(bddContact1);
    bddContacts.add(bddContact2);
    Assert.assertTrue(listCachingStrategy.isValid(bddContacts));
  }

  @Test public void testInvalidCache() {
    prepareInvalidCacheStubs();
    Assert.assertFalse(listCachingStrategy.isValid(bddContacts));
  }

  @Test public void testCandidatesToPurgue() {
    prepareInvalidCacheStubs();
    Assert.assertTrue(listCachingStrategy.candidatesToPurgue(bddContacts).size() == bddContacts.size());
  }

  private void prepareInvalidCacheStubs() {
    BddContact bddContact1 = new BddContact();
    bddContact1.setPersistedTime(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(61));
    BddContact bddContact2 = new BddContact();
    bddContact2.setPersistedTime(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(61));
    bddContacts.add(bddContact1);
    bddContacts.add(bddContact2);
  }
}
