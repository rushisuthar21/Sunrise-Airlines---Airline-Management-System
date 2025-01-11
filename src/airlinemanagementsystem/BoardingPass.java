package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//BoardingPass class extends JFrame and implements ActionListener for handling GUI and user actions
public class BoardingPass extends JFrame implements ActionListener{
    
    //declaring UI components
    JTextField tfpnr;
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate;
    JButton fetchButton;
    
    //constructor to initialize the BoardingPass window and its components
    public BoardingPass() {
        //setting background color and layout of the window
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //setting title of the window
        setTitle("SUNRISE AIRLINES BOARDING PASS");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //subheading label for boarding pass title
        JLabel subheading = new JLabel("BOARDING PASS");
        subheading.setBounds(360, 50, 300, 30);
        subheading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subheading.setForeground(Color.RED);
        add(subheading);
        
        //Label and text field for PNR input
        JLabel lblaadhar = new JLabel("PNR DETAILS");
        lblaadhar.setBounds(60, 100, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(220, 100, 150, 25);
        add(tfpnr);
        
        //Button to fetch and display boarding pass details
        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 100, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        //Labels to display the passenger's name, nationality, source, destination, flight name, flight code, and date
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 140, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 140, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("NATIONALITY");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);
        
        JLabel lbladdress = new JLabel("SRC");
        lbladdress.setBounds(60, 220, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        lblsrc = new JLabel();
        lblsrc.setBounds(220, 220, 150, 25);
        add(lblsrc);
        
        JLabel lblgender = new JLabel("DEST");
        lblgender.setBounds(380, 220, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        lbldest = new JLabel();
        lbldest.setBounds(540, 220, 150, 25);
        add(lbldest);
        
        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 260, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);
        
        labelfname = new JLabel();
        labelfname.setBounds(220, 260, 150, 25);
        add(labelfname);
        
        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(380, 260, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);
        
        labelfcode = new JLabel();
        labelfcode.setBounds(540, 260, 150, 25);
        add(labelfcode);
        
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 300, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        labeldate = new JLabel();
        labeldate.setBounds(220, 300, 150, 25);
        add(labeldate);
        
        //adding an image to the window (e.g., airline logo)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/icon.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550,0, 490, 370);
        add(lblimage);
        
        //eting size and location of the window, and make it visible
        setSize(970, 420);
        setLocation(300, 150);
        setVisible(true);
    }
    
    //Action listener method to handle button click events
    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText();

        try {
            //establishing a connection to the database
            MyConnection conn = new MyConnection();

            // query to retrieve reservation details based on the PNR number
            String query = "select * from reservation where PNR = '"+pnr+"'";

            //executing the query and process the result
            ResultSet rs = conn.stm.executeQuery(query);

            // If a matching record is found, populating the labels with the passenger's details
            if (rs.next()) {
                tfname.setText(rs.getString("name")); 
                tfnationality.setText(rs.getString("nationality")); 
                lblsrc.setText(rs.getString("departure")); 
                lbldest.setText(rs.getString("destination1"));  
                labelfname.setText(rs.getString("flightname"));  
                labelfcode.setText(rs.getString("flightcode"));  
                labeldate.setText(rs.getString("book_date")); 
            } else {
                //showing a message if the PNR is not found
                JOptionPane.showMessageDialog(null, "Please enter correct PNR");                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //main method to create an instance of the BoardingPass class and display the window
    public static void main(String[] args) {
        new BoardingPass();
    }
}