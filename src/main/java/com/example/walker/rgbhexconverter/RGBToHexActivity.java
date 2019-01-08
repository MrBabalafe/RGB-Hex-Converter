package com.example.walker.rgbhexconverter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RGBToHexActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgb_to_hex);
        setTitle("RGB to Hex");

        String redStr, greenStr, blueStr;
        String redHex, greenHex, blueHex;

        //Set up variables to access all items in the view
        final EditText redNum = (EditText) findViewById(R.id.redNumField);
        final EditText greenNum = (EditText) findViewById(R.id.greenNumField);
        final EditText blueNum = (EditText) findViewById(R.id.blueNumField);
        final Button convert = (Button) findViewById(R.id.RGBToHex_btn);
        final TextView response = (TextView) findViewById(R.id.finalHexCodeTextView);
        final Button coloredBtn = (Button) findViewById(R.id.RGBToHexFinalColor_btn);
        convert.setEnabled(false);

        //Check if the text in "Red" is okay.
        redNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if((editTextValid(redNum)) && (editTextValid(greenNum)) && (editTextValid(blueNum))) {
                    convert.setEnabled(true);
                } else {
                    convert.setEnabled(false);
                }
            }
        });

        //Check if the text in "Green" is okay.
        greenNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if((editTextValid(redNum)) && (editTextValid(greenNum)) && (editTextValid(blueNum))) {
                    convert.setEnabled(true);
                } else {
                    convert.setEnabled(false);
                }
            }
        });

        //Check if the text in "Blue" is okay.
        blueNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if((editTextValid(redNum)) && (editTextValid(greenNum)) && (editTextValid(blueNum))) {
                    convert.setEnabled(true);
                } else {
                    convert.setEnabled(false);
                }
            }
        });

        //What to do when convert button is pressed.
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hide the keyboard
                hideKeyboard(RGBToHexActivity.this);

                int red, green, blue;
                String redHex, greenHex, blueHex, finalHex;

                red = Integer.parseInt(redNum.getText().toString());
                green = Integer.parseInt(greenNum.getText().toString());
                blue = Integer.parseInt(blueNum.getText().toString());

                if(red <= 15) {
                   redHex = "0" + Integer.toHexString(red);
                } else {
                    redHex = Integer.toHexString(red);
                }

                if(green <= 15) {
                    greenHex = "0" + Integer.toHexString(green);
                } else {
                    greenHex = Integer.toHexString(green);
                }

                if(blue <= 15) {
                    blueHex = "0" + Integer.toHexString(blue);
                } else {
                    blueHex = Integer.toHexString(blue);
                }

                finalHex = redHex + greenHex + blueHex;
                finalHex = "#" + finalHex;
                response.setText(finalHex);
                coloredBtn.setBackgroundColor(Color.parseColor(finalHex));
                response.setVisibility(View.VISIBLE);
                coloredBtn.setVisibility(View.VISIBLE);
            }
        });

        coloredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hexCode = response.getText().toString();
                hexCode = hexCode.replace("#", "");
                String url = "http://www.color-hex.com/color/" + hexCode;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //Method to make sure that an EditText has a value and that value is 255 or less.
    public static boolean editTextValid(EditText text) {
        if(((text.getText().toString().length() > 0) && (Integer.parseInt(text.getText().toString()) > 255)) || (text.getText().toString().length() == 0)) {
            return false;
        } else {
            return true;
        }
    }
}
