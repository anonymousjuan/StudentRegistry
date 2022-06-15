package com.example.studentregistryv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
RadioGroup rbGroup;
RadioButton rbChoice;

Button btnAdd,btnView;
EditText etName, etStudentID;
ListView lvShowData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbGroup = findViewById(R.id.rbGroup);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        etName = findViewById(R.id.etName);
        etStudentID =findViewById(R.id.etStudentID);
        lvShowData = findViewById(R.id.lvShowData);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String id = etStudentID.getText().toString().trim();

                int radioId = rbGroup.getCheckedRadioButtonId();
                rbChoice = findViewById(radioId);

                String sex = rbChoice.getText().toString();

                DataModel dataModel = new DataModel(null,name,id,sex);

               // Toast.makeText(MainActivity.this, dataModel.toString(), Toast.LENGTH_SHORT).show();
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                Boolean success = databaseHelper.addOne(dataModel);
                Toast.makeText(MainActivity.this, "success" + success, Toast.LENGTH_SHORT).show();

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                List<DataModel> result =databaseHelper.getEveryone();
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,databaseHelper.getEveryone());
                lvShowData.setAdapter(arrayAdapter);
            }
        });

    lvShowData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DataModel dataClicked = (DataModel)parent.getItemAtPosition(position);

        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.deleteOne(dataClicked);

            List<DataModel> result =databaseHelper.getEveryone();
            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,databaseHelper.getEveryone());
            lvShowData.setAdapter(arrayAdapter);

        }
    });
    }
    /*public void Selected(View view){
        int radioId = rbGroup.getCheckedRadioButtonId();
        rbChoice = findViewById(radioId);
        Toast.makeText(this, "Selected "+rbChoice.getText(), Toast.LENGTH_SHORT).show();
    }*/
}