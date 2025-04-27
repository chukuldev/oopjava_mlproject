import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PropertyDataGenerator {
    public static void genData() {
        // Define possible values
        String[] hasGarden = {"Grass", "Concrete"};
        String[] bedSize = {"Double", "Single"};
        String[] propertyType = {"House", "Apartment"};
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
            String[] permutation = Arrays.copyOf(combinations.get(i%16), 5);
            permutation[4] = genLabel(permutation);
            dataset.add(permutation);
        }



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

    //Function to generate a label based on what I believe would be a more desirable property type
    //This is to make the data, and classifier/predictor more intuitive/realistic =)
    public static String genLabel(String[] combination){
        if (combination[0].equals("Grass") && combination[1].equals("Double")
         && combination[3].equals("Fixed")){
            return new Random().nextDouble() < 0.8 ? "Yes" : "No";
        }
        else{
            return new Random().nextDouble() < 0.5 ? "Yes" : "No";
        }
    }


}
