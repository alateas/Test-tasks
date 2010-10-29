import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> sortedStrings;
        ArrayList<String> unsortedStrings = new ArrayList<String>();

        FileReader fr = new FileReader();
        sortedStrings = fr.getStrings();

        unsortedStrings = (ArrayList)sortedStrings.clone();

        Collections.sort(sortedStrings);

        DbWorker db = new DbWorker();
        for (String str : sortedStrings) {
            db.insertRecord(unsortedStrings.indexOf(str)+1, str);
        }

        db.printRecords();
    }
}