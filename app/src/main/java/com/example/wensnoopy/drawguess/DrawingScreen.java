package com.example.wensnoopy.drawguess;

/**
 * Created by wensnoopy on 15/1/7.
 */
public interface DrawingScreen {

    public DrawingScreen getInstance();
    public void createScreen();
    public void updateScreen();
    public void deleteScreen();
    public <T extends Object> T serializeScreen();

}
