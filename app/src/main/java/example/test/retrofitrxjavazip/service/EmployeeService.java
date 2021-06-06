package example.test.retrofitrxjavazip.service;

import example.test.retrofitrxjavazip.model.EmployeeResponse;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeService {
    class Factory {
        public static EmployeeService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(EmployeeService.class);
        }
    }

    @GET("users/{id}")
    Observable<EmployeeResponse> getEmployeeResponse(@Path("id") int id);
}
