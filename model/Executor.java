/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;


/**
 *
 * @author LolaA
 */
public abstract class Executor {
    
    private static Robot robot;
    
    static{
        try { 
            robot=new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean execute(Command command){
     try{  
        if(command.equals(Command.CLICK_RIGHT)){
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.CLICK_CENTER)){
            robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.CLICK_LEFT)){
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.PRESS_RIGHT)){
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.PRESS_CENTER)){
            robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.PRESS_LEFT)){
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.RELEASE_RIGHT)){
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.RELEASE_CENTER)){
            robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.RELEASE_LEFT)){
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            return true;
        }
        if(command.equals(Command.TYPE_KEY)){
            robot.keyPress(command.getKey1());
            if(command.getKey2()!= -1){ // If it's -1 it means that there is no more keys to press
                robot.keyPress(command.getKey2());
               if(command.getKey3()!=-1){
                   robot.keyPress(command.getKey3());
                   if(command.getKey4()!=-1){
                       robot.keyPress(command.getKey4());
                       robot.keyRelease(command.getKey4());
                   }
                   robot.keyRelease(command.getKey3());
               }
               robot.keyRelease(command.getKey2());
            }
            robot.keyRelease(command.getKey1());
            return true;
        }
        if(command.equals(Command.PRESS_KEY)){
            robot.keyPress(command.getKey1());
            return true;
        }
        if(command.equals(Command.RELEASE_KEY)){
            robot.keyRelease(command.getKey1());
            return true;
        }
        return false;
        
     }catch(IllegalArgumentException ex){
          ex.printStackTrace();
          return false;
      }
    }
    
    public static boolean relativeMove(Command command, int x){
        
        Point mouse=MouseInfo.getPointerInfo().getLocation();
        if(command.equals(Command.MOVE_UP)){
            robot.mouseMove(mouse.x, mouse.y-x);
            return true;
        }
        if(command.equals(Command.MOVE_DOWN)){
            robot.mouseMove(mouse.x, mouse.y+x);
            return true;
        }
        if(command.equals(Command.MOVE_RIGHT)){
            robot.mouseMove(mouse.x+x, mouse.y);
            return true;
        }
        if(command.equals(Command.MOVE_LEFT)){
            robot.mouseMove(mouse.x-x, mouse.y);
            return true;
        }
        if(command.equals(Command.MOVE_NORTHEAST)){
            robot.mouseMove((int)(mouse.x+(double)(x/Math.sqrt(2))), (int)(mouse.y-(double)(x/Math.sqrt(2))));
            return true;
        }
        if(command.equals(Command.MOVE_NORTHWEST)){
            robot.mouseMove((int)(mouse.x-(double)(x/Math.sqrt(2))), (int)(mouse.y-(double)(x/Math.sqrt(2))));
            return true;
        }
        if(command.equals(Command.MOVE_SOUTHEAST)){
            robot.mouseMove((int)(mouse.x+(double)(x/Math.sqrt(2))), (int)(mouse.y+(double)(x/Math.sqrt(2))));
            return true;
        }
        if(command.equals(Command.MOVE_SOUTHWEST)){
            robot.mouseMove((int)(mouse.x-(double)(x/Math.sqrt(2))), (int)(mouse.y+(double)(x/Math.sqrt(2))));
            return true;
        }
        if(command.equals(Command.SCROLL_UP)){
            //Negative integer in order to scroll up
            robot.mouseWheel(-x);
            return true;
        }
        if(command.equals(Command.SCROLL_DOWN)){
            robot.mouseWheel(x);
            return true;
        }
     return false;
        
    }
    
    public static boolean absoluteMove(Command command, int x, int y){
        if(command.equals(Command.MOVE)){
            robot.mouseMove(x, y);
            return true;
        }
      return false;
    }
}
