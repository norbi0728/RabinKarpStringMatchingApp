package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReaderUtility {
    private static BufferedReader reader;

    public FileReaderUtility(File file) {
        try {
            this.reader = new BufferedReader(new FileReader(file));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String readFile(){
        String fileData = "";
        String newString = "";
        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                fileData += line;
                fileData += "\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < fileData.length() - 1; i++){
            newString += fileData.charAt(i);
        }
        return newString;
    }
}
