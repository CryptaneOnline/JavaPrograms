package com.example.assignment7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity2 extends AppCompatActivity {
    EditText e6,e7,e8,e9,e10;
    Button b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e6 = findViewById(R.id.p7);
        e7 = findViewById(R.id.p8);
        e8 = findViewById(R.id.p9);
        e9 = findViewById(R.id.p10);
        e10 = findViewById(R.id.p11);
        b3 = findViewById(R.id.b3);
        Intent in2 = getIntent();
        e6.setText(in2.getStringExtra("p1"));
        e7.setText(in2.getStringExtra("p2"));
        e8.setText(in2.getStringExtra("p3"));
        e9.setText(in2.getStringExtra("p4"));
        e10.setText(in2.getStringExtra("p5"));
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new
                        Intent(MainActivity2.this,MainActivity.class);
                int sum =
                        Integer.parseInt(e6.getText().toString())+
                                Integer.parseInt(e7.getText().toString())+

                                Integer.parseInt(e8.getText().toString())+Integer.parseInt(e9.
                                getText().toString())+

                                Integer.parseInt(e10.getText().toString());
                i.putExtra("res",sum);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }
}