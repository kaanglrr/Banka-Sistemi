import model.Islemler;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;

public class musteri_aylikozet extends JFrame{
    private JTable table1;
    private JButton aylıkÖzetiGösterButton;
    private JPanel musteriozet;
    private JTextField textField1;
    private User user;

    public musteri_aylikozet(){
        add(musteriozet);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public musteri_aylikozet(User user){
        this.user=user;
        add(musteriozet);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aylıkÖzetiGösterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sistem_tarihi;
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query1 = "select * from zaman";
                    ResultSet rs= statement.executeQuery(query1);
                    rs.next();
                    sistem_tarihi = rs.getString("sistem_tarihi");


                    String query = "select * from islemler where kaynak_id ="+Integer.parseInt(textField1.getText())+" ";
                    rs= statement.executeQuery(query);
                    ResultSetMetaData rsmd= rs.getMetaData();
                    DefaultTableModel model= (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i=0;i<cols;i++){
                        colName[i]=rsmd.getColumnName(i+1);
                    }
                    model.setColumnIdentifiers(colName);
                    String islem_id,kaynak_hesap,hedef_hesap,islem_turu,tutar,kaynak_bakiye,hedef_bakiye,islem_tarihi;
                    while (rs.next()){
                        islem_id = rs.getString(1);
                        kaynak_hesap = rs.getString(2);
                        hedef_hesap = rs.getString(3);
                        islem_turu = rs.getString(4);
                        tutar = rs.getString(5);
                        kaynak_bakiye = rs.getString(6);
                        hedef_bakiye = rs.getString(7);
                        islem_tarihi = rs.getString(8);
                        String[] row = {islem_id,kaynak_hesap,hedef_hesap,islem_turu,tutar,kaynak_bakiye,hedef_bakiye,islem_tarihi};
                        if (ay_kontrol(islem_tarihi,sistem_tarihi)){
                            model.addRow(row);
                        }
                    }
                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
    }
    public boolean ay_kontrol(String tarih,String tarih1){
        Date a = Date.valueOf(tarih);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(a);
        int ilk = calendar.get(Calendar.MONTH);
        a = Date.valueOf(tarih1);
        calendar.setTime(a);
        int iki = calendar.get(Calendar.MONTH);
        if(ilk==iki){
            return true;
        }else {
            return false;
        }
    }
}
