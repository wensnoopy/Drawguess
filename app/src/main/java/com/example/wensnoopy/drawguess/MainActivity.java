package com.example.wensnoopy.drawguess;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

    private AndroidDrawingScreen drawingScreen;
    private DrawingView drawView;
    private ImageButton newBtn, refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (DrawingView)findViewById(R.id.drawing);

        newBtn = (ImageButton)findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);
        refreshBtn = (ImageButton)findViewById(R.id.refresh_btn);
        refreshBtn.setOnClickListener(this);

        drawingScreen = AndroidDrawingScreen.getInstance();
        drawingScreen.init(getApplicationContext());

    }

    @Override
    public void onClick(View view){
        //respond to clicks
        if(view.getId() == R.id.refresh_btn){ ///* updateScreen
            byte[] imgByte = drawingScreen.getPictureByteArray(drawView);
            Log.i("test", "imgByte: " + imgByte);
            drawingScreen.updateScreen(drawView, imgByte);
        }
        else if(view.getId()==R.id.new_btn){
            //new button
            //drawingMgr.createScreen();
            drawingScreen.createScreen(drawView);
        }

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
}

