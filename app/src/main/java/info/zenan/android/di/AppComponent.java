package info.zenan.android.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import info.zenan.android.AndroidApplication;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent extends OtherComponent {

    void inject(AndroidApplication application);

    final class Initializer {
        private Initializer() {
        }

        public static AppComponent init(Application application) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule((AndroidApplication) application))
                    .build();
        }
    }
}
