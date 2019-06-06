package com.thaton.kzp.studenttextfile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private Context mComtext;
    private List<Student> studentList=new ArrayList<>();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvYear,tvUni;
        private Student student;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tv_name);
            tvYear=itemView.findViewById(R.id.tv_year);
            tvUni=itemView.findViewById(R.id.tv_uni);

        }
        public void bindView(Student student){

            this.student= student;

            tvName.setText(student.getName());
            tvYear.setText(student.getYear());
            tvUni.setText(student.getUniName());
        }
    }

    public DataAdapter(Context mComtext,List<Student> studentList) {
        this.mComtext=mComtext;
        this.studentList=studentList;
    }

    @NonNull
    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_data_list,viewGroup,false);

        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.MyViewHolder myViewHolder, int i) {
        Student student=studentList.get(i);

        myViewHolder.bindView(student);

    }

    @Override
    public int getItemCount() {
        return studentList.size() ;
    }

}
