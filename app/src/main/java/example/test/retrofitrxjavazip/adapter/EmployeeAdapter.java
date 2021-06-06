package example.test.retrofitrxjavazip.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import example.test.retrofitrxjavazip.R;
import example.test.retrofitrxjavazip.model.Data;
import example.test.retrofitrxjavazip.model.EmployeeResponse;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmpViewHolder> {

    Activity context;
    ArrayList<Data> employeeData;

    public EmployeeAdapter(Activity context, ArrayList<Data> employeeData) {
        this.context = context;
        this.employeeData = employeeData;
    }

    @NonNull
    @Override
    public EmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        return new EmpViewHolder(rootView);

    }

    @Override
    public void onBindViewHolder(@NonNull EmpViewHolder holder, int position) {

        Data data = employeeData.get(position);
        holder.tvEmail.setText("Email Id: "+data.getEmail());
        holder.tvName.setText("Name: "+data.getFirstName());
    }

    @Override
    public int getItemCount() {
        return employeeData.size();
    }

    public class EmpViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmail,tvName;

        public EmpViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvName = itemView.findViewById(R.id.tv_Name);
        }
    }
}
