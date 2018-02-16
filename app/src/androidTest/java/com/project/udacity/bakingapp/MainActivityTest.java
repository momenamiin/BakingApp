package com.project.udacity.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.anything;

/**
 * Created by momenamiin on 2/16/18.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickonlist() {
        onData(anything()).inAdapterView(withId(R.id.recyclerView)).atPosition(1).perform(click());
        // Checks that the OrderActivity opens with the correct tea name displayed
        onView(withId(R.id.master_list_fragment_container)).check(matches(isDisplayed()));
    }


}
