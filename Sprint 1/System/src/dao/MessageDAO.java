/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import factory.ConnectionFactory;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import modelo.Message;
/**
 *
 * @author rodin
 */
public class MessageDAO {
    private Connection connection;
    
    public MessageDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Message message) {
        String sql = "INSERT INTO mensagens(remetente, titulo, texto, data, projeto) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, message.getRemetente());
            stmt.setString(2, message.getTitulo());
            stmt.setString(3, message.getTexto());
            stmt.setString(4, message.getData());
            stmt.setInt(5, message.getProjeto());
            stmt.execute();
            stmt.close();
        }
        catch (SQLException u) {
            throw new RuntimeException (u);
        }
    }
    
    public List<Message> read() throws SQLException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from mensagens");
        rs = stmt.executeQuery();
        
        List<Message> mensagens = new ArrayList<Message>();
        while(rs.next()) {
            Message mensagem = new Message();
            mensagem.setRemetente(rs.getString("remetente"));
            mensagem.setTitulo(rs.getString("titulo"));
            mensagem.setTexto(rs.getString("texto"));
            mensagem.setData(rs.getString("data"));
            mensagens.add(mensagem);
        }
        return mensagens;
    }
    
   public List<Message> search(int idProjeto) throws SQLException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from mensagens where projeto = ?");
        stmt.setInt(1, idProjeto);
        rs = stmt.executeQuery();
        
        List<Message> mensagens = new ArrayList<Message>();
        while(rs.next()) {
            Message mensagem = new Message();
            mensagem.setRemetente(rs.getString("remetente"));
            mensagem.setTitulo(rs.getString("titulo"));
            mensagem.setTexto(rs.getString("texto"));
            mensagem.setData(rs.getString("data"));
            mensagem.setId(rs.getInt("id"));
            mensagens.add(mensagem);
        }
        return mensagens;
    }
   
   public Message searchExactly(String titulo, String remetente, String data, String projeto) throws SQLException{
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from mensagens where titulo = ? and remetente = ? and data = ? and projeto = ?");
        stmt.setString(1, titulo);
        stmt.setString(2, remetente);
        stmt.setString(3, data);
        stmt.setString(4, projeto);
        rs = stmt.executeQuery();
        Message message = new Message();
        while (rs.next()) {
        message.setRemetente(rs.getString("remetente"));
        message.setTitulo(rs.getString("titulo"));
        message.setTexto(rs.getString("texto"));
        message.setData(rs.getString("data"));
        }
        return message;
    }
   
   public Message searchById(int id) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        stmt = connection.prepareStatement("select * from mensagens where id = ?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        
        ClienteDAO clienteDao = new ClienteDAO();
        Message message = new Message();
        while (rs.next()) {
        message.setRemetente(clienteDao.searchByEmail(rs.getString("remetente")).get(0).getNome());
        message.setTitulo(rs.getString("titulo"));
        message.setTexto(rs.getString("texto"));
        message.setData(rs.getString("data"));
        message.setId(id);
        }
        return message;
   }
   
   public void delete(int id) {
       String command0 = "set SQL_SAFE_UPDATES = 0";
        String command1 = "set SQL_SAFE_UPDATES = 1";
        String sql = "delete from mensagens where id = ?";
        try {
            PreparedStatement cm0 = connection.prepareStatement(command0);
            cm0.execute();
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, id);
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