import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


//our class extends from JFrame for the GUI creation and implements ActionListener
// for event handling
public class GUI extends JFrame implements ActionListener {
    
    NaiveBayesClassifier nbc = new NaiveBayesClassifier();
    FileProcessor fileProcessor = new FileProcessor("property_data.csv");
    
    //a menubar for the top of our frame, carry over from Lab Screen class (yay polymorphism!)
    JMenuBar menuBar = new JMenuBar();

    //adding a lil flair
    JMenu fileMenu = new JMenu("Author");
    JMenuItem nameItem = new JMenuItem("Andrew Ugweches");
    JMenuItem studentNoItem = new JMenuItem("C23767071");

    //create a panel for the first buttons
    JPanel panel0 = new JPanel();
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    
    //labels
    JLabel gtLabel = new JLabel("Garden Type:");
    JLabel btLabel = new JLabel("Bed Type:");
    JLabel ptLabel = new JLabel("Property Type:");
    JLabel ltLabel = new JLabel("Lease Type:");
    
    //buttons
    JButton predict = new JButton("Predict");
    JButton train = new JButton("Train");
    JButton addNew = new JButton("Add New Row");
    JButton testClassifier = new JButton("Test Classifier");
    JButton freqTable = new JButton("Print FreqTable/HashMap in Terminal");

    //comboboxes
    JComboBox<String> gardenTypes = new JComboBox<>(new String[]{"Grass", "Concrete"});
    JComboBox<String> bedTypes = new JComboBox<>(new String[]{"Double", "Single"});
    JComboBox<String> propertyTypes = new JComboBox<>(new String[]{"House", "Apartment"});
    JComboBox<String> leaseTypes = new JComboBox<>(new String[]{"Fixed", "Temporary"});
    
    //popup
    JOptionPane popup = new JOptionPane();
    
    //constructor for our screen
    public GUI() throws IOException {
        //default params for our screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        //add the menu elements to the file menu
        fileMenu.add(nameItem);
        fileMenu.addSeparator();
        fileMenu.add(studentNoItem);

        //add the fileMenu to the menuBar object
        menuBar.add(fileMenu);


        //add our labels and comboBox for the feature columns and selection
        panel.add(gtLabel);
        panel.add(gardenTypes);

        panel.add(btLabel);
        panel.add(bedTypes);

        panel.add(ptLabel);
        panel.add(propertyTypes);

        panel.add(ltLabel);
        panel.add(leaseTypes);

        //next panel to hold some buttons
        panel2.add(predict);
        panel2.add(train);
        
        predict.addActionListener(this);
        train.addActionListener(this);

        predict.setEnabled(false);
        
        //another panel for some more buttons
        panel3.add(addNew);
        addNew.addActionListener(this);
        
        //and again (this for level 4, wip)
        panel4.add(testClassifier);
        testClassifier.addActionListener(this);
        
        //final panel
        panel5.add(freqTable);
        freqTable.addActionListener(this);
        freqTable.setEnabled(false);

        //set the layout of the screen and add in all the panels.
        this.setLayout(new GridLayout(9, 1));
        this.setJMenuBar(menuBar);
        //this.add(panel0);
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.setVisible(true);

    }

    //this method gets performs an action when one of our Jbuttons are pressed
    // using the getSource function to find which button has been pressed to give the apropriate output
    @Override
    public void actionPerformed(ActionEvent e) {
        //if button pressed was predict
        if (e.getSource() == predict) {
            String gardenType = (String) gardenTypes.getSelectedItem();
            String bedType = (String) bedTypes.getSelectedItem();
            String propertyType = (String) propertyTypes.getSelectedItem();
            String leaseType = (String) leaseTypes.getSelectedItem();

            //nbc.predict returns a string Yes or No depending on which probability is higher
            //so we pass all the arguments that the user is selected so predict knows what features to look for
            // and calculate
            JOptionPane.showMessageDialog(popup, nbc.predict(gardenType, bedType, propertyType, leaseType));

        }
        else if (e.getSource() == train){
            nbc.trainClassifier();
            JOptionPane.showMessageDialog(popup, "Classifier Trained");
            //can now press the other buttons
            //have to include this because the freqTable/hashmap doesn't exist until you run the trainClassifier command
            predict.setEnabled(true);
            freqTable.setEnabled(true);
        }

        else if (e.getSource() == addNew){
            String gardenType = (String) gardenTypes.getSelectedItem();
            String bedType = (String) bedTypes.getSelectedItem();
            String propertyType = (String) propertyTypes.getSelectedItem();
            String leaseType = (String) leaseTypes.getSelectedItem();

            String fullInput = String.join(",",gardenType, bedType, propertyType, leaseType);
            
            //confirm user wants to add row based off the JComboBox's and allow them to select which label
            int result = JOptionPane.showConfirmDialog(popup, fullInput + "\nSelect Feature Label: ",
                    "Confirm Adding Row", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION){
                System.out.println("new row added - label:yes");
                try {
                    fileProcessor.writeToFile(String.join(",", fullInput, "Yes"));
                    predict.setEnabled(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
            else if (result == JOptionPane.NO_OPTION){
                System.out.println("new row added - label:no");
                try {
                    fileProcessor.writeToFile(String.join(",", fullInput, "No"));
                    predict.setEnabled(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }



        }
        //This will be for level 4 functionality
        else if (e.getSource() == testClassifier){

        }
        //This is to print out the hashmap, lets you see that new rows are being added
        else if (e.getSource() == freqTable){
            nbc.printHashMap();
        }


    }


}



