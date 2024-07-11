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
import java.util.List;



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
    
    public ArrayList<ProdutosDTO> listarProdutos() {

        conn = new conectaDAO().connectDB();
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM produtos");
            rs = st.executeQuery();

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));

                produtos.add(p);

            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos. ERRO: " + sqle.getMessage());

        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
            }
        }

        return produtos;

    }
    
    public void venderProduto(String id){
       //função deve atualizar o status de um produto para “Vendido”.
       conn = new conectaDAO().connectDB();
        try{
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = "+id;
            st = conn.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Produto Vendido.");
           
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"Produto não encontrado: "+sqle.getMessage());
            
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                
            }
        }
    }
    
    
        
}

