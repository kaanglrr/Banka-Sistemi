import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class temsilci extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton müşteriEkleButton;
    private JPanel panel1;
    private JButton müşterileriListeleButton;
    private JTable table1;
    private JTextField textField8;
    private JButton müşteriyiGüncelleButton;
    private JButton müşteriyiSilButton;
    private User user;
    public temsilci(){
        add(panel1);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public temsilci(User user) {
        this.user = user;
        add(panel1);
        setSize(800, 400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        müşteriEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long tc_no = Long.parseLong(textField1.getText());
                String ad_soyad = textField2.getText();
                String adres = textField4.getText();
                String eposta = textField5.getText();
                Long tel_no = Long.parseLong(textField6.getText());
                String parola = textField7.getText();
                Long temsilci_id;
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();
                    String query1 = "SELECT temsilci_tc, COUNT(temsilci_tc) FROM temsilci_musteri GROUP BY temsilci_tc ORDER BY COUNT(temsilci_tc) ASC limit 1";
                    ResultSet rs= statement.executeQuery(query1);
                    rs.next();
                    temsilci_id = rs.getLong(1);

                    String query = " INSERT  INTO musteri (tc_no,ad_soyad,adres,eposta,tel_no,parola) VALUES ("+tc_no+", '"+ad_soyad+"', '"+adres+"', '"+eposta+"', "+tel_no+", '"+parola+"' ) ";
                    statement.executeUpdate(query);

                    String query2 = " INSERT  INTO temsilci_musteri (musteri_tc,temsilci_tc) VALUES ("+tc_no+", "+temsilci_id+") ";
                    statement.executeUpdate(query2);


                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        müşterileriListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "SELECT musteri.* FROM temsilci_musteri INNER JOIN musteri ON musteri.tc_no = temsilci_musteri.musteri_tc WHERE temsilci_musteri.temsilci_tc = "+user.getTcno()+"";
                    ResultSet rs= statement.executeQuery(query);
                    ResultSetMetaData rsmd= rs.getMetaData();
                    DefaultTableModel model= (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i=0;i<cols;i++){
                        colName[i]=rsmd.getColumnName(i+1);
                    }
                    model.setColumnIdentifiers(colName);
                    String tc_no,ad_soyad,adres,eposta,tel_no,parola;
                    while (rs.next()) {
                        tc_no = rs.getString(1);
                        ad_soyad = rs.getString(2);
                        adres = rs.getString(3);
                        eposta = rs.getString(4);
                        tel_no = rs.getString(5);
                        parola = rs.getString(6);
                        String[] row = {tc_no,ad_soyad,adres,eposta,tel_no,parola};
                        model.addRow(row);
                    }
                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        müşteriyiSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long tc_no = Long.parseLong(textField8.getText());
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = " DELETE FROM musteri where tc_no= "+tc_no+" ";
                    statement.executeUpdate(query);



                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });

    }
}
