
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

    public HashMap<String, Map<String, Integer>> readFile(){
        HashMap<String, Map<String, Integer>> features = new HashMap<>();

        List<String[]> rows = new ArrayList<>();

        String[] featureColumnsData = {"Grass", "Concrete",
                "Double", "Single",
                "House", "Apartment",
                "Fixed", "Temporary"};

        String[] featureColumns = {"GardenType", "BedSize", "PropertyType", "LeaseType", "PropertyIsRented"};

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
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
        }

        //Just for testing trying to make sure I can separate all the rows properly
        for (String[] row : rows) {
            System.out.println(Arrays.toString(row));
        }

        int i = 0;
        boolean sameCol = true;
        for (String column : featureColumnsData){
            Map<String, Integer> counts = new HashMap<>();
            for (String[] row : rows) {
                if(row[4].equals("Yes") && (row[i].equals(column))){
                    counts.put("Yes", counts.getOrDefault("Yes", 0)+1);
                }
                else if (row[4].equals("No") && (row[i].equals(column))){
                    counts.put("No", counts.getOrDefault("No", 0)+1);
                }
            }
            features.put(column, counts);
            if (sameCol){
                sameCol = false;
            }
            else{
                sameCol = true;
                i++;
            }
        }

        return features;
    }


    public void printHashMap(HashMap<String, Map<String, Integer>> map) {
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            String key = entry.getKey();
            Map<String, Integer> innerMap = entry.getValue();

            System.out.println("Key: " + key);
            for (Map.Entry<String, Integer> innerEntry : innerMap.entrySet()) {
                System.out.println("    " + innerEntry.getKey() + ": " + innerEntry.getValue());
            }
        }
    }


    public void writeToFile(String input) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter pw = new PrintWriter((fw));

        pw.println(input);

        pw.close();
    }

}