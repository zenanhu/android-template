package info.zenan.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import info.zenan.android.R;
import info.zenan.android.api.OtherService;
import info.zenan.android.data.Something;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OtherService service = retrofit.create(OtherService.class);

        Call<ArrayList<Something>> repos = service.listRepos("zenanhu");

        repos.enqueue(new Callback<ArrayList<Something>>() {
            @Override
            public void onResponse(Call<ArrayList<Something>> call, Response<ArrayList<Something>> response) {
                List<Something> somethingList = response.body();
                for (Something something : somethingList) {
                    Log.i("tag", something.getName());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Something>> call, Throwable t) {

            }
        });
    }
}
