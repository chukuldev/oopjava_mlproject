import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


//our class extends from JFrame for the GUI creation and implements ActionListener
// for event handling
public class GUI extends JFrame implements ActionListener {

    //Attributes of our screen class
    NaiveBayesClassifier nbc = new NaiveBayesClassifier();
    //a menubar for the top of our frame
    JMenuBar menuBar = new JMenuBar();

    //adding a lil flair
    JMenu fileMenu = new JMenu("Author");
    JMenuItem nameItem = new JMenuItem("Andrew Ugweches");
    JMenuItem studentNoItem = new JMenuItem("C23767071");

    //create a panel for the first buttons
    JPanel panel0 = new JPanel();
    JPanel panel = new JPanel();

    JLabel gtLabel = new JLabel("Garden Type:");
    JLabel btLabel = new JLabel("Bed Type:");
    JLabel ptLabel = new JLabel("Property Type:");
    JLabel ltLabel = new JLabel("Lease Type:");

    JButton predict = new JButton("Predict");
    JButton train = new JButton("Train");

    //create another panel for our text or label
    JPanel panel2 = new JPanel();

    JComboBox<String> gardenTypes = new JComboBox<>(new String[]{"Grass", "Concrete"});
    JComboBox<String> bedTypes = new JComboBox<>(new String[]{"Double", "Single"});
    JComboBox<String> propertyTypes = new JComboBox<>(new String[]{"House", "Apartment"});
    JComboBox<String> leaseTypes = new JComboBox<>(new String[]{"Fixed", "Temporary"});

    JOptionPane popup = new JOptionPane();

    JTextArea area = new JTextArea(30, 30);

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


        //add our label and jtextfield to our panels
        panel.add(gtLabel);
        panel.add(gardenTypes);

        panel.add(btLabel);
        panel.add(bedTypes);

        panel.add(ptLabel);
        panel.add(propertyTypes);

        panel.add(ltLabel);
        panel.add(leaseTypes);


        panel2.add(predict);
        panel2.add(train);
        predict.addActionListener(this);
        train.addActionListener(this);

        predict.setEnabled(false);



        //set the layout of the screen and add in all the panels.
        this.setLayout(new GridLayout(9, 1));
        this.setJMenuBar(menuBar);
        this.add(panel0);
        this.add(panel);
        this.add(panel2);
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


            JOptionPane.showMessageDialog(popup, nbc.predict(gardenType, bedType, propertyType, leaseType));

        }
        else if (e.getSource() == train){
            nbc.trainClassifier();
            JOptionPane.showMessageDialog(popup, "Classifier Trained");
            predict.setEnabled(true);
        }
    }


}



