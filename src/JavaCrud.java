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
    private JTextField textField3;
    private JButton saveButton;
    private JTextField txtQty;


    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCrud");
        frame.setContentPane(new JavaCrud().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    PreparedStatement pst;
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
//                    pst.setString(1,"1");
                    pst.setString(1,name);
                    pst.setString(2,price);
                    pst.setString(3,qty);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addeddddddddd!!!");

                    txtName.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtName.requestFocus();

                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });

    }

    Connection con;


    public void Connect(){
        //"jdbc:mysql://localhost:3306/crud-schema"
        try{
            //khúc này url thêm "cj" vào chạy không có lỗi, google sau
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
