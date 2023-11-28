package com.example.as1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.as1.screens.StartPage;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartTest {
    @Rule   // needed to launch the activity
    public ActivityTestRule<StartPage> activityRule = new ActivityTestRule<>(StartPage.class);

    /**
     * Start the server and run this test
     */
    @Test
    public void Test1(){
        String testString = "hello";
        String resultString = "olleh";
        // Type in testString and send request
        onView(withId(R.id.toLoginBtn)).perform(click());

    }


}
