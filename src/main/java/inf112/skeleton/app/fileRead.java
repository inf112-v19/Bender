// ** PROJECT BENDER / ROBOrally 2019
// ** Created 1.feb 2019 Rune Thelen

package inf112.skeleton.app;
package io;
import java.io.*;
import java.util.ArrayList;

public class fileRead {

    FileInputStream fileStream = new FileInputStream("cardData.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));

    String strLine;
    ArrayList<String> linesInFile = new ArrayList<String>();

    while ((strLine = br.readLine()) != null)   {
        linesInFile.add(strLine);
    }
fileStream.close();
}
