package info.zenan.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import javax.inject.Inject;

import info.zenan.android.AndroidApplication;
import info.zenan.android.R;
import info.zenan.android.api.OtherService;
import info.zenan.android.data.Something;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Inject
    OtherService otherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplication.getAppComponent(this).inject(this);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();

//        OtherService service = retrofit.create(OtherService.class);

        otherService.listRepos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Something>>() {
                    @Override
                    public void call(List<Something> somethings) {
                        for (Something something : somethings) {
                            Log.i("tag", something.getName());
                        }
                    }
                });
    }
}
