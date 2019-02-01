package inf112.skeleton.app;
package io;
import java.io.*;

public class fileRead {

    public static void main(String[] args) throws Exception {
            FileReader fr = new FileReader("C:\\Users\\pankaj\\Desktop\\test.java");
            int i;
            while ((i=fr.read()) != -1){
                System.out.print((char) i);
            }
    }

}
