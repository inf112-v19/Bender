package inf112.skeleton.app.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {     // call with ReadFile lesFil = new ReadFile(); ReadFile.FileRead();

    public ArrayList<String> fileRead(String fileName) throws IOException {
        ArrayList<String> linesInFile = new ArrayList<String>();
        FileReader fileReader = new FileReader(fileName);

        try (
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                linesInFile.add(line);
            }
            return linesInFile;
        }
    }
}
