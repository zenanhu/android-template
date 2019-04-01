package info.zenan.android.di;

import info.zenan.android.ui.MainActivity;
import info.zenan.android.ui.TestViewModel;

public interface OtherComponent {

    void inject(MainActivity object);

    void inject(TestViewModel object);
}
