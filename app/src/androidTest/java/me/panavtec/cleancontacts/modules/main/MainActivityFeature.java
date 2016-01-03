package me.panavtec.cleancontacts.modules.main;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.intent.Intents;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import java.util.Arrays;
import java.util.List;
import me.panavtec.cleancontacts.ActivityResumedIdlingResource;
import me.panavtec.cleancontacts.di.DaggerActivityTestRule;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.modules.detail.DetailActivity;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.recyclerview.RecyclerViewInteraction;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class) @LargeTest public class MainActivityFeature {

  @Rule public DaggerActivityTestRule<MainActivity> activityTestRule =
      new DaggerActivityTestRule<>(MainActivity.class, true, false);

  private ActivityResumedIdlingResource idlingResource;

  @Before public void setUp() {
    waitUntilOnResumeExecuted();
    activityTestRule.launchActivity();
  }

  @Test public void load_a_contact_list_when_screen_is_resumed() {
    List<PresentationContact> items = Arrays.asList(contactNamed("mr", "fred", "bryant"),
        contactNamed("miss", "tamara", "watson"), contactNamed("mr", "sebastian", "gray"));
    RecyclerViewInteraction.<PresentationContact>onRecyclerView(withId(R.id.recyclerView))
        .withItems(items)
        .check(new RecyclerViewInteraction.ItemViewAssertion<PresentationContact>() {
          @Override
          public void check(PresentationContact item, View view, NoMatchingViewException e) {
            matches(hasDescendant(withText(item.getDisplayName()))).check(view, e);
          }
        });
  }

  @Test public void navigates_to_a_details_when_click_on_list() {
    Intents.init();
    onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(2, click()));
    intended(hasComponent(DetailActivity.class.getCanonicalName()));
    Intents.release();
  }

  @After public void after() {
    unregisterIdlingResources(idlingResource);
  }

  private void waitUntilOnResumeExecuted() {
    idlingResource = new ActivityResumedIdlingResource(
        (Application) InstrumentationRegistry.getTargetContext().getApplicationContext());
    registerIdlingResources(idlingResource);
  }

  private PresentationContact contactNamed(String title, String first, String last) {
    PresentationContact presentationContact = new PresentationContact();
    presentationContact.setTitle(title);
    presentationContact.setFirstName(first);
    presentationContact.setLastName(last);
    return presentationContact;
  }
}