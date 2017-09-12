package com.willard5991.beautifulbulldogs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class BulldogListActivity extends AppCompatActivity {

//    private TextView textView;
    private ListView bulldogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog_list);

//        textView = (TextView) findViewById(R.id.textView);
//
//        String email = getIntent().getStringExtra("email");
//        textView.setText(email);

        bulldogList = (ListView) findViewById(R.id.bulldog_list);

        ArrayList<Bulldog> bulldogs = new ArrayList<Bulldog>();
        Bulldog bulldog1 = new Bulldog();
        bulldog1.setName("Porterhouse");
        bulldog1.setAge("9");

        Bulldog bulldog2 = new Bulldog();
        bulldog2.setName("Drake");
        bulldog2.setAge("2");

        bulldogs.add(bulldog1);
        bulldogs.add(bulldog2);

        final BulldogArrayAdapter adapter = new BulldogArrayAdapter(this, bulldogs);
        bulldogList.setAdapter(adapter);

        bulldogList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                final Bulldog bulldog = (Bulldog) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(view.getContext(), BulldogActivity.class);
                intent.putExtra("bulldog",(Serializable) bulldog);
                startActivity(intent);
            }
        });

    }
}
