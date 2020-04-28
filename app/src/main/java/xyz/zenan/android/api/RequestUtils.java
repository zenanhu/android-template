package xyz.zenan.android.api;

import org.json.JSONObject;

import okhttp3.CertificatePinner;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestUtils {
    public static RequestBody jsonObject(JSONObject object) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());
    }

    public static RequestBody string(String text) {
        return RequestBody.create(MediaType.parse("text/plain"), text);
    }

    public static CertificatePinner certificatePinner() {
        return new CertificatePinner.Builder()
                .add("http://url", "shaxxxxxxxxxxx")
                .build();
    }
}
