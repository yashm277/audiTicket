import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.*;

public class Table extends JFrame implements ActionListener {
    JFrame f;
    JButton b1;

    DefaultTableModel model = new DefaultTableModel(
            new String[] { "Seat No", "Status" }, 0);
    Container cnt = this.getContentPane();

    Table(String event, String UserId) throws SQLException {
        ConnectionClass c1 = new ConnectionClass();
        String q1 = "select AvailableSeats from event where EventName = '" + event + "';";
        ResultSet rs = c1.stm.executeQuery(q1);
        rs.next();
        String test = rs.getString("AvailableSeats");
        if (test == null) {
            JOptionPane.showMessageDialog(null, "There are no seats available for " + event);

        } else {
            String hul = test.trim();
            String q2 = "select totalseats from event where EventName = '" + event + "';";
            ResultSet rs2 = c1.stm.executeQuery(q2);
            rs2.next();
            String tes = rs2.getString("totalseats");
            String[] array = hul.split(" ");

            Vector<String> columnNames = new Vector<String>();
            columnNames.add("Seat Number");
            columnNames.add("Status");

            Vector<String> row = new Vector<String>();
            Vector data = new Vector();
            int j = 0;
            for (int i = 1; i < Integer.parseInt(tes) + 1; i++) {
                row = new Vector();
                row.add("" + i);
                if (j < array.length && array[j].equals("" + i)) {
                    row.add("Available");
                    j++;
                } else {
                    row.add("Booked");
                }
                data.add(row);
            }

            JFrame frame = new JFrame("Availabilty");
            frame.setBounds(400, 0, 500, 400);
            JScrollPane jsp = new JScrollPane(new JTable(data, columnNames));
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(jsp, BorderLayout.CENTER);
            frame.getContentPane().add(panel);
            frame.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent ee) {
    }
}
