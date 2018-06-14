/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



/**
 *
 * @author LolaA
 */
public enum Command {
    
    CLICK_LEFT, CLICK_RIGHT, CLICK_CENTER,
    PRESS_RIGHT, PRESS_LEFT, PRESS_CENTER,
    RELEASE_RIGHT,RELEASE_LEFT,RELEASE_CENTER, 
    MOVE, MOVE_UP, MOVE_DOWN, MOVE_RIGHT, MOVE_LEFT,MOVE_NORTHEAST, MOVE_NORTHWEST, MOVE_SOUTHEAST,MOVE_SOUTHWEST, 
    SCROLL_UP, SCROLL_DOWN,
    TYPE_KEY, PRESS_KEY, RELEASE_KEY, ERROR, STOP;
    
    private int mouseAbsolutePositionX;
    private int mouseAbsolutePositionY;
    private int relativeMovement;
    private int keyCode1=-1;
    private int keyCode2= -1;
    private int keyCode3= -1;
    private int keyCode4= -1;
    
    
    
    public void setMouseAbsolutePosition(int x, int y){
        mouseAbsolutePositionX=x;
        mouseAbsolutePositionY=y;
    }
    
    public int getMouseXPosition(){
        return mouseAbsolutePositionX;
    }
    
    public int getMouseYPosition(){
        return mouseAbsolutePositionY;
    }
    
    public void setRelativeMovement(int i){
        relativeMovement=i;
    }
    public int getRelativeMovement(){
        return relativeMovement;
    }
    
    public void setKey1(int key){
        keyCode1=key;
    }
    
    public int getKey1(){
        return keyCode1;
    }
     public void setKey2(int key){
        keyCode2=key;
    }
    
    public int getKey2(){
        return keyCode2;
    }
     public void setKey3(int key){
        keyCode3=key;
    }
    
    public int getKey3(){
        return keyCode3;
    }
     public void setKey4(int key){
        keyCode4=key;
    }
    
    public int getKey4(){
        return keyCode4;
    }
    
 }
