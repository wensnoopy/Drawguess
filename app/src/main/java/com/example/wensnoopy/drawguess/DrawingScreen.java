package com.example.wensnoopy.drawguess;

import android.content.Context;
import android.view.View;

/**
 * Created by wensnoopy on 15/1/7.
 */
public interface DrawingScreen {

    //public DrawingScreen getInstance();
    public void createScreen(DrawingView view);
    public void updateScreen(DrawingView view, byte[] imgByte);
    public byte[] getPictureByteArray(DrawingView view);

    //void init(Context , DrawingView );
}
