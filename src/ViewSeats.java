import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.*;

public class ViewSeats extends JFrame implements ActionListener {
    ViewSeats(String UserID) {
        try {
            ConnectionClass c1 = new ConnectionClass();
            String q1 = "select * from tickets where userid = '" + UserID + "';";
            ResultSet rs = c1.stm.executeQuery(q1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            Vector<String> columnNames = new Vector<String>();

            for (int column = 2; column <= columnsNumber; column++) {
                columnNames.add(rsmd.getColumnName(column));
            }

            Vector data = new Vector();
            Vector row = new Vector();
            while (rs.next()) {
                row = new Vector(columnsNumber);
                for (int i = 2; i <= columnsNumber; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            JFrame frame = new JFrame("Tickets");
            frame.setBounds(400, 0, 500, 400);
            JScrollPane jsp = new JScrollPane(new JTable(data, columnNames));
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(jsp, BorderLayout.CENTER);
            frame.getContentPane().add(panel);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ee) {
    }
}
