import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class musteri extends JFrame{
    private JPanel panell;
    private JButton krediIşlemleriButton;
    private JButton bilgiGüncellemeButton;
    private JButton aylıkÖzetButton;
    private JButton hesapişlemleributton;
    private JTable table1;
    private JTable table2;
    private JButton kredileriGörüntüleButton;
    private JButton hesaplarıGörüntüleButton;
    private User user;

    public musteri(){
        add(panell);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



    }
    public musteri(User user){
        this.user=user;
        add(panell);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        hesaplarıGörüntüleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "select * from hesap where kisi_tc ="+user.getTcno()+"";
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
                    String hesap_id,doviz_id,kisi_tc,hesap_bakiye;
                    while (rs.next()){
                        hesap_id = rs.getString(1);
                        doviz_id = rs.getString(2);
                        kisi_tc = rs.getString(3);
                        hesap_bakiye = rs.getString(4);
                        String[] row = {hesap_id,doviz_id,kisi_tc,hesap_bakiye};
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
        bilgiGüncellemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musteri_guncelleme mg = new musteri_guncelleme(user);
                mg.setVisible(true);
            }
        });
        hesapişlemleributton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musteri_hesap mh = new musteri_hesap(user);
                mh.setVisible(true);
            }
        });
        kredileriGörüntüleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "select * from krediler";
                    ResultSet rs= statement.executeQuery(query);
                    ResultSetMetaData rsmd= rs.getMetaData();
                    DefaultTableModel model= (DefaultTableModel) table2.getModel();
                    model.setRowCount(0);
                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i=0;i<cols;i++){
                        colName[i]=rsmd.getColumnName(i+1);
                    }
                    model.setColumnIdentifiers(colName);
                    String kredi_id,musteri_tc,miktar,odenen_ana,odenen_faiz,baslangic_tarihi,bitis_tarihi,faiz_id;
                    while (rs.next()){
                        kredi_id = rs.getString(1);
                        musteri_tc = rs.getString(2);
                        miktar = rs.getString(3);
                        odenen_ana = rs.getString(4);
                        odenen_faiz = rs.getString(5);
                        baslangic_tarihi = rs.getString(6);
                        bitis_tarihi = rs.getString(7);
                        faiz_id = rs.getString(8);
                        String[] row = {kredi_id,musteri_tc,miktar,odenen_ana,odenen_faiz,baslangic_tarihi,bitis_tarihi,faiz_id};
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
        krediIşlemleriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musteri_kredi mk = new musteri_kredi(user);
                mk.setVisible(true);
            }
        });
        aylıkÖzetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musteri_aylikozet ma = new musteri_aylikozet(user);
                ma.setVisible(true);
            }
        });
    }
}
