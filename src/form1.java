import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class form1 extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JPanel panel1;
    private JButton button1;
    private JTable table1;

    public form1(){
        add(panel1);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                int id;
                String isim;
                id = Integer.parseInt(textField1.getText());
                isim = textField2.getText();
                System.out.println(id+" "+isim);
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String sql = "INSERT INTO hesap (idhesap, kisi) " +
                            "VALUES (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, isim);
                    System.out.println("geldi");
                    int addedRows = preparedStatement.executeUpdate();
                    if (addedRows>0){
                        System.out.println(addedRows);
                    }
                    String query = "select * from hesap";
                    ResultSet rs= statement.executeQuery(query);
                    ResultSetMetaData rsmd= rs.getMetaData();
                    DefaultTableModel model= (DefaultTableModel) table1.getModel();
                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i=0;i<cols;i++){
                        colName[i]=rsmd.getColumnName(i+1);
                    }
                    model.setColumnIdentifiers(colName);
                    String idd,kisi;
                    while (rs.next()){
                        idd = rs.getString(1);
                        kisi = rs.getString(2);
                        String[] row = {idd,kisi};
                        model.addRow(row);
                    }
                    statement.close();
                    connection.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }


}
