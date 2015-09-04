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

import com.whiterabbit.robolectricdependency.inject.ApplicationModule;
import com.whiterabbit.robolectricdependency.inject.MockApplicationModule;

public class TestRobolectricApplication extends RobolectricApplication {
    private ApplicationModule mApplicationModule;

    @Override
    ApplicationModule getApplicationModule() {
        if (mApplicationModule == null) {
            return super.getApplicationModule();
        }
        return mApplicationModule;
    }

    public void setApplicationModule(ApplicationModule mApplicationModule) {
        this.mApplicationModule = mApplicationModule;
        initComponent();
    }
}
