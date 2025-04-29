import java.io.IOException;
import java.util.*;

public class NaiveBayesClassifier {
    //Attributes of my class, struggled here with deciding on when to use what and where to use them

    //These are for the theorem like the actual calculation
    private double priorYes;
    private double priorNo;
    private double probYes;
    private double probNo;

    private double correctCount;

    //Instance of my StratifiedSplit class to return a list of string arrays that have been selected to
    //ensure even stratification of results
    StratifiedSplit splitData = new StratifiedSplit();

    //Instance of my FileProcessor so that I can read the csv file
    FileProcessor myDataSet = new FileProcessor("property_data.csv");
    //The readFile function returns a list of string arrays, so I store that here
    //because I think when updating the classifier for level 3 I will basically reread the file
    //and retrain the classifier
    private List<String[]> tableRows = myDataSet.readFile();

    /*Map with another map nested inside, idea here is each feature i.e. Grassy, Concrete, Double
     etc. will be a Key with a value of another Map with the Keys of Yes and No with a corresponding
     integer count. As of writing this comment it is a LinkedHashMap, I had changed it from a HashMap to
     a linked one because it looked nicer with my current printHashMap function, but I could and might just
     change my printHashMap function, removing the need for a linkedHashMap.
     */
    private Map<String, Map<String, Integer>> features = new LinkedHashMap<>();
    String[] featureColumns = {"Grass", "Concrete",
            "Double", "Single",
            "House", "Apartment",
            "Fixed", "Temporary"};

    //Constructor, currently not sure how I feel about it generating my freq table, not sure if that's smooth.
    public NaiveBayesClassifier() throws IOException {
        /*
        yeah constructor deprecated didn't end up really using it since I seem to have strayed too far
        away to figure out how to reincorporate it again
         */
        //trainClassifier();
        /*
        genFreqTable();
        getTotalsFreq();

         */

        //printHashMap();

        //All used just for testing my Map
        //predict();
        //System.out.println(features.keySet());
        //System.out.println(features.get("Grass").get("No"));

    }

    public void trainClassifier(){
        tableRows = myDataSet.readFile();
        genFreqTable();
        getTotalsFreq();

    }

    public String testClassifier(){
        correctCount = 0;

        /* ORIGINAL FUNCTIONALITY PRESERVED BEFORE MEDDLING WITH THE STRATIFYING DATA
        //USING THIS CAN SEE HOW MY CLASSIFIER PERFORMS WITH ORIGINAL DATA FOUND IN CSV FILE
        //update and retrain the classifier
        tableRows = myDataSet.readFile();
        //this time however only on the first 150 rows of the data
        genFreqTable(0, 150);
        getTotalsFreq(0, 150);
         */

        //using the Stratified Data but in a way that made sense with how my program was already designed
        tableRows = splitData.getCombinedSet();
        genFreqTable(0, 150);
        getTotalsFreq(0, 150);

        //printHashMap();
        for (int i = 151; i < 200; i++){
            //store the i'th row of the csv
            String[] row = tableRows.get(i);
            //System.out.println(Arrays.toString(row));
            //get the trained classifier to make a prediction based on the columns of the current row
            String prediction = predict(row[0], row[1], row[2], row[3]);
            System.out.println(prediction);

            //compare the classifier's prediction to the actual label
            if (prediction.equals(row[4])){
                correctCount++;
                System.out.println("Classifier correct!");
            }
        }
        //output the data and accuracy to terminal
        System.out.println("Classifier correct count: " + correctCount);
        correctCount = correctCount/50;
        return ("Classifier accuracy: "+ correctCount);


    }

    /*This the main maths function, passes in the values user selects with the GUI
    and then uses the .get function of the map to find the values for math manipulation
     */
    public String predict(String gardenType, String bedType, String propertyType, String leaseType){
        String[] featureType = {gardenType, bedType, propertyType, leaseType};
        System.out.println(String.join("," , gardenType, bedType, propertyType, leaseType));
        priorYes = (double) (features.get("Total").get("Yes"))
                / ((features.get("Total").get("Yes")) + (features.get("Total").get("No")));
        priorNo = (double) (features.get("Total").get("No"))
                / ((features.get("Total").get("Yes")) + (features.get("Total").get("No")));

        /*
        This for loop so that I don't have to do the same operation of multiplying the probYes by the individual
        feature probability
         */
        probYes = priorYes;
        probNo = priorNo;
        for (String feature : featureType){
            probYes *= (double) (features.get(feature).get("Yes")) / (features.get("Total").get("Yes"));
            probNo *= (double) (features.get(feature).get("No")) / (features.get("Total").get("No"));

        }



        System.out.println("Probability Yes: " + probYes);
        System.out.println("Probability No: " + probNo);
        return probYes > probNo ? "Yes" : "No";
    }

    public void genFreqTable(){
        //I think I could've made it better but it works
        /*this int i and sameCol are to loop through my csv but making sure to keep it on the
        same column for 2 iterations as I am checking the same column for two different features
         */
        int i = 0;
        boolean sameCol = true;

        for (String column : featureColumns){
            //new hashmap that will store my count of Yes's and No's for each feature
            Map<String, Integer> counts = new HashMap<>();
            for (String[] row : tableRows) {
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

    //quick copy-paste of above function basically just to do the same thing, but removes the additional
    //clause for the if statement to just count up the total labels
    public void getTotalsFreq(){
        for (String column : featureColumns){
            Map<String, Integer> counts = new HashMap<>();
            for (String[] row : tableRows) {
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

    //prints out the hashmap
    public void printHashMap() {
        for (Map.Entry<String, Map<String, Integer>> entry : features.entrySet()) {
            String key = entry.getKey();
            Map<String, Integer> innerMap = entry.getValue();

            System.out.println("Key: " + key);
            for (Map.Entry<String, Integer> innerEntry : innerMap.entrySet()) {
                System.out.println("    " + innerEntry.getKey() + ": " + innerEntry.getValue());
            }
        }
    }

    /*
    Think I should actually be using method overloading here, instead of on the fileProcessor class.
    Going to try make it so that it loops for 150 times, rather than the full list of string arrays (200)
     */
    public void genFreqTable(int start, int end){
        int i = 0;
        boolean sameCol = true;

        for (String column : featureColumns){
            Map<String, Integer> counts = new HashMap<>();
            for (int j = start; j < end; j++){
                String [] row = tableRows.get(j);
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

    //more method overloading same as above for level 4 functionality
    public void getTotalsFreq(int start, int end){
        for (String column : featureColumns){
            Map<String, Integer> counts = new HashMap<>();
            for (int j = start; j < end; j++){
                String [] row = tableRows.get(j);
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



}
