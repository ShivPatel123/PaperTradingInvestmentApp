package com.example.as1;


import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.as1.screens.StartPage;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class AndroidTest {

    @Rule   // needed to launch the activity
    public ActivityTestRule<AndroidTest> activityRule = new ActivityTestRule<>(StartPage.class);


}