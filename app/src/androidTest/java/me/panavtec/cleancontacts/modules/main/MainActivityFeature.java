package me.panavtec.cleancontacts.modules.main;

import android.support.test.espresso.intent.Intents;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import me.panavtec.cleancontacts.ActivityResumedIdlingResource;
import me.panavtec.cleancontacts.DaggerActivityTestRule;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.modules.detail.DetailActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class) @LargeTest public class MainActivityFeature {

  @Rule public DaggerActivityTestRule<MainActivity> activityTestRule =
      new DaggerActivityTestRule<>(MainActivity.class);

  private ActivityResumedIdlingResource idleUntilResume;

  @Test public void load_a_contact_list_when_screen_is_resumed() {
    RecyclerView recyclerView = activityTestRule.getActivity().recyclerView;
    assertThat(recyclerView.getChildCount(), is(3));
  }

  @Test public void navigates_to_a_details_when_click_on_list() {
    Intents.init();
    onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(2, click()));
    intended(hasComponent(DetailActivity.class.getCanonicalName()));
    Intents.release();
  }
}