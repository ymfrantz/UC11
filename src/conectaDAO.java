
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

//Declarando variáveis;


public class conectaDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    //Dados do banco;
    String Driver = "com.mysql.cj.jdbc.Driver";
    String DBUrl = "jdbc:mysql://localhost:3306/bancodb";
    String DBUser = "root";
    String DBPassword = "root";
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(DBUrl,DBUser,DBPassword);
            return conn;
            
        } catch (SQLException | ClassNotFoundException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
            return conn;
        }         
    }
    
        }
