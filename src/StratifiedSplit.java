/*
GenAI did start off this code originally so credit where credit is due, just changed everything
to actually work in my program
 */

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StratifiedSplit {
    public FileProcessor fileProcessor = new FileProcessor("property_data.csv");
    List<String[]> existingData = fileProcessor.readFile();
    List<DataRow> trainSet = new ArrayList<>();
    List<DataRow> testSet = new ArrayList<>();

    public StratifiedSplit() throws IOException {
        List<DataRow> dataset = new ArrayList<>();
        for (String[] row : existingData) {
            dataset.add(new DataRow(row[0], row[1], row[2], row[3], row[4]));
        }

        // Step 1: Group data by label
        Map<String, List<DataRow>> groupedByLabel = dataset.stream()
                .collect(Collectors.groupingBy(DataRow::getLabel));

        // Step 2: Define split ratio (150 train, 50 test)
        double testRatio = 50.0 / dataset.size(); // 25% test

        // Step 3: Stratified split for each label group
        for (Map.Entry<String, List<DataRow>> entry : groupedByLabel.entrySet()) {
            List<DataRow> group = entry.getValue();
            Collections.shuffle(group); // Randomize to avoid bias

            int groupSize = group.size();
            int testSize = (int) (groupSize * testRatio);

            // Add to test set
            testSet.addAll(group.subList(0, testSize));
            // Add remaining to train set
            trainSet.addAll(group.subList(testSize, groupSize));
        }

        // Step 4: Verify stratification
        System.out.println("Train set size: " + trainSet.size());
        System.out.println("Test set size: " + testSet.size());
        System.out.println("Train 'Yes' count: " + countLabel(trainSet, "Yes"));
        System.out.println("Train 'No' count: " + countLabel(trainSet, "No"));
        System.out.println("Test 'Yes' count: " + countLabel(testSet, "Yes"));
        System.out.println("Test 'No' count: " + countLabel(testSet, "No"));
    }

    // Convert DataRow objects back to String[] for test set
    public List<String[]> getTestSet() {
        List<String[]> result = new ArrayList<>();
        for (DataRow row : testSet) {
            result.add(new String[]{row.f1, row.f2, row.f3, row.f4, row.getLabel()});
        }
        return result;
    }

    // Convert DataRow objects back to String[] for train set
    public List<String[]> getTrainSet() {
        List<String[]> result = new ArrayList<>();
        for (DataRow row : trainSet) {
            result.add(new String[]{row.f1, row.f2, row.f3, row.f4, row.getLabel()});
        }
        return result;
    }

    //output as an entire List of String arrays to preserve existing functionality in NBC class
    public List<String[]> getCombinedSet(){
        List<String[]> combined = getTrainSet();
        combined.addAll(getTestSet());

        return combined;
    }

    // Helper method to count label occurrences
    private static long countLabel(List<DataRow> data, String label) {
        return data.stream().filter(row -> row.getLabel().equals(label)).count();
    }

    // Inner class to hold row data, funny that this class of data is basically the same as what I thought to
    // implement originally but didn't realise how I could make use of it
    static class DataRow {
        private String f1, f2, f3, f4;
        private String label;

        public DataRow(String f1, String f2, String f3, String f4, String label) {
            this.f1 = f1;
            this.f2 = f2;
            this.f3 = f3;
            this.f4 = f4;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}