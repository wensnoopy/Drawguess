package com.example.wensnoopy.drawguess;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, DrawingScreen {

    private DrawingView drawView;
    private ImageButton currPaint, newBtn, saveBtn, eraserBtn;
    private String imgSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (DrawingView)findViewById(R.id.drawing);
        //Drawable d = Drawable.createFromPath("content://media/external/images/media/40");
        //drawView.setBackground(d);
        //LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        //currPaint = (ImageButton)paintLayout.getChildAt(0);
        //currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        //drawingMgr = (DrawingManager) DrawingManager.getInstance();
        //drawingMgr.init(getApplicationContext(), drawView);

        newBtn = (ImageButton)findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);
        saveBtn = (ImageButton)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);
        eraserBtn = (ImageButton)findViewById(R.id.eraser_btn);
        eraserBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        //respond to clicks
        if(view.getId()==R.id.save_btn){
            //save drawing
            //drawingMgr.updateScreen();
            //saveScreen();
        }
        else if(view.getId() == R.id.eraser_btn){ ///* updateScreen
            byte[] imgByte = getPictureByteArray();
            Log.i("test", "imgByte: " + imgByte);
            updateScreen(imgByte);
        }
        else if(view.getId()==R.id.new_btn){
            //new button
            //drawingMgr.createScreen();
            createScreen();
        }
        //else if(view.getId()==R.id.eraser_btn) {
            //drawingMgr.deleteScreen();
        //    deleteScreen();
        //}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    public void paintClicked(View view){
        //use chosen color
        if(view!=currPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;
        }

    }*/



    @Override
    public void createScreen() {
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("New drawing");
        newDialog.setMessage("Start new drawing ?");
        newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                drawView.startNew();
                drawView.setBackgroundColor(0xffffffff);
                dialog.dismiss();
            }
        });
        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        newDialog.show();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void updateScreen(final byte[] imgByte) {
        /*
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Save drawing");
        saveDialog.setMessage("Save drawing to device Gallery?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int which){
                //save drawing
                drawView.setDrawingCacheEnabled(true);

                Drawable image = null;
                image =  new BitmapDrawable(BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length));
                Log.i("test", "image: " + image);
                //Drawable d = Drawable.createFromPath(imgSaved);
                //Log.i("test", "d: " + d);
                drawView.setBackground(image);

                imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), drawView.getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");

                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }

            }
        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        saveDialog.show();
        */

        Drawable image =  new BitmapDrawable(BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length));
        Log.i("test", "image: " + image);

        drawView.setBackground(image);
        drawView.destroyDrawingCache();
    }

    @Override
    public byte[] getPictureByteArray() {
        drawView.setDrawingCacheEnabled(true);
        Bitmap tmp = drawView.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        tmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}

