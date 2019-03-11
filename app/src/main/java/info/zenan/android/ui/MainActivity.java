package info.zenan.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.List;

import javax.inject.Inject;

import info.zenan.android.AndroidApplication;
import info.zenan.android.R;
import info.zenan.android.api.OtherService;
import info.zenan.android.data.Something;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @Inject
    OtherService otherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplication.getAppComponent(this).inject(this);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .build();

        OtherService otherService = retrofit.create(OtherService.class);

        Call<List<Something>> repos = otherService.listRepos("zenanhu");


//        otherService.listRepos().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<List<Something>>() {
//                    @Override
//                    public void call(List<Something> somethings) {
//                        for (Something something : somethings) {
//                            Log.i("tag", something.getName());
//                        }
//                    }
//                });
    }
}
