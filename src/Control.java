import java.io.IOException;

public class Control {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");

        //generates our data for the dataset
        //PropertyDataGenerator.genData();

        GUI mygui = new GUI();
        FileProcessor myDataSet = new FileProcessor("propert_data.csv");


    }
}