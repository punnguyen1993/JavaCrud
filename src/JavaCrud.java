import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class JavaCrud {
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtPrice;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField txtpid;
    private JButton saveButton;
    private JTextField txtQty;
    private JButton searchButton;


    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCrud");
        frame.setContentPane(new JavaCrud().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    public JavaCrud() {
        Connect();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, price, qty;
                name = txtName.getText();
                price = txtPrice.getText();
                qty = txtQty.getText();

                try{
                    pst = con.prepareStatement("INSERT INTO products(pname, price, qty)values(?,?,?)");
                    pst.setString(1,name);
                    pst.setString(2,price);
                    pst.setString(3,qty);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added!!!!");

                    txtName.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtName.requestFocus();

                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String pid = txtpid.getText();
                    pst = con.prepareStatement("SELECT pname, price, qty FROM products WHERE pid = ?");
                    pst.setString(1, pid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()){
                        String name = rs.getString(1);
                        String price = rs.getString(2);
                        String qty = rs.getString(3);

                        txtName.setText(name);
                        txtPrice.setText(price);
                        txtQty.setText(qty);
                    }else{
                        txtName.setText("");
                        txtPrice.setText("");
                        txtQty.setText("");
                        JOptionPane.showMessageDialog(null, "Invalid Product ID");
                    }

                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, price, qty, pid;
                name = txtName.getText();
                price = txtPrice.getText();
                qty = txtQty.getText();
                pid = txtpid.getText();


                try{
                    pst = con.prepareStatement("UPDATE products SET pname = ?, price = ?, qty = ? WHERE pid = ?");
                    pst.setString(1, name);
                    pst.setString(2, price);
                    pst.setString(3, qty);
                    pst.setString(4, pid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated!!!!");

                    txtName.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtName.requestFocus();
                    txtpid.setText("");

                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pid;
                pid = txtpid.getText();

                try{
                    pst = con.prepareStatement("DELETE FROM products WHERE pid = ?");
                    pst.setString(1, pid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted!!!!!");

                    txtName.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtName.requestFocus();
                    txtpid.setText("");

                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
    }

    Connection con;
    PreparedStatement pst;

    public void Connect(){
        try{
            // kh??c n??y tr??? v??? ?????i t?????ng li??n k???t v???i class v???i package directory
            // n?? n???m ??? lib package, ???????c khuy???n ngh??? ????? s??? d???ng DriverManager
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud-schema",
                                                    "root", "th!nhnguyen93");

            //The query below is to print elements in column price of table
            // It is used to understand structure of mysql, need to delete later
//            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("price"));
//            }

        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
