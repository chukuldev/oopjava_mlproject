import java.io.IOException;

public class Control {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");

        //generates our data for the dataset
        //PropertyDataGenerator.genData();

        GUI mygui = new GUI();
        FileProcessor myDataSet = new FileProcessor("property_data.csv");
        //myDataSet.readFile();

        myDataSet.printHashMap(myDataSet.readFile());
    }
}