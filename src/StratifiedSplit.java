/*
GenAI solution for stratifying my data...

 */
import java.util.*;
import java.util.stream.Collectors;

public class StratifiedSplit {

    public static void main(String[] args) {
        // Example dataset (replace with your actual data)
        List<DataRow> dataset = new ArrayList<>();
        dataset.add(new DataRow(1, 2, 3, 4, "Yes"));
        dataset.add(new DataRow(5, 6, 7, 8, "No"));
        dataset.add(new DataRow(9, 10, 11, 12, "Yes"));
        dataset.add(new DataRow(13, 14, 15, 16, "No"));
        // ... (add all 200 rows)

        // Step 1: Group data by label
        Map<String, List<DataRow>> groupedByLabel = dataset.stream()
                .collect(Collectors.groupingBy(DataRow::getLabel));

        // Step 2: Define split ratio (150 train, 50 test)
        double testRatio = 50.0 / dataset.size(); // 25% test
        List<DataRow> trainSet = new ArrayList<>();
        List<DataRow> testSet = new ArrayList<>();

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

    // Helper method to count label occurrences
    private static long countLabel(List<DataRow> data, String label) {
        return data.stream().filter(row -> row.getLabel().equals(label)).count();
    }

    // Sample data row class (replace with your actual structure)
    static class DataRow {
        private int f1, f2, f3, f4;
        private String label;

        public DataRow(int f1, int f2, int f3, int f4, String label) {
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