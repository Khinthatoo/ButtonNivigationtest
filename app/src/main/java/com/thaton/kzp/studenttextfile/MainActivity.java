package com.thaton.kzp.studenttextfile;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private FloatingActionButton floatingActionButton;
    String[] studentList;
    List<Student> stList=new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataAdapter=new DataAdapter(this,stList);

       // dataAdapter=new DataAdapter(MainActivity.this,studentList);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataAdapter);

//        try{  prepareData();}
      //  catch (Exception e){}





        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ContentActivity.class));
            }
        });
        }
    private void prepareData(){

        String student=new String(bindData());

        studentList=student.split("//");

        for(int i=0;i<studentList.length;i++){
            Student st=new Student(studentList[i].split(":")[0],studentList[i].split(":")[1],studentList[i].split(":")[2]);
            stList.add(st);
            dataAdapter.notifyDataSetChanged();

        }

       // List<String>studentList1= Arrays.asList(studentList);

}
    public String bindData(){

        StringBuilder stringBuilder = new StringBuilder();
        try {
            File myFile = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/StudentInfo/"+ContentActivity.FileName);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
           // BufferedReader bufferedReader = new BufferedReader(isr);

            String line;
            while ((line = myReader.readLine()) != null) {
                stringBuilder.append(line);
               // stringBuilder.append("//");

            }

            myReader.close();

            Log.d("RESPONSE ", stringBuilder.toString());

          //  return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();

    }

    //@Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            stList.clear();
            prepareData();
        }
        catch (Exception e){}
    }
}
