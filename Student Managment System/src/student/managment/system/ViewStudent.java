package student.managment.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewStudent extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewStudent frame = new ViewStudent();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewStudent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 782, 611);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Student Details");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));

        JButton btnNewButton = new JButton("Go Back");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.show();
                dispose();
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));

        // Setting up the table
        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        scrollPane.setViewportView(table);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(lblNewLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                            .addComponent(btnNewButton))
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(btnNewButton))
                    .addGap(18)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);

        // Load data from the database
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        String url = "jdbc:mysql://localhost/studentmanagementsystem";
        String user = "root"; // Change as per your database username
        String password = ""; // Change as per your database password

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagementsystem", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from student")) {

            // Set up table model
            DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Number", "Email", "Contactnumber","Homecity"}, 0);
            while (rs.next()) {
                String name = rs.getString("name");
                String entrynumber = rs.getString("entrynumber");
                String email = rs.getString("email");
                String contactnumber = rs.getString("contactnumber");
                String homecity = rs.getString("homecity");
                model.addRow(new Object[]{name, entrynumber, email, contactnumber,homecity});
            }
            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
