import java.io.IOException;
import java.util.*;

public class NaiveBayesClassifier {
    private double priorYes;
    private double priorNo;
    FileProcessor myDataSet = new FileProcessor("property_data.csv");
    private List<String[]> tableRows = myDataSet.readFile();;

    // AAAAGH
    private Map<String, Map<String, Integer>> features = new LinkedHashMap<>();
    String[] featureColumns = {"Grass", "Concrete",
            "Double", "Single",
            "House", "Apartment",
            "Fixed", "Temporary"};

    public NaiveBayesClassifier() throws IOException {
        genFreqTable(tableRows);
        getTotalsFreq(tableRows);

        printHashMap(features);

        //All used just for testing my Map
        //predict();
        //System.out.println(features.keySet());
        //System.out.println(features.get("Grass").get("No"));
    }

    public String predict(String gardenType, String bedType, String propertyType, String leaseType){
        String[] featureType = {gardenType, bedType, propertyType, leaseType};
        priorYes = (double) (features.get("Total").get("Yes"))
                / ((features.get("Total").get("Yes")) + (features.get("Total").get("No")));
        priorNo = (double) (features.get("Total").get("No"))
                / ((features.get("Total").get("Yes")) + (features.get("Total").get("No")));

        double probYes = (double) (features.get(gardenType).get("Yes")) / (features.get("Total").get("Yes"));
        probYes *= (double) (features.get(bedType).get("Yes")) / (features.get("Total").get("Yes"));
        probYes *= (double) (features.get(propertyType).get("Yes")) / (features.get("Total").get("Yes"));
        probYes *= (double) (features.get(leaseType).get("Yes")) / (features.get("Total").get("Yes"));
        probYes *= priorYes;

        double probNo = (double) (features.get(gardenType).get("No")) / (features.get("Total").get("No"));
        probNo *= (double) (features.get(bedType).get("No")) / (features.get("Total").get("No"));
        probNo *= (double) (features.get(propertyType).get("No")) / (features.get("Total").get("No"));
        probNo *= (double) (features.get(leaseType).get("No")) / (features.get("Total").get("No"));
        probNo *= priorNo;

        System.out.println("Probability Yes: " + probYes);
        System.out.println("Probability No: " + probNo);
        return probYes > probNo ? "Yes" : "No";
    }

    public void genFreqTable(List<String[]> rows){

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
        //printHashMap(features);

    }

    public void getTotalsFreq(List<String[]> rows){
        for (String column : featureColumns){
            Map<String, Integer> counts = new HashMap<>();
            for (String[] row : rows) {
                if(row[4].equals("Yes")){
                    counts.put("Yes", counts.getOrDefault("Yes", 0)+1);
                }
                else if (row[4].equals("No")){
                    counts.put("No", counts.getOrDefault("No", 0)+1);
                }
            }
            features.put("Total", counts);
        }
    }

    public void printHashMap(Map<String, Map<String, Integer>> map) {
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
