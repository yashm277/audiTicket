import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Track extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1;
    JButton b1, b2;
    JComboBox c1;

    Track() {
        f = new JFrame("Track Event");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("Name of Event -");
        l1.setBounds(50, 75, 1000, 30);
        f.add(l1);

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT eventname FROM event;";
            ResultSet rs = obj.stm.executeQuery(query);
            ArrayList<String> eve = new ArrayList<String>();
            while (rs.next()) {
                eve.add(rs.getString("eventname"));
            }
            Object[] ev = eve.toArray();
            System.out.println(ev[0]);
            c1 = new JComboBox<Object>(ev);
            c1.setBounds(160, 75, 150, 30);
            c1.addActionListener(this);
            f.add(c1);
        } catch (Exception e) {
            System.out.println(e);
        }

        b1 = new JButton("Back");
        b1.setBounds(40, 190, 120, 30);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Track");
        b2.setBounds(200, 190, 120, 30);
        b2.addActionListener(this);
        f.add(b2);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 300);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            String name = (String) c1.getSelectedItem();
            int b = 0;

            try {

                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Select an Event");
                } else {
                    ConnectionClass c1 = new ConnectionClass();
                    ResultSet rs1 = c1.stm.executeQuery(
                            "SELECT TotalSeats,BookedSeats,Price FROM Event where EventName  = '" + name + "';");
                    rs1.next();
                    System.out.println(rs1.getString("TotalSeats"));
                    System.out.println(rs1.getString("Price"));
                    System.out.println(rs1.getString("BookedSeats"));
                    int totSeats = Integer.parseInt(rs1.getString("TotalSeats"));
                    Double price = Double.parseDouble(rs1.getString("Price"));
                    int bookedSeats = Integer.parseInt(rs1.getString("BookedSeats"));
                    Double revenue = bookedSeats * price;
                    int availableSeats = totSeats - bookedSeats;
                    JOptionPane.showMessageDialog(null, "Total Revenue for " + name + " = " + revenue
                            + "\nTotal booked seats = " + bookedSeats + "\nTotal available seats = " + availableSeats);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b1) {
            f.setVisible(false);
            new AdminHome();
        }
    }
}