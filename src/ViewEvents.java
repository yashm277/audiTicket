import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.*;

public class ViewEvents extends JFrame implements ActionListener {
    JFrame f;
    JButton b1;

    DefaultTableModel model = new DefaultTableModel(
            new String[] { "Event Name", "Total Seats", "Price", "Booked seats" }, 0);
    Container cnt = this.getContentPane();

    ViewEvents() throws SQLException {
        ConnectionClass c1 = new ConnectionClass();
        String q1 = "select * from event";
        ResultSet rs = c1.stm.executeQuery(q1);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        Vector<String> columnNames = new Vector<String>();

        for (int column = 1; column <= columnsNumber - 1; column++) {
            columnNames.add(rsmd.getColumnName(column));
        }

        Vector data = new Vector();
        Vector row = new Vector();
        while (rs.next()) {
            row = new Vector();
            for (int i = 1; i <= columnsNumber - 1; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        JFrame frame = new JFrame("Events");
        frame.setBounds(400, 0, 500, 400);
        JScrollPane jsp = new JScrollPane(new JTable(data, columnNames));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(jsp, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ee) {
    }
}
