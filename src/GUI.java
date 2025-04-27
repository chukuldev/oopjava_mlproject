import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


//our class extends from JFrame for the GUI creation and implements ActionListener
// for event handling
public class GUI extends JFrame implements ActionListener {

    //Attributes of our screen class

    //a menubar for the top of our frame
    JMenuBar menuBar = new JMenuBar();

    //adding a filemenu and appropriate menu elements to our menu bar
    JMenu fileMenu = new JMenu("Help");
    JMenuItem openItem = new JMenuItem("lol");
    JMenuItem exitItem = new JMenuItem("same bro");

    //create a panel for the first buttons
    JPanel panel = new JPanel();

    JButton predict = new JButton("Predict");

    //create another panel for our text or label
    JPanel panel2 = new JPanel();

    JComboBox<String> gardenTypes = new JComboBox<>(new String[]{"Grass", "Concrete"});
    JComboBox<String> bedTypes = new JComboBox<>(new String[]{"Double", "Single"});
    JComboBox<String> propertyTypes = new JComboBox<>(new String[]{"House", "Apartment"});
    JComboBox<String> leaseTypes = new JComboBox<>(new String[]{"Fixed", "Temporary"});

    JOptionPane popup = new JOptionPane();

    JTextArea area = new JTextArea(30, 30);

    //constructor for our screen
    public GUI() {
        //default params for our screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        //add the menu elements to the file menu
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        //add the fileMenu to the menuBar object
        menuBar.add(fileMenu);


        //add our label and jtextfield to our panels
        panel.add(gardenTypes);
        panel.add(bedTypes);
        panel.add(propertyTypes);
        panel.add(leaseTypes);


        panel2.add(predict);
        predict.addActionListener(this);



        //set the layout of the screen and add in all the panels.
        this.setLayout(new GridLayout(6, 3));
        this.setJMenuBar(menuBar);
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
            JOptionPane.showMessageDialog(popup, "abc");

            }
        }

    }



