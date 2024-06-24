/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        int status;
        conn = new conectaDAO().connectDB();
        try{
           
            st = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES(?,?,?)");
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            status = st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Produto cadastrado.");
            return status;
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"Erro ao salvar dados no DB: "+sqle.getMessage());
            return sqle.getErrorCode();
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                
            }
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

