package com.willard5991.beautifulbulldogs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;

public class BulldogActivity extends AppCompatActivity {

    private TextView bulldogName;
    private ImageView bulldogImage;
    private Button voteButton;
    private Spinner bulldogSpinner;
    private Realm realm;
    private User owner;
    private Bulldog bulldog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        bulldogName = (TextView) findViewById(R.id.bulldog_name);
        bulldogImage = (ImageView) findViewById(R.id.bulldog_image);
        voteButton = (Button) findViewById(R.id.bulldog_submit);
        bulldogSpinner = (Spinner) findViewById(R.id.bulldog_spinner);

        realm = Realm.getDefaultInstance();

        String id = (String) getIntent().getStringExtra("bulldog");
        String username = (String) getIntent().getStringExtra("username");
        owner = realm.where(User.class).equalTo("username",username).findFirst();

        bulldog = realm.where(Bulldog.class).equalTo("id",id).findFirst();

        if(bulldog.getImage()  != null){
            Bitmap bmp = BitmapFactory.decodeByteArray(bulldog.getImage(),0,bulldog.getImage().length);
            bulldogImage.setImageBitmap(bmp);
        }
        bulldogName.setText(bulldog.getName());

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bulldogSpinner.setAdapter(adapter);

        bulldogSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                     @Override
                                                     public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                                     }

                                                     @Override
                                                     public void onNothingSelected(AdapterView<?> adapterView) {

                                                     }
                                                 });

        voteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                realm.executeTransaction(new Realm.Transaction(){
                    @Override
                    public void execute(Realm realm){
                        Vote vote = new Vote();
                        vote.setOwner(owner);
                        vote.setRating(Integer.valueOf(bulldogSpinner.getSelectedItem().toString()));
                        bulldog.appendVote(vote);

                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Close the Realm instance.
        realm.close();
    }
}
