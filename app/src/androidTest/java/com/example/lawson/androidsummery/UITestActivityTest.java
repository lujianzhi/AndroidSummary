package com.example.lawson.androidsummery;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.lawson.androidsummery.junittest.UITestActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Ian.Lu on 2017/9/15.
 * Project : AndroidSummary
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITestActivityTest {

    @Rule
    public ActivityTestRule<UITestActivity> activityTestRule = new ActivityTestRule<UITestActivity>(UITestActivity.class);

    @Test
    public void save() {
        onView(withId(R.id.input_edit_text)).perform(typeText("yoyoyo"), closeSoftKeyboard());
        onView(withText("save")).perform(click());
        onView(withId(R.id.show_text_view)).check(matches(withText("yoyoyo")));
    }
}
