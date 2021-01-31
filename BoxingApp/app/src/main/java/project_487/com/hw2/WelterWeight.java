package project_487.com.hw2;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WelterWeight extends AppCompatActivity {

    GestureDetectorCompat mDetector;
    ImageView img;
    Spinner spinw;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    Dbhelper db;
    ArrayList<Boxer> bx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welter_weight);
        img = findViewById(R.id.imageView);
        spinw= findViewById(R.id.spinnerw);
        txt1=findViewById(R.id.textView2);
        txt2=findViewById(R.id.textView3);
        txt3=findViewById(R.id.textView4);
        txt4=findViewById(R.id.textView5);
        db=new Dbhelper(this);
        bx = (ArrayList<Boxer>)BoxersDB.findboxer(db,"welterweight");
        mDetector = new GestureDetectorCompat(WelterWeight.this, new MyGestureListener());

        AllBoxers.welterboxers.add(new Boxer(bx.get(0).name,bx.get(0).wins,bx.get(0).titles,bx.get(0).losses,bx.get(0).divisions,bx.get(0).division,bx.get(0).imgId));
        AllBoxers.welterboxers.add(new Boxer(bx.get(1).name,bx.get(1).wins,bx.get(1).titles,bx.get(1).losses,bx.get(1).divisions,bx.get(1).division,bx.get(1).imgId));
        AllBoxers.welterboxers.add(new Boxer(bx.get(2).name,bx.get(2).wins,bx.get(2).titles,bx.get(2).losses,bx.get(2).divisions,bx.get(2).division,bx.get(2).imgId));
        AllBoxers.welterboxers.add(new Boxer(bx.get(3).name,bx.get(3).wins,bx.get(3).titles,bx.get(3).losses,bx.get(3).divisions,bx.get(3).division,bx.get(3).imgId));


        MySpinnerAdapter spinnerAdapter = new MySpinnerAdapter(this, R.layout.spinnerlayout,  AllBoxers.welterboxers);
        spinw.setAdapter(spinnerAdapter);

        spinw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Boxer thisBoxer = AllBoxers.welterboxers.get(i);
                img.setImageResource(R.mipmap.keith);
                txt1.setText(Integer.toString(thisBoxer.getWins()));
                txt2.setText(Integer.toString(thisBoxer.getLosses()));
                txt3.setText(thisBoxer.getTitles());
                txt4.setText(Integer.toString(thisBoxer.getDivision()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onDoubleTap(MotionEvent event) {
            Toast.makeText(WelterWeight.this,"This is current top welterweight Fighters",Toast.LENGTH_LONG).show();
            return true;
        }
    }
    public boolean onTouchEvent(MotionEvent event){
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}


