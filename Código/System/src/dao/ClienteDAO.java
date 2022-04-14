/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import factory.ConnectionFactory;
import java.sql.Connection;//conexão SQL para Java
import java.sql.SQLException;//classe de tratamento para exceções
import java.sql.ResultSet;
import modelo.Cliente;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author vlb45
 */
public class ClienteDAO {
    private Connection connection;
    
    public ClienteDAO() throws SQLException, SQLException, SQLException{
        this.connection = new ConnectionFactory().getConnection();
    }
    public void adiciona(Cliente cliente) throws SQLException{
        String sql = "INSERT INTO clientes (nome, empresa, cargo, telefone, email) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmpresa());
                stmt.setString(3, cliente.getCargo());
                stmt.setString(4, cliente.getTelefone());
                stmt.setString(5, cliente.getEmail());
                stmt.execute();
                stmt.close();
            }
        
        catch (SQLException u){
            throw new RuntimeException(u);
        }      
    }
    
    public List<Cliente> read() throws SQLException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from clientes");
        rs = stmt.executeQuery();
        
        List<Cliente> clientes = new ArrayList<Cliente>();
        while(rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setNome(rs.getString("nome"));
            cliente.setEmpresa(rs.getString("empresa"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCargo(rs.getString("cargo"));
            clientes.add(cliente);
        }
        return clientes;
    }
    
   //public String nameByEmail(String email){}
    
   public List<Cliente> search(String nome, String empresa, String cargo) throws SQLException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from clientes where nome like ? and empresa like ? and cargo like ?");
        stmt.setString(1, "%"+nome+"%");
        stmt.setString(2, "%"+empresa+"%");
        stmt.setString(3, "%"+cargo+"%");
        rs = stmt.executeQuery();
        
        List<Cliente> clientes = new ArrayList<Cliente>();
        while(rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setNome(rs.getString("nome"));
            cliente.setEmpresa(rs.getString("empresa"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCargo(rs.getString("cargo"));
            clientes.add(cliente);
        }
        return clientes;
    }
   
   public List<Cliente> searchByEmail(String email) throws SQLException{
       PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from clientes where email = ?");
        stmt.setString(1, email);
        rs = stmt.executeQuery();
        List<Cliente> cliente = new ArrayList<Cliente>();
        while(rs.next()){
            Cliente cli = new Cliente();
            cli.setNome(rs.getString("nome"));
            cli.setEmpresa(rs.getString("empresa"));
            cli.setEmail(rs.getString("email"));
            cliente.add(cli);
        }
        return cliente;
   }
   
  public void update(Cliente cliente, String email) throws SQLException{
        String command0 = "set SQL_SAFE_UPDATES = 0";
        String command1 = "set SQL_SAFE_UPDATES = 1";
        String sql = "update clientes set nome = ?, empresa = ?, cargo = ?, telefone = ?, email = ? where email = ?";
        try {
            PreparedStatement cm0 = connection.prepareStatement(command0);
            cm0.execute();
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmpresa());
                stmt.setString(3, cliente.getCargo());
                stmt.setString(4, cliente.getTelefone());
                stmt.setString(5, cliente.getEmail());
                stmt.setString(6, email);
                stmt.execute();
                stmt.close();
            PreparedStatement cm1 = connection.prepareStatement(command1);
            cm1.execute();
            }
        catch (SQLException u){
            throw new RuntimeException(u);
        }      
    }
  
  public void delete(String email) {
        String command0 = "set SQL_SAFE_UPDATES = 0";
        String command1 = "set SQL_SAFE_UPDATES = 1";
        String sql = "delete from clientes where email = ?";
        try {
            PreparedStatement cm0 = connection.prepareStatement(command0);
            cm0.execute();
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.execute();
                stmt.close();
            PreparedStatement cm1 = connection.prepareStatement(command1);
            cm1.execute();
            }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
  }
  
  public List<String> empresaList()throws SQLException{
      
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from clientes");
        rs = stmt.executeQuery();
        
        List<String> empresas = new ArrayList<String>();
        while(rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setEmpresa(rs.getString("empresa"));
            if (!empresas.contains(cliente.getEmpresa())) {
                empresas.add(cliente.getEmpresa());
            }
        }
        return empresas;
  }
  
  public boolean haveProjetos(String email) throws SQLException{
      PreparedStatement stmt = null;
      ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from projetos where cliente = ?");
        stmt.setString(1, email);
        rs = stmt.executeQuery();

        return rs.next();
  }
}