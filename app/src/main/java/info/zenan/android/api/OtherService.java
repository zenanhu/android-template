package info.zenan.android.api;

import java.util.List;

import info.zenan.android.data.Something;
import retrofit2.http.GET;
import rx.Observable;

public interface OtherService {

    @GET("users/zenanhu/repos")
    Observable<List<Something>> listRepos();
}
