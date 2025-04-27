import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PropertyDataGenerator {
    public static void main(String[] args) {
        // Define possible values
        String[] hasGarden = {"Grass", "Concrete"};
        String[] bedSize = {"Double", "Single"};
        String[] propertyType = {"Apartment", "House"};
        String[] lease = {"Fixed", "Temporary"};
        List<String[]> dataset = new ArrayList<>();

        // Generate all possible unique combinations (16 total)
        List<String[]> combinations = new ArrayList<>();
        for (String g : hasGarden) {
            for (String bs : bedSize) {
                for (String p : propertyType) {
                    for (String le : lease) {
                        combinations.add(new String[]{g, bs, p, le});
                    }
                }
            }
        }

        //Adds the label to the unique permutations, alternating between yes and no
        for (int i=0; i< 200; i++){
            String label = (i % 2 == 0) ? "Yes" : "No";
            String[] permutation = Arrays.copyOf(combinations.get(i%16), 5);
            permutation[4] = label;
            dataset.add(permutation);
        }

        //shuffle the dataset
        Collections.shuffle(dataset, new Random(18));

        // Write dataset to CSV file
        String fileName = "property_data.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("GardenType,BedSize,PropertyType,LeaseType,PropertyIsRented\n");
            for (String[] row : dataset) {
                writer.append(String.join(",", row)).append("\n");
            }
            System.out.println("CSV file successfully created: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
