package com.willard5991.beautifulbulldogs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;

public class NewBulldogActivity extends AppCompatActivity {

    private ImageButton newImage;
    private EditText newName;
    private EditText newAge;
    private Button saveButton;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bulldog);

        newImage = (ImageButton) findViewById(R.id.new_image);
        newName = (EditText) findViewById(R.id.new_name);
        newAge = (EditText) findViewById(R.id.new_age);
        saveButton = (Button) findViewById(R.id.save_button);

        realm = Realm.getDefaultInstance();

        newImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, 1);
                    }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (!newName.getText().toString().matches("")
                        && !newAge.getText().toString().matches("")
                        && newImage.getDrawable() != null) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Bulldog bulldog = new Bulldog();
                            bulldog.setAge(newAge.getText().toString());
                            bulldog.setName(newName.getText().toString());
                            bulldog.setId(realm.where(Bulldog.class).findAllSorted("id").last().getId() + 1);
                            BitmapDrawable image = (BitmapDrawable) newImage.getDrawable();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            image.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageInBytes = baos.toByteArray();
                            bulldog.setImage(imageInBytes);

                            realm.copyToRealm(bulldog);
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            newImage.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Close the Realm instance.
        realm.close();
    }
}
