package Databasee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Databasee {
     private static final String connectUrl ="jdbc:sqlserver://DESKTOP-OLNNKN8:1433;databaseName=QLKS;user=sa;password=224466;encrypt = false;trustServerCertificate = true";
    public static Connection getConnection(){
       Connection conn = null;
       try {
           conn = DriverManager.getConnection(connectUrl);
           System.out.println("Ket noi thanh cong!");
       } catch (SQLException ex){
           Logger.getLogger(Databasee.class.getName()).log(Level.SEVERE,null,ex);
           System.out.println("Ket noi khong thanh cong!");
       }
       return conn;
    }
    public static void main(String[] args) {
        Databasee.getConnection();
    }
}
