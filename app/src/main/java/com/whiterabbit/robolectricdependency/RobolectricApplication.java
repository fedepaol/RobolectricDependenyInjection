package com.whiterabbit.robolectricdependency;

import android.app.Application;

import com.whiterabbit.robolectricdependency.inject.ApplicationModule;
import com.whiterabbit.robolectricdependency.inject.DaggerRoboSampleComponent;
import com.whiterabbit.robolectricdependency.inject.RoboSampleComponent;

public class RobolectricApplication extends Application {
    private RoboSampleComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    ApplicationModule getApplicationModule() {
        return new ApplicationModule(this);
    }

    public RoboSampleComponent getComponent() {
        return mComponent;
    }

    void initComponent() {
        mComponent = DaggerRoboSampleComponent.builder()
                .applicationModule(getApplicationModule())
                .build();
    }
}
