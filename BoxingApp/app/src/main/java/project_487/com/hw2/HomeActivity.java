package project_487.com.hw2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Button g;
    RadioGroup rdbgroup;
    RadioButton rdb;
    RadioButton rdb2;
    RadioButton rdb3;
    Intent heavy;
    Intent welter;
    Intent middle;
    Dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Common.jsonStr =loadFileFromAssets("Boxers.json");

        Log.d("TAG", Common.jsonStr);


        db=new Dbhelper(this);
        List<Boxer> b = BoxersDB.getAllboxers(db);

        if(b.size() ==0){
            BoxersDB.getBoxers(db);
        }

        heavy= new Intent(HomeActivity.this, Main2Activity.class);
        welter=new Intent(HomeActivity.this,WelterWeight.class);
        middle=new Intent(HomeActivity.this,MiddleWeight.class);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        rdb = findViewById(R.id.radioButton);
        rdb2 = findViewById(R.id.radioButton2);
        rdb3 = findViewById(R.id.radioButton3);
        rdbgroup= findViewById(R.id.rdbg);
        g=findViewById(R.id.button);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdbgroup.getCheckedRadioButtonId()==R.id.radioButton)
                    startActivity(middle);
                else if(rdbgroup.getCheckedRadioButtonId()==R.id.radioButton2)
                    startActivity(heavy);
                else if(rdbgroup.getCheckedRadioButtonId()==R.id.radioButton3)
                    startActivity(welter);
                else if(rdbgroup.getCheckedRadioButtonId()==R.id.radioButton4)
                    alertDialog("boxing is fun!! Trust me.Try again");
            }
        });

    }
         private void alertDialog(String message) {

        AlertDialog.Builder mDialogBox = new AlertDialog.Builder(this);
        mDialogBox.setMessage(message);
        mDialogBox.setIcon(R.drawable.ic_launcher_background);
        mDialogBox.setTitle("Boxing is a nobel sport!!");
        mDialogBox.setMessage(message);
        mDialogBox.setPositiveButton("Try again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        mDialogBox.create();
        mDialogBox.show();
    }
    public String loadFileFromAssets(String fileName) {
        String fileContent = null;
        try {

            InputStream is =getBaseContext().getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            fileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return fileContent;

    }
}
