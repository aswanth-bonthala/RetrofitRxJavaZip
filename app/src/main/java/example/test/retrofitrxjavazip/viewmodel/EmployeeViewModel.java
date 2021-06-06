package example.test.retrofitrxjavazip.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import example.test.retrofitrxjavazip.model.EmployeeResponse;
import example.test.retrofitrxjavazip.repository.EmployeeRepository;
import example.test.retrofitrxjavazip.service.EmployeeService;

public class EmployeeViewModel extends ViewModel {

    public LiveData<List<EmployeeResponse>> getEmployeeData(EmployeeRepository employeeRepository) {
        EmployeeService employeeService = EmployeeService.Factory.create();
        return employeeRepository.getEmployeeData(employeeService);
    }
}
