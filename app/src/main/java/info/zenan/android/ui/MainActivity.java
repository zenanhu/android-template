package info.zenan.android.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import info.zenan.android.AndroidApplication;
import info.zenan.android.R;
import info.zenan.android.api.OtherService;
import info.zenan.android.data.Something;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    OtherService otherService;

    private TestViewModel mShowUserViewModel;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplication.getAppComponent(this).inject(this);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);


        otherService.listRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<List<Something>>() {
                    @Override
                    public void accept(List<Something> somethings) {
                        for (Something something : somethings) {
                            Log.i("tag", something.getName());
                        }
                    }
                });

        // RoomDB

        mShowUserViewModel = ViewModelProviders.of(this).

                get(TestViewModel.class);

        populateDb();

        subscribeUiLoans();

    }

    private void populateDb() {
        mShowUserViewModel.createDb();
    }


    public void onRefreshBtClicked(View view) {
        populateDb();
        subscribeUiLoans();
    }

    private void subscribeUiLoans() {
        mShowUserViewModel.getUserNames().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String result) {
                textView.setText(result);
            }
        });
    }
}
