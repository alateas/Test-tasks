import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {

    public ArrayList<String> getStrings() {
        File file = new File("./input.txt");
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        DataInputStream dataInputStream = null;
        ArrayList<String> strings = new ArrayList<String>();

        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            dataInputStream = new DataInputStream(bufferedInputStream);
            while (dataInputStream.available() != 0) {
                strings.add(dataInputStream.readLine());
            }

            fileInputStream.close();
            bufferedInputStream.close();
            dataInputStream.close();

            return strings;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}