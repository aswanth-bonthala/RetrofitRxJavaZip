package example.test.retrofitrxjavazip.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import example.test.retrofitrxjavazip.R;
import example.test.retrofitrxjavazip.adapter.EmployeeAdapter;
import example.test.retrofitrxjavazip.model.Data;
import example.test.retrofitrxjavazip.model.EmployeeResponse;
import example.test.retrofitrxjavazip.repository.EmployeeRepository;
import example.test.retrofitrxjavazip.viewmodel.EmployeeViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_main);
        EmployeeViewModel employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        employeeViewModel.getEmployeeData(new EmployeeRepository()).observe(this, new Observer<List<EmployeeResponse>>() {
            @Override
            public void onChanged(List<EmployeeResponse> employeeResponses) {
                ArrayList<Data> employeeData = new ArrayList<Data>();
                for (EmployeeResponse employeeResponse : employeeResponses) {
                    employeeData.add(employeeResponse.getData());
                }
                employeeAdapter = new EmployeeAdapter(MainActivity.this,employeeData);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(employeeAdapter);
            }
        });
    }
}