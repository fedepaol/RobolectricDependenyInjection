package com.whiterabbit.robolectricdependency;

import android.app.Activity;
import android.widget.TextView;

import com.whiterabbit.robolectricdependency.client.Repo;
import com.whiterabbit.robolectricdependency.inject.MockApplicationModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(application = TestRobolectricApplication.class)
public class SampleTest {
    @Before
    public void setup() {
        TestRobolectricApplication app = (TestRobolectricApplication) ApplicationProvider.getApplicationContext();

        // Setting up the mock module
        MockApplicationModule module = new MockApplicationModule(app);
        Repo r1 = new Repo("RocketOnTheMoon", "Rocket", "COBOL");
        ArrayList<Repo> list = new ArrayList<Repo>(Arrays.asList(r1));
        module.setResult(list);

        app.setApplicationModule(module);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void oneRepoReturned() {
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        TextView firstRepo = activity.findViewById(R.id.first_repo);
        TextView secondRepo = activity.findViewById(R.id.second_repo);
        assertEquals("Only one repo", firstRepo.getText(), "RocketOnTheMoon");
        assertEquals(secondRepo.getText(), "");
    }
}

