
import java.io.*;
import java.util.*;

public class FileProcessor {
    private String filename;
    private Scanner myScanner;
    private File myFile;


    public FileProcessor(String filename) throws IOException {
        setFilename(filename);
        connectFile();

    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void connectFile(){
        myFile = new File(filename);
    }

    public List<String[]> readFile() {
        List<String[]> rows = new ArrayList<>();

        try {
            myScanner = new Scanner(myFile);
            //skip my header line
            myScanner.nextLine();

            while (myScanner.hasNextLine()) {
                //take the first line of the csv
                String line = myScanner.nextLine();
                //split the line into each column by using the ',' as a delimiter
                String[] columns = line.split(",");
                String[] row = new String[5];
                for (int i = 0; i < 5; i++) {
                    row[i] = columns[i].trim();
                }
                //add each row into my rows arraylist (array of string arrays)
                rows.add(row);

            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }

        //Just for testing trying to make sure I can separate all the rows properly
        for (String[] row : rows) {
            System.out.println(Arrays.toString(row));
        }

        return rows;
    }



    public void writeToFile(String input) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter pw = new PrintWriter((fw));

        pw.println(input);

        pw.close();
    }

}