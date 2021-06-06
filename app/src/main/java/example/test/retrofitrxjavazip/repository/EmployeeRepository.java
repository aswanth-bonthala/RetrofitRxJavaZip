package example.test.retrofitrxjavazip.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;


import example.test.retrofitrxjavazip.model.Data;
import example.test.retrofitrxjavazip.model.EmployeeResponse;
import example.test.retrofitrxjavazip.service.EmployeeService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class EmployeeRepository {

    private final MutableLiveData<List<EmployeeResponse>> listEmployeeMutableLiveData = new MutableLiveData<>();


    @SuppressLint("CheckResult")
    public LiveData<List<EmployeeResponse>> getEmployeeData(EmployeeService employeeService) {

        Observable<EmployeeResponse> observable1 = employeeService.getEmployeeResponse(1);
        Observable<EmployeeResponse> observable2 = employeeService.getEmployeeResponse(3);
        Observable<EmployeeResponse> observable3 = employeeService.getEmployeeResponse(10);

        Observable<List<EmployeeResponse>> resultObservable = Observable.zip(
                observable1.subscribeOn(Schedulers.io()),
                observable2.subscribeOn(Schedulers.io()),
                observable3.subscribeOn(Schedulers.io()),
                new Function3<EmployeeResponse, EmployeeResponse, EmployeeResponse, List<EmployeeResponse>>() {

                    @NonNull
                    @Override
                    public List<EmployeeResponse> apply(@NonNull EmployeeResponse employeeResponse, @NonNull EmployeeResponse employeeResponse2, @NonNull EmployeeResponse employeeResponse3) {
                        List<EmployeeResponse> employeeResponseList = new ArrayList<EmployeeResponse>();
                        employeeResponseList.add(employeeResponse);
                        employeeResponseList.add(employeeResponse2);
                        employeeResponseList.add(employeeResponse3);
                        return employeeResponseList;
                    }
                });
        resultObservable.observeOn(AndroidSchedulers.mainThread()).subscribeWith(new Observer<List<EmployeeResponse>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<EmployeeResponse> employeeResponses) {
                listEmployeeMutableLiveData.postValue(employeeResponses);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


        return listEmployeeMutableLiveData;

    }


}
