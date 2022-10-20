package com.example.assignment7;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button b1,b2,b4;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.p1);
        e2 = findViewById(R.id.p2);
        e3 = findViewById(R.id.p3);
        e4 = findViewById(R.id.p4);
        e5 = findViewById(R.id.p5);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b4 = findViewById(R.id.b3);
        t1 = findViewById(R.id.p6);
        Random rand = new Random();
        int rand1 = rand.nextInt((13 - 1) + 1) + 1;
        int rand2 = rand.nextInt((13 - 1) + 1) + 1;
        int rand3 = rand.nextInt((13 - 1) + 1) + 1;
        int rand4 = rand.nextInt((13 - 1) + 1) + 1;
        int rand5 = rand.nextInt((13 - 1) + 1) + 1;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText(String.valueOf(rand1));
                e2.setText(String.valueOf(rand2));
                e3.setText(String.valueOf(rand3));
                e4.setText(String.valueOf(rand4));
                e5.setText(String.valueOf(rand5));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int []arr = new int[5];
                arr[0] = Integer.parseInt(e1.getText().toString());
                arr[1] = Integer.parseInt(e2.getText().toString());
                arr[2] = Integer.parseInt(e3.getText().toString());
                arr[3] = Integer.parseInt(e4.getText().toString());
                arr[4] = Integer.parseInt(e5.getText().toString());
                Arrays.sort(arr);
                Intent in1 = new
                        Intent(MainActivity.this,MainActivity2.class);
                in1.putExtra("p1",String.valueOf(arr[0]));
                in1.putExtra("p2",String.valueOf(arr[1]));
                in1.putExtra("p3",String.valueOf(arr[2]));
                in1.putExtra("p4",String.valueOf(arr[3]));
                in1.putExtra("p5",String.valueOf(arr[4]));
                startActivityForResult(in1,0);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                t1.setText("");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0)
        {
            if(resultCode==RESULT_OK)
            {
                assert data != null;
                int result = data.getIntExtra("res",0);
                t1.setText(String.valueOf(result));
            }
        }
    }}