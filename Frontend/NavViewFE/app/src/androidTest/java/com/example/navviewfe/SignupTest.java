package com.example.navviewfe;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.navviewfe.Screens.CreateAccountPage;
import com.example.navviewfe.Screens.CreateGroup;
import com.example.navviewfe.Screens.LoginPage;
import com.example.navviewfe.Screens.StartPage;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SignupTest {
    @Rule   // needed to launch the activity
    public ActivityTestRule<CreateAccountPage> activityRule = new ActivityTestRule<>(CreateAccountPage.class);

    /**
     * Start the server and run this test
     */
    @Test
    public void Signup(){
        //Enter username
        onView(withId(R.id.usernameSignup_txtInput))
                .perform(typeText("user"), closeSoftKeyboard());
        //Enter password
        onView(withId(R.id.passwordSignup_txtInput))
                .perform(typeText("Password"), closeSoftKeyboard());
        //Send login request
        onView(withId(R.id.sendSignupReq_btn)).perform(click());
        //Check
        onView(withId(R.id.signupReqResponse_txt)).check(matches(withText("username already taken")));
    }

}