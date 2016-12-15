package com.example.dione.freehanddrawing;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button save;
    InkView ink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
        save.setOnClickListener(this);
    }

    private void initDrawer() {
        save = (Button) findViewById(R.id.save);
        ink = (InkView) findViewById(R.id.ink);
        ink.setFlags(InkView.FLAG_INTERPOLATION | InkView.FLAG_RESPONSIVE_WIDTH);
        ink.setColor(ContextCompat.getColor(this, android.R.color.white));
        ink.setMinStrokeWidth(1.5f);
        ink.setMaxStrokeWidth(6f);
        ink.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black)); 
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                Bitmap drawing = ink.getBitmap();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                drawing.compress(Bitmap.CompressFormat.JPEG, 90, bos);
                byte[] imageData = bos.toByteArray();
                String encodedSign = Base64.encodeToString(imageData,
                        Base64.DEFAULT);
                Log.d("base64sig", encodedSign);
                break;
        }
    }
}
