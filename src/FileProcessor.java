
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public HashMap<String, Map<String, Integer>> readFile(){
        HashMap<String, Map<String, Integer>> lines = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
        ArrayList<String> rows = new ArrayList<>();

        lines.put("Grass", counts);
        counts.put("Yes", counts.getOrDefault("Yes", 0)+1);
        try{
            myScanner = new Scanner(myFile);
            myScanner.useDelimiter(",");
            while(myScanner.hasNextLine()){
                rows.add(myScanner.next());
            }
            myScanner.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
        }
        System.out.println(rows);
        return lines;
    }



    public void writeToFile(String input) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter pw = new PrintWriter((fw));

        pw.println(input);

        pw.close();
    }

}