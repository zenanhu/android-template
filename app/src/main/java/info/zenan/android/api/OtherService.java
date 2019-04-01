package info.zenan.android.api;

import java.util.List;

import info.zenan.android.data.Something;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OtherService {

    @GET("users/zenanhu/repos")
    Observable<List<Something>> listRepos();

    @GET("users/{user}/repos")
    Call<List<Something>> listRepos(@Path("user") String user);
}
