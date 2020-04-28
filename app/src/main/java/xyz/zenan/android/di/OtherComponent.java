package xyz.zenan.android.di;

import xyz.zenan.android.ui.MainActivity;
import xyz.zenan.android.ui.TestViewModel;

public interface OtherComponent {

    void inject(MainActivity object);

    void inject(TestViewModel object);
}
