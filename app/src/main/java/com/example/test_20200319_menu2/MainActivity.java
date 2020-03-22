package com.example.test_20200319_menu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private ImageView imageViewPic1, imageViewPic2, imageViewPic3;
    private Context context;
    private String pictureName[] = {"Pic-1", "Pic-2", "Pic-3"};
    private boolean[] pictureCheck = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        textViewTitle = (TextView) findViewById(R.id.textView_title);

        imageViewPic1 = (ImageView) findViewById(R.id.imageView_pic1);
        imageViewPic2 = (ImageView) findViewById(R.id.imageView_pic2);
        imageViewPic3 = (ImageView) findViewById(R.id.imageView_pic3);

        imageViewPic1.setVisibility(View.INVISIBLE);
        imageViewPic2.setVisibility(View.INVISIBLE);
        imageViewPic3.setVisibility(View.INVISIBLE);

    }// onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.setup, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_mario:
                textViewTitle.setText("Msario game is enabled.");
                imageViewPic1.setImageResource(R.drawable.mario);
                imageViewPic1.setVisibility(View.VISIBLE);
                imageViewPic2.setVisibility(View.INVISIBLE);
                imageViewPic3.setVisibility(View.INVISIBLE);
                break;

            case R.id.menu_sonic:
                textViewTitle.setText("Sonic game is enabled.");
                imageViewPic1.setImageResource(R.drawable.sonic);
                imageViewPic1.setVisibility(View.VISIBLE);
                imageViewPic2.setVisibility(View.INVISIBLE);
                imageViewPic3.setVisibility(View.INVISIBLE);
                break;

            case R.id.menu_reset:
                textViewTitle.setText("Game Selection");
                imageViewPic1.setVisibility(View.INVISIBLE);
                imageViewPic2.setVisibility(View.INVISIBLE);
                imageViewPic3.setVisibility(View.INVISIBLE);
                break;

            case R.id.menu_dialogRadio:
                textViewTitle.setText("Select Picture");
                showDialog_radio();

                break;

            case R.id.menu_dialogCheck:
                textViewTitle.setText("Select CheckBox :");
                showDialog_checkbox();

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showDialog_radio() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Selet pictures");
        builder.setIcon(android.R.drawable.ic_dialog_info);

        builder.setSingleChoiceItems(pictureName, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                textViewTitle.setText(pictureName[which]);

                if(which == 0){
                    imageViewPic1.setImageResource(R.drawable.img_1);
                    imageViewPic1.setVisibility(View.VISIBLE);
                    imageViewPic2.setVisibility(View.INVISIBLE);
                    imageViewPic3.setVisibility(View.INVISIBLE);
                }else if(which == 1){
                    imageViewPic2.setImageResource(R.drawable.img_2);
                    imageViewPic2.setVisibility(View.VISIBLE);
                    imageViewPic1.setVisibility(View.INVISIBLE);
                    imageViewPic3.setVisibility(View.INVISIBLE);
                }else if(which == 2){
                    imageViewPic3.setImageResource(R.drawable.img_3);
                    imageViewPic3.setVisibility(View.VISIBLE);
                    imageViewPic1.setVisibility(View.INVISIBLE);
                    imageViewPic2.setVisibility(View.INVISIBLE);
                }

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();

    }//  showDialog_radio()


    private void showDialog_checkbox() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Select pictures");

        builder.setIcon(android.R.drawable.ic_dialog_info);

        for(int i=0; i<pictureCheck.length; i++) {
            pictureCheck[i] = false;
        }

        builder.setMultiChoiceItems(pictureName, pictureCheck, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                pictureCheck[which] = isChecked;
            }
        });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                StringBuilder sb = new StringBuilder();

                for(int i=0; i<pictureCheck.length; i++) {

                    if(pictureCheck[i]) {
                        sb.append(pictureName[i] + ",");
                    }
                }

                textViewTitle.append(sb.toString());

                if(pictureCheck[0]) {
                    imageViewPic1.setImageResource(R.drawable.img_1);
                    imageViewPic1.setVisibility(View.VISIBLE);
                }else {
                    imageViewPic1.setVisibility(View.INVISIBLE);
                }


                if(pictureCheck[1]) {
                    imageViewPic2.setImageResource(R.drawable.img_2);
                    imageViewPic2.setVisibility(View.VISIBLE);
                }else {
                    imageViewPic2.setVisibility(View.INVISIBLE);
                }

                if(pictureCheck[2]) {
                    imageViewPic3.setImageResource(R.drawable.img_3);
                    imageViewPic3.setVisibility(View.VISIBLE);
                }else {
                    imageViewPic3.setVisibility(View.INVISIBLE);
                }


                dialog.dismiss();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();

    }


}// class Main
