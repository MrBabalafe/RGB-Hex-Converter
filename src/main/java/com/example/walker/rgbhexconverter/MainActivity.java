package com.example.walker.rgbhexconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rgb_to_hex = (Button) findViewById(R.id.rgbToHex_btn);
        Button hex_to_rgb = (Button) findViewById(R.id.hexToRGB_btn);

        rgb_to_hex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RGBToHexActivity.class);
                startActivity(intent);
            }
        });

        hex_to_rgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HexToRGBActivity.class);
                startActivity(intent);
            }
        });
    }
}
