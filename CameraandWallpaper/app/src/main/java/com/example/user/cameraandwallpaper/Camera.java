package com.example.user.cameraandwallpaper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class Camera extends Activity implements View.OnClickListener{

    ImageButton ib;
    Button b;
    ImageView iv;
    Intent i;
    final static int cameraData = 0;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initilize();
        InputStream is =getResources().openRawResource(R.drawable.splash);
        bmp= BitmapFactory.decodeStream(is);
    }

    private void initilize(){
        iv = (ImageView)findViewById(R.id.ivReturnedPic);
        ib = (ImageButton)findViewById(R.id.takePic);
        b = (Button)findViewById(R.id.setWall);
        b.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takePic:
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraData);
                break;
        }

        switch (v.getId()){
            case R.id.setWall:
                try{
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e){
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            bmp = (Bitmap)extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }
}
