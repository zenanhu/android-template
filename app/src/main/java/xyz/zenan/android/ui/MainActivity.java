package xyz.zenan.android.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import xyz.zenan.android.AndroidApplication;
import xyz.zenan.android.R;
import xyz.zenan.android.api.OtherService;
import xyz.zenan.android.data.Something;
import xyz.zenan.android.event.SimpleEvent;
import xyz.zenan.android.widget.marquee.BaseAdapter;
import xyz.zenan.android.widget.marquee.MarqueeView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    OtherService otherService;

    private TestViewModel mShowUserViewModel;

    private TextView textView;

    private MarqueeView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplication.getAppComponent(this).inject(this);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        initMarqueeView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SimpleEvent("hello"));
            }
        });

        // RoomDB
        mShowUserViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        populateDb();
        subscribeUiLoans();
    }

    private void initMarqueeView() {
        marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        MarqueeAdapter adapter= new MarqueeAdapter(this);
        List<String> data = new ArrayList<>();
        data.add("Test");
        data.add("Test1");
        data.add("Test2");
        data.add("Test3");
        adapter.setData(data);
        marqueeView.setAdapter(adapter);
        marqueeView.notifyDataSetChanged();
        marqueeView.startFlipping();
    }

    private void populateDb() {
        mShowUserViewModel.createDb();
    }

    private void listRepos() {
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
    }

    private void testRxJava() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.d("TAG", "111 " + Thread.currentThread().getName());
                emitter.onNext("test");
            }
        }).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                Log.d("TAG", "222 " + Thread.currentThread().getName());
                return Observable.just(o);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).subscribe(new io.reactivex.Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                Log.d("TAG", "333 " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

//        Disposable disposable = Observable.just("test1").map(new Function<String, Object>() {
//            @Override
//            public Object apply(String s) throws Exception {
//                return null;
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//
//                    }
//                });

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i("TAG", "count: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart");
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle", "onStop");
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy");
    }

    @Subscribe
    public void onSimpleEvent(SimpleEvent event) {
        Log.d("EventBus", "event.message: " + event.message);
    }

    public static class MarqueeAdapter extends BaseAdapter<TextView, String> {
        private Context mContext;

        public MarqueeAdapter(Context context) {
            this.mContext = context;
        }
        @Override
        protected TextView getView(int position) {
            TextView mView = new TextView(mContext);
            String content = getItem(position);
            mView.setText(content);
            return mView;
        }

        @Override
        protected String getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public int getItemCount() {
            return super.getItemCount();
        }

        @Override
        protected void setData(List<String> dataList) {
            super.setData(dataList);
        }
    }
}
