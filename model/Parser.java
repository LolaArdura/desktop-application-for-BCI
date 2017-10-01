/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.KeyEvent;



/**
 *
 * @author LolaA
 */
public class Parser {   
   private String[] splitCommand;
   
    public Command parse(String command){
        command = sanitize(command);
        splitCommand= command.split(" +");
        
     try{   
        if(splitCommand[0].equals("click")){
            if(splitCommand[1].equals("left"))return Command.CLICK_LEFT;
            if(splitCommand[1].equals("right"))return Command.CLICK_RIGHT;
            if(splitCommand[1].equals("center"))return Command.CLICK_CENTER;
        }
        
        if(splitCommand[0].equals("press")){
            if(splitCommand[1].equals("right"))return Command.PRESS_RIGHT;
            if(splitCommand[1].equals("left"))return Command.PRESS_LEFT;
            if(splitCommand[1].equals("center")) return Command.PRESS_CENTER;
            if(splitCommand[1].equals("key")) {
                //In the beginning nonvalid keycodes. The previous ones get deleted
                Command.PRESS_KEY.setKey1(-1);
                Command.PRESS_KEY.setKey2(-1);
                Command.PRESS_KEY.setKey3(-1);
                Command.PRESS_KEY.setKey4(-1);
                int keyCode;
                //If the first word is a modifier it may precede another modifier or key
                if(splitCommand[2].equals("alt")|| splitCommand[2].equals("shift")|| splitCommand[2].equals("control")){
                  if(splitCommand.length>=4){
                    if(splitCommand[3].equals("shift")||splitCommand[3].equals("control")||splitCommand[3].equals("alt")){
                      if(splitCommand.length>=5){
                        if(splitCommand[4].equals("shift")|| splitCommand[4].equals("control")||splitCommand[4].equals("alt")){
                           if(splitCommand.length>=6){
                              keyCode=keyToKeyCode(5);
                              if(keyCode==-1) return Command.ERROR;
                              Command.PRESS_KEY.setKey4(keyCode);
                           }
                        }
                        keyCode=keyToKeyCode(4);
                        if(keyCode==-1)return Command.ERROR;
                        Command.PRESS_KEY.setKey3(keyCode);
                        }
                    }
                    keyCode=keyToKeyCode(3);
                    if(keyCode==-1) return Command.ERROR;
                    Command.PRESS_KEY.setKey2(keyCode);
                  }
                  keyCode=keyToKeyCode(2);
                  if(keyCode==-1) return Command.ERROR;
                  Command.PRESS_KEY.setKey1(keyCode);
                  return Command.PRESS_KEY;
                }
                else{
                keyCode = keyToKeyCode(2);
                if(keyCode==-1) return Command.ERROR;
                else{
                    Command.PRESS_KEY.setKey1(keyCode);
                    return Command.PRESS_KEY;
                }
                }
            }
        }
        
        if(splitCommand[0].equals("release")){
            if(splitCommand[1].equals("right"))return Command.RELEASE_RIGHT;
            if(splitCommand[1].equals("left"))return Command.RELEASE_LEFT;
            if(splitCommand[1].equals("center")) return Command.RELEASE_CENTER;
            if(splitCommand[1].equals("key")){
                //In the beginning nonvalid keycodes. The previous ones get deleted
                Command.RELEASE_KEY.setKey1(-1);
                Command.RELEASE_KEY.setKey2(-1);
                Command.RELEASE_KEY.setKey3(-1);
                Command.RELEASE_KEY.setKey4(-1);
                int keyCode;
                if(splitCommand[2].equals("alt")|| splitCommand[2].equals("shift")|| splitCommand[2].equals("control")){
                  if(splitCommand.length>=4){
                    if(splitCommand[3].equals("shift")||splitCommand[3].equals("control")||splitCommand[3].equals("alt")){
                      if(splitCommand.length>=5){
                        if(splitCommand[4].equals("shift")|| splitCommand[4].equals("control")||splitCommand[4].equals("alt")){
                          if(splitCommand.length>=6){
                             keyCode=keyToKeyCode(5);
                             if(keyCode==-1) return Command.ERROR;
                             Command.RELEASE_KEY.setKey4(keyCode);
                             }
                        }
                        keyCode=keyToKeyCode(4);
                        if(keyCode==-1)return Command.ERROR;
                        Command.RELEASE_KEY.setKey3(keyCode);
                      }
                    }
                    keyCode=keyToKeyCode(3);
                    if(keyCode==-1) return Command.ERROR;
                    Command.RELEASE_KEY.setKey2(keyCode);
                  }
                  keyCode=keyToKeyCode(2);
                  if(keyCode==-1) return Command.ERROR;
                  Command.RELEASE_KEY.setKey1(keyCode);
                  return Command.RELEASE_KEY;
                }
                else{
                keyCode = keyToKeyCode(2);
                if(keyCode==-1) return Command.ERROR;
                
                Command.RELEASE_KEY.setKey1(keyCode);
                return Command.RELEASE_KEY;
                }
            }
        }
        
        if(splitCommand[0].equals("move")){
            if(splitCommand[1].equals("relative")){
                int relativeMovement;
                try{
                relativeMovement= Integer.parseInt(splitCommand[2]);
                if(relativeMovement<0) return Command.ERROR;
                }catch(NumberFormatException ex){
                    ex.printStackTrace();
                    return Command.ERROR;
                }
                if(splitCommand[3].equals("up")){
                    Command.MOVE_UP.setRelativeMovement(relativeMovement);
                    return Command.MOVE_UP;
                }
                if(splitCommand[3].equals("down")){
                    Command.MOVE_DOWN.setRelativeMovement(relativeMovement);
                    return Command.MOVE_DOWN;
                }
                if(splitCommand[3].equals("right")){
                    Command.MOVE_RIGHT.setRelativeMovement(relativeMovement);
                    return Command.MOVE_RIGHT;
                }
                if(splitCommand[3].equals("left")){
                    Command.MOVE_LEFT.setRelativeMovement(relativeMovement);
                    return Command.MOVE_LEFT;
                }
                if(splitCommand[3].equals("northeast")){
                    Command.MOVE_NORTHEAST.setRelativeMovement(relativeMovement);
                    return Command.MOVE_NORTHEAST;
                }
                if(splitCommand[3].equals("northwest")){
                    Command.MOVE_NORTHWEST.setRelativeMovement(relativeMovement);
                    return Command.MOVE_NORTHWEST;
                }
                if(splitCommand[3].equals("southeast")){
                    Command.MOVE_SOUTHEAST.setRelativeMovement(relativeMovement);
                    return Command.MOVE_SOUTHEAST;
                }
                if(splitCommand[3].equals("southwest")){
                    Command.MOVE_SOUTHWEST.setRelativeMovement(relativeMovement);
                    return Command.MOVE_SOUTHWEST;
                }
            }
            try{
                int x=Integer.parseInt(splitCommand[1]);
                int y=Integer.parseInt(splitCommand[2]);
                if(x<0||y<0) return Command.ERROR;
                Command.MOVE.setMouseAbsolutePosition(x, y);
                return Command.MOVE;
            }catch(NumberFormatException ex){
               ex.printStackTrace();
               return Command.ERROR;
            }
        }
        
        
        if(splitCommand[0].equals("wheel")){
            if(splitCommand[1].equals("scroll")){
                int relativeMovement;
                try{
                    relativeMovement=Integer.parseInt(splitCommand[2]);
                }catch(NumberFormatException ex){
                    ex.printStackTrace();
                    return Command.ERROR;
                }
                
                if(splitCommand[3].equals("up")){
                    Command.SCROLL_UP.setRelativeMovement(relativeMovement); 
                    return Command.SCROLL_UP;
                }
                if(splitCommand[3].equals("down")){
                    Command.SCROLL_DOWN.setRelativeMovement(relativeMovement);
                    return Command.SCROLL_DOWN;
                }
            }
        }
        
        if(splitCommand[0].equals("type")){
            if(splitCommand[1].equals("key")){
                //In the beginning nonvalid keycodes.The previous ones get deleted
                Command.TYPE_KEY.setKey1(-1); 
                Command.TYPE_KEY.setKey2(-1);
                Command.TYPE_KEY.setKey3(-1);
                Command.TYPE_KEY.setKey4(-1);
                int keyCode;
                if(splitCommand[2].equals("alt")|| splitCommand[2].equals("shift")|| splitCommand[2].equals("control")){
                  if(splitCommand.length>=4){
                    if(splitCommand[3].equals("shift")||splitCommand[3].equals("control")||splitCommand[3].equals("alt")){
                      if(splitCommand.length>=5){
                       if(splitCommand[4].equals("shift")|| splitCommand[4].equals("control")||splitCommand[4].equals("alt")){
                          if(splitCommand.length>=6){
                             keyCode=keyToKeyCode(5);
                             if(keyCode==-1) return Command.ERROR;
                                Command.TYPE_KEY.setKey4(keyCode);
                             }
                          }
                         keyCode=keyToKeyCode(4);
                         if(keyCode==-1)return Command.ERROR;
                         Command.TYPE_KEY.setKey3(keyCode);
                         }
                    }
                    keyCode=keyToKeyCode(3);
                    if(keyCode==-1) return Command.ERROR;
                    Command.TYPE_KEY.setKey2(keyCode);
                  }
                  keyCode=keyToKeyCode(2);
                  if(keyCode==-1) return Command.ERROR;
                  Command.TYPE_KEY.setKey1(keyCode);
                  return Command.TYPE_KEY;
                }
                else{
                keyCode = keyToKeyCode(2);
                if(keyCode==-1) return Command.ERROR;
                else{
                    Command.TYPE_KEY.setKey1(keyCode);
                    return Command.TYPE_KEY;
                }
                }
            }
            }
         
        return Command.ERROR;
      }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
            return Command.ERROR;
     }
       
    }
     
  
   
    
    private String sanitize(String command) {
        String sanitizedCommand = command.toLowerCase().trim();
         
        return sanitizedCommand;
        
    }
    
    //This method converts the key into the corresponding keyCode
    private int keyToKeyCode(int arrayIndex){ 
         try{
            if(splitCommand[arrayIndex].length()== 1){ //This means that it is a letter, a number or any other char with
                                                       // a graphic representation in ASCII, however that doesn't mean that the
                                                       // character is on the primary keyboard
               int asciiCode=KeyEvent.getExtendedKeyCodeForChar(splitCommand[arrayIndex].charAt(0));
               return asciiCode; 
            }
            if(splitCommand[arrayIndex].equals("backspace")){
                return KeyEvent.VK_BACK_SPACE;
            }
            if(splitCommand[arrayIndex].equals("caps")){
                if(splitCommand[arrayIndex+1].equals("lock")){
                return KeyEvent.VK_CAPS_LOCK;
                }
            }
            if(splitCommand[arrayIndex].equals("context")){
                if(splitCommand[arrayIndex+1].equals("menu")){
                    return KeyEvent.VK_CONTEXT_MENU;
                }
            }
            if(splitCommand[arrayIndex].equals("delete")){
                return KeyEvent.VK_DELETE;
            }
            if(splitCommand[arrayIndex].equals("enter")){
                return KeyEvent.VK_ENTER;
            }
            if(splitCommand[arrayIndex].equals("escape")){
                return KeyEvent.VK_ESCAPE;
            }
            if(splitCommand[arrayIndex].equals("function")){
                int number;
                try{
                number=Integer.parseInt(splitCommand[arrayIndex+1]);
                }catch(NumberFormatException ex){
                    ex.printStackTrace();
                    return -1;//Since there are no negative keyCodes, this indicates ERROR
                }
                if(number>=1 && number<=12){
                //function keys' keyCodes are equal to the number+111
                return number+111;
                }
            }
            if(splitCommand[arrayIndex].equals("num")){
                if(splitCommand[arrayIndex+1].equals("lock")){
                    return KeyEvent.VK_NUM_LOCK;
                }
            }
            if(splitCommand[arrayIndex].equals("numpad")){
                int number;
                try{
                    number=Integer.parseInt(splitCommand[3]);
                }catch(NumberFormatException ex){
                    return -1; //Since there are no negative keyCodes, this indicates ERROR
                }
                if(number>=0&&number<=9){
                //KeyCode for numpad numbers is the number + 96
                return number+96;
                }
            }
            if(splitCommand[arrayIndex].equals("pause")){
                return KeyEvent.VK_PAUSE;
            }
            if(splitCommand[arrayIndex].equals("print")){
                if(splitCommand[arrayIndex+1].equals("screen")){
                    return KeyEvent.VK_PRINTSCREEN;
                }
            }
            if(splitCommand[arrayIndex].equals("space")){
                return KeyEvent.VK_SPACE;
            }
            if(splitCommand[arrayIndex].equals("tab")){
                return KeyEvent.VK_TAB;
            }
            if(splitCommand[arrayIndex].equals("windows")){
                return KeyEvent.VK_WINDOWS;
            }
                        
            if(splitCommand[arrayIndex].equals("alt")){
                return KeyEvent.VK_ALT;
            }
            if(splitCommand[arrayIndex].equals("control")){
                return KeyEvent.VK_CONTROL;
            }
            if(splitCommand[arrayIndex].equals("shift")){
                return KeyEvent.VK_SHIFT;
            }
                        
            if(splitCommand[arrayIndex].equals("down")){
                return KeyEvent.VK_DOWN;
            }
            if(splitCommand[arrayIndex].equals("end")){
                return KeyEvent.VK_END;
            }
            if(splitCommand[arrayIndex].equals("home")){
                return KeyEvent.VK_HOME;
            }
            if(splitCommand[arrayIndex].equals("insert")){
                return KeyEvent.VK_INSERT;
            }
            if(splitCommand[arrayIndex].equals("left")){
                return KeyEvent.VK_LEFT;
            }
            if(splitCommand[arrayIndex].equals("page")){
               if(splitCommand[arrayIndex+1].equals("down")){
                 return KeyEvent.VK_PAGE_DOWN;
               }
               if(splitCommand[arrayIndex].equals("up")){
                  return KeyEvent.VK_PAGE_UP;
               }
            }
            if(splitCommand[arrayIndex].equals("right")){
                return KeyEvent.VK_RIGHT;
            } 
            if(splitCommand[arrayIndex].equals("up")){
                return KeyEvent.VK_UP;
                
            }
            return -1;
         }catch (IndexOutOfBoundsException ex){
             ex.printStackTrace();
             return -1;
         }
   
    }

}