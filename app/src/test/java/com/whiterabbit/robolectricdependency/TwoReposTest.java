/*
 *
 *  * Copyright (C) 2015 Federico Paolinelli.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(application = TestRobolectricApplication.class)
public class TwoReposTest {
    @Before
    public void setup() {
        TestRobolectricApplication app = (TestRobolectricApplication) ApplicationProvider.getApplicationContext();
        MockApplicationModule module = new MockApplicationModule(app);
        Repo r1 = new Repo("RocketOnTheMoon", "Rocket", "COBOL");
        Repo r2 = new Repo("Embarassing project", "Embarassing", "Logo");
        ArrayList<Repo> list = new ArrayList<Repo>(Arrays.asList(r1, r2));
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
        assertEquals(secondRepo.getText(), "Embarassing project");
    }
}

