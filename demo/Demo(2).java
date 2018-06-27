/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;
import java.net.Socket;
import java.io.*;
import model.Reader;
/**
 *
 * @author LolaA
 */

/*This is a demo for windows where the notepad is opened, a small text is typed and then is closed withoud saving*/
public class Demo {
    public static void main (String[] args){
        Socket socket=null;
        PrintWriter writer=null;
        BufferedReader reader=null;
        
        try{
            Reader server= new Reader();
            server.start();
            socket=new Socket("localhost",9000);
            writer=new PrintWriter(socket.getOutputStream(),true);
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           
            
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
           
            
            writer.println("type key windows");
            writer.flush();
            Thread.sleep(500);
            writer.println("type key b");
            writer.flush();
            Thread.sleep(500);
            writer.println("type key l");
            writer.flush();
            Thread.sleep(500);
            writer.println("type key enter");
            writer.flush();
            Thread.sleep(500);
            
            writer.println("move 1070 85");
            writer.flush();
            Thread.sleep(500);
            writer.println("click right");
            writer.flush();
            Thread.sleep(500);
            
            /*writer.println("type key control alt 2");
            writer.flush();*/
            char[] notePad="\n\n\n                Thank you for your attention".toCharArray();
            for(int i=0;i<notePad.length;i++){
                if(notePad[i]=='\n'){
                    writer.println("type key enter");
                    writer.flush();
                }
                else{
                   if(notePad[i]==' ') {
                       writer.println("type key space");
                       writer.flush();
                   }
                   else{
                       writer.println("type key "+ notePad[i]);
                       writer.flush();
                   }
                }
               Thread.sleep(100);
            }
            
           /*writer.println("move 1150 85");
            writer.flush();
            Thread.sleep(500);
            writer.println("click right");
            writer.flush();
            Thread.sleep(500);
            writer.println("type key right");
            writer.flush();
            Thread.sleep(500);
            writer.println("type key enter");
            writer.flush();*/
            server.stop();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            if(writer!=null){
               try{
                   writer.flush();
                   writer.close();
               } catch(Exception ex){
                   ex.printStackTrace();
               }
            }
            if(reader!= null){
                try{
                    reader.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            if(socket!=null){
            try{
                socket.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            }
        }
    }
}
