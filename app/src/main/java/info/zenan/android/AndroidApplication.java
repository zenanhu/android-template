package info.zenan.android;

import android.app.Application;
import android.content.Context;

import info.zenan.android.di.AppComponent;

public class AndroidApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = AppComponent.Initializer.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    public static AppComponent getAppComponent(Context context) {
        return ((AndroidApplication) context.getApplicationContext()).appComponent;
    }
}
