# Test_20200319_Menu2

--使用xml檔建立menu。(see setup xml for menu.txt)

-- MainActivity 設定:

--建立 一個 textView 及 三個 imageView 物件。

--imageView 物件先不要顯示。


-- 在 Activity 中使用選單，必須使用 MenuInflater.inflate() 擴大選單資源 (將 XML 轉換成可程式化的物件)。 
 如要指定 Activity 的選項選單，可覆寫 onCreateOptionsMenu() (片段會提供自己的 onCreateOptionsMenu() 回呼)。
 在這種方法中，您可以將選單資源(在 XML  中完成定義) 擴大回呼中提供的 Menu。

-- @Override onOptionsItemSelected()方法，並使用getItemId() 方法控制 menu中每個 item 的處理。 

   -- switch(item.getItemId()){}

   -- 選到 mario 及 sonic 則 textView 及 第一個 imageView 物件會顯示相對應的文字與圖片。

   -- 選到 reset 則 textView 回復顯示原本的文字，三個 imageView 物件回復原本沒有顯示圖片的狀態。

   -- 選到 dialog_Radio 則 textView 顯示Select Picture，以及呼叫自建的一般方法showDialog_radio()。
      --建立一般方法 private void showDialog_radio()，於其內部建立AlertDialog.Builder 物件。
      --使用AlertDialog.Builder 物件設定Title，Icon。
      --宣告屬性: private String pictureName[] = {"Pic-1", "Pic-2", "Pic-3"};
      --使用AlertDialog.Builder物件的方法 setSingleChoiceItems()，設定多選一，see dialog.txt。

        builder.setSingleChoiceItems(pictureName, 0, new DialogInterface.OnClickListener() {
           @Override
            public void onClick(DialogInterface dialog, int which){
        }
       });

      --在 onClick() 內，以which判斷是哪一個被選到，再進行相對應的處理。
      --建立 builder.setNegativeButton 去 dismiss Dialog。
      --builder.create().show() 顯示 Dialog。


   --選到 dialog_Radio 則 textView 顯示Select Picture，以及呼叫自建的一般方法showDialog_radio()。
      --建立一般方法 private void showDialog_radio()，於其內部建立AlertDialog.Builder 物件。
      --使用AlertDialog.Builder 物件設定Title，Icon。
      --宣告屬性:private boolean[] pictureCheck = new boolean[3];
      --用 for-loop 將pictureCheck[]各元素的內容預設成false。
      --使用AlertDialog.Builder物件的方法  setMultiChoiceItems，設定多選，see dialog.txt。

        builder.setMultiChoiceItems(pictureName, pictureCheck, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

              pictureCheck[which] = isChecked;
            }
        });

      --在onClick方法內，將 pictureCheck[which] = isChecked，如上。
      --建立 builder.setPositiveButton()方法， 用 StringBuilder 建立字串方式 ，讓多選後的結果於textViewTitle顯示所選的選項名稱，
        及 用 判斷式判斷哪一個被選到(為真)，再進行相對應的處理。
      
  
----------------------------------------------------------------------------------------------
package com.example.menu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Context context;
    private TextView textViewTitle;
    private ImageView imageViewPic1, imageViewPic2, imageViewPic3;

    private String pictureName[] = {"Pic-1", "Pic-2", "Pic-3"};

    private final String TAG = "main";

    private boolean[] pictureCheck = new boolean[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context =  this;

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
                textViewTitle.setText("Mario game is enabled.");
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
                textViewTitle.setText("Select Picture :");
                showDialog_radio();

                break;

            case R.id.menu_dialogCheck:
                textViewTitle.setText("Select CheckBox :");
                showDialog_checkbox();

                break;
        }

        return super.onOptionsItemSelected(item);
    }


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


    private void showDialog_radio() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select one picture");
        builder.setIcon(android.R.drawable.ic_dialog_info);

        // 0 是 default值
        builder.setSingleChoiceItems(pictureName, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d(TAG, "which = " + which);

                textViewTitle.append(pictureName[which]);

                if(which == 0) {
                    imageViewPic1.setImageResource(R.drawable.img_1);
                    imageViewPic1.setVisibility(View.VISIBLE);
                    imageViewPic2.setVisibility(View.INVISIBLE);
                    imageViewPic3.setVisibility(View.INVISIBLE);

                }else if(which == 1) {
                    imageViewPic2.setImageResource(R.drawable.img_2);
                    imageViewPic2.setVisibility(View.VISIBLE);
                    imageViewPic1.setVisibility(View.INVISIBLE);
                    imageViewPic3.setVisibility(View.INVISIBLE);


                }else if(which == 2) {
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

    }


} // main

----------------------------------------------------------------------------------------------
