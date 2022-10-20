package com.example.assignment8;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView
import android.widget.TextView
public class MainActivity extends AppCompatActivity {
String names[]={"Bike","Boat","Bus","Car","Train","Run"};
String desc[]={"Bike Time to travel:2hrs 30min", "Boat Time to travel:0","Bus Time to Travel:4hrs 20min", "Car Time to travel:5hrs 20min", "Train time to Travel:3 hrs 30min","Run time to Travel:5hrs 10min" };
int images[]={R.drawable.bike, R.drawable.boat, R.drawable.bus,R.drawable.car,R.drawable.train, R.drawable.run};
ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lsv);
        lv.setAdapter(new MyAdapter(this));

    }
    private class MyAdapter extends BaseAdapter {
        Context context;

        public MyAdapter(MainActivity mainActivity)

        context=mainActivity;
    }
    @Override
    public int getCount() {
        return names.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Overridepublic View getView(int i, View view, ViewGroup viewGroup) {
        View V = LayoutInflater.frim(context).inflate(R.layout.layout, viewGroup, false);
        TextView t1 = V.findViewById(R.id.textView);
        ImageView img = V.findViewById(R.id.imageView);
        t1.setText(names[i]);
        img.setImageResource(images[i]);
        V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.utExtra("name", names[i]);
                intent.putExtra("desc", desc[i]);
                intent.putExtra("image", images[i]);
                startActivity(intent);
            }
        });
        return V;
    }

            }
        }


    }
}