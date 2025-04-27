import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveBayesClassifier {
    private double priorYes;
    private double priorNo;
    // AAAAGH
    private HashMap<String, Map<String, Integer>> features = new HashMap<>();
    String[] featureColumns = {"Grass", "Concrete",
            "Double", "Single",
            "House", "Apartment",
            "Fixed", "Temporary"};

    public NaiveBayesClassifier() throws IOException {
        FileProcessor myDataSet = new FileProcessor("property_data.csv");
        genHashMap(myDataSet.readFile());
    }

    public void genHashMap(List<String[]> rows){
        int i = 0;
        boolean sameCol = true;

        for (String column : featureColumns){
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
        //Test to see how my hashmap is looking
        printHashMap(features);

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



}
