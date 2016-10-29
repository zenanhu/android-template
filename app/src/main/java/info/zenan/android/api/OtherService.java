package info.zenan.android.api;

import java.util.ArrayList;

import info.zenan.android.data.Something;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OtherService {

    @GET("users/{user}/repos")
    Call<ArrayList<Something>> listRepos(@Path("user") String user);


}
