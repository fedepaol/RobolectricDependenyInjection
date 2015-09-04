package com.whiterabbit.robolectricdependency;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class SampleTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}

