package com.shazan.studentdetails;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText edname, edcourse, edmarks,edID,edStudentID;
    Button btnAddData,btnViewAll,btnUpdateData,btnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);

        edname = findViewById(R.id.editTextTextPersonName);
        edcourse = findViewById(R.id.editTextTextPersoncourse);
        edmarks = findViewById(R.id.editTextTextPersonmarks);
        btnAddData = findViewById(R.id.buttonInsert);
        btnViewAll=findViewById(R.id.buttonView);
        btnUpdateData=findViewById(R.id.buttonUpdate);
        edID=findViewById(R.id.editTextTextID);
        btnDeleteData=findViewById(R.id.buttonDelete);
        edStudentID=findViewById(R.id.editTextTextPersonSNumber);

        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
    }
    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(edname.getText().toString(), edcourse.getText().toString(), edmarks.getText().toString(),edStudentID.getText().toString());
                if (isInserted)
                    Toast.makeText(MainActivity.this,"Data inserted successfully",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void ViewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor reshults=mydb.getAllData();
                if (reshults.getCount()==0){
                    showMessage("error message:","No data avilabale in the database");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (reshults.moveToNext()){
                    buffer.append("ID:"+reshults.getString(0)+"\n");
                    buffer.append("StudentID:"+reshults.getString(4)+"\n");
                    buffer.append("name:"+reshults.getString(1)+"\n");
                    buffer.append("course:"+reshults.getString(2)+"\n");
                    buffer.append("marks:"+reshults.getString(3)+"\n\n");
                    showMessage("list of data",buffer.toString());
                }
            }
        });
    }
    public void showMessage(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void UpdateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=mydb.UpdateData(edID.getText().toString(),edname.getText().toString(),edcourse.getText().toString(),edmarks.getText().toString(),edStudentID.getText().toString());
                if(isUpdate==true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not updated",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void DeleteData(){
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Integer deletedatarows=mydb.DeleteData(edID.getText().toString());
                if(deletedatarows>0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
            }
        });
}}
