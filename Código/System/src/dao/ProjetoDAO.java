/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import factory.ConnectionFactory;
import java.sql.Connection;//conexão SQL para Java
import java.sql.SQLException;//classe de tratamento para exceções
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import modelo.Projeto;


/**
 *
 * @author vlb45
 */
public class ProjetoDAO {
    private Connection connection;
    
    public ProjetoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    public void adiciona(Projeto projeto) throws SQLException{
        String sql = "INSERT INTO projetos (nome, cliente) VALUES(?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, projeto.getNome());
                stmt.setString(2, projeto.getCliente());
                stmt.execute();
                stmt.close();
            }
        
        catch (SQLException u){
            throw new RuntimeException(u);
        }      
    }
    
    public List<Projeto> read() throws SQLException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from projetos");
        rs = stmt.executeQuery();
        
        List<Projeto> projetos = new ArrayList<Projeto>();
        while(rs.next()) {
            ClienteDAO clienteDao = new ClienteDAO();
            Projeto projeto = new Projeto();
            projeto.setNome(rs.getString("nome"));
            projeto.setEmpresa(clienteDao.searchByEmail(rs.getString("cliente")).get(0).getEmpresa());
            projeto.setCliente(rs.getString("cliente"));
            projeto.setId(rs.getInt("id_projeto"));
            projetos.add(projeto);
        }
        return projetos;
    }
    
   public List<Projeto> search(String nome, String empresa, String cliente) throws SQLException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = connection.prepareStatement("select * from projetos where nome like ? and cliente like ?");
        stmt.setString(1, "%"+nome+"%");
        stmt.setString(2, "%"+cliente+"%");
        rs = stmt.executeQuery();
        
        List<Projeto> projetos = new ArrayList<Projeto>();
        while(rs.next()) {
            //boolean condition = true;
            ClienteDAO clienteDao = new ClienteDAO();
            Projeto projeto = new Projeto();
            projeto.setNome(rs.getString("nome"));
            projeto.setEmpresa(clienteDao.searchByEmail(rs.getString("cliente")).get(0).getEmpresa());
            projeto.setCliente(rs.getString("cliente"));
            projeto.setId(rs.getInt("id_projeto"));
            //condition = condition && ((empresa.equals(null)) && projeto.getEmpresa().equals(empresa)) && ((cliente.equals(null)) && projeto.getCliente().equals(cliente));            
            projetos.add(projeto);
        }
        return projetos;
    }
   public int getID (Projeto projeto) throws SQLException {
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       stmt = connection.prepareStatement("select * from projetos where nome = ? and cliente = ?");
        stmt.setString(1, projeto.getNome());
        stmt.setString(2, projeto.getCliente());
        rs = stmt.executeQuery();
        
        return rs.getInt("id_projeto");
   }
   public void update(Projeto projetoNovo) throws SQLException{
        String command0 = "set SQL_SAFE_UPDATES = 0";
        String command1 = "set SQL_SAFE_UPDATES = 1";
        String sql = "update projetos set nome = ?, cliente = ? where id_projeto = ?";
        try {
            PreparedStatement cm0 = connection.prepareStatement(command0);
            cm0.execute();
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, projetoNovo.getNome());
                stmt.setString(2, projetoNovo.getCliente());
                stmt.setInt(3, projetoNovo.getId());
                stmt.execute();
                stmt.close();
            PreparedStatement cm1 = connection.prepareStatement(command1);
            cm1.execute();
            }
        catch (SQLException u){
            throw new RuntimeException(u);
        }      
    }
   
   public void delete(int id_projeto) {
        String command0 = "set SQL_SAFE_UPDATES = 0";
        String command1 = "set SQL_SAFE_UPDATES = 1";
        String sql = "delete from projetos where id_projeto = ?";
        String sqlMensagens = "delete from mensagens where projeto = ?";
        try {
            PreparedStatement cm0 = connection.prepareStatement(command0);
                cm0.execute();
            PreparedStatement stmtMensagem = connection.prepareStatement(sqlMensagens);
                stmtMensagem.setInt(1, id_projeto);
                stmtMensagem.execute();
                stmtMensagem.close();
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, id_projeto);
                stmt.execute();
                stmt.close();
            PreparedStatement cm1 = connection.prepareStatement(command1);
                cm1.execute();
            }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
   }
    }


    


    

