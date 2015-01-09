package com.example.wensnoopy.drawguess;

import android.content.Context;
import android.view.View;

/**
 * Created by wensnoopy on 15/1/7.
 */
public interface DrawingScreen {

    //public DrawingScreen getInstance();
    public void createScreen();
    public void updateScreen(byte[] imgByte);
    public byte[] getPictureByteArray();

    //void init(Context , DrawingView );
}
