package com.thaton.kzp.studenttextfile;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ContentActivity extends AppCompatActivity {

    private EditText etName,etYear;
    private Spinner spinnerUni;

    static  String FileName = "StudentList.txt";

    String [] uniList={"UCSM", "UCSY", "CUM", "UCS(Monywr)", "CU(Kalay)", "UCS(TGI)", "UCS(Pinlon)", "UIT"};

    private int REQUEST_WRITE_PERMISSION = 2;
    boolean  hasWritePermission;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);


        hasWritePermission = (ContextCompat.checkSelfPermission(ContentActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if(!hasWritePermission){
            // ask the permission
            ActivityCompat.requestPermissions(ContentActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_PERMISSION);
        }

        etName=findViewById(R.id.et_name);
        etYear=findViewById(R.id.et_year);
        spinnerUni=findViewById(R.id.spin_uni);

        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(ContentActivity.this,android.R.layout.simple_dropdown_item_1line,uniList);
        spinnerUni.setAdapter(arrayAdapter);
        spinnerUni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
            });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            hasWritePermission = true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_save) {
            savetoFile();
            return true;
        }
        else if(id == R.id.action_clear){

            etName.setText("");
            etYear.setText("");
            spinnerUni.setSelection(0);
            return true;
            }
        return super.onOptionsItemSelected(item);
        }

    public static boolean CreateDir_NotExists(String path) {
        boolean ret = true;

        File file = new File(Environment.getExternalStorageDirectory(), path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("CREATE DIR : ", "Problem creating folder");
                ret = false;
            }
        }
        return ret;
    }

    private void savetoFile(){

        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/StudentInfo/"+FileName;
        String data = etName.getText().toString()+ ":" +etYear.getText().toString()
                +":"+spinnerUni.getSelectedItem().toString();
        Log.i("SAVE DATA : ", data);

        try {

            if(CreateDir_NotExists("/StudentInfo")) {

                FileWriter fw = new FileWriter(filepath, true);
                fw.write(data + "//");
                fw.close();

                etName.setText("");
                etYear.setText("");
                spinnerUni.setSelection(0);

                Snackbar.make(getWindow().getDecorView().getRootView(), "Saved to : " + filepath, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }else{
                Toast.makeText(getApplicationContext(), "Problem creating folder", Toast.LENGTH_LONG).show();
            }

        } catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

}