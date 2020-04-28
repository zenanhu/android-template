package xyz.zenan.android;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import xyz.zenan.android.di.AppComponent;

public class AndroidApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = AppComponent.Initializer.init(this);

        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    public static AppComponent getAppComponent(Context context) {
        return ((AndroidApplication) context.getApplicationContext()).appComponent;
    }
}
