/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author rodin
 */
public class Message {
    private String titulo;
    private String texto;
    private String remetente;
    private String data;
    private int projeto;
    private int id;

    public Message(String titulo, String texto, String remetente, String data, int projeto) {
        this.titulo = titulo;
        this.texto = texto;
        this.remetente = remetente;
        this.data = data;
        this.projeto = projeto;
    }

    public Message() {
    }
    
    public void setAll(Message message) {
        String titulo = message.getTitulo();
        String texto = message.getTexto();
        String remetente = message.getRemetente();
        String data = message.getData();
        int projeto = message.getProjeto();
        
        this.titulo = titulo;
        this.texto = texto;
        this.remetente = remetente;
        this.data = data;
        this.projeto = projeto;
    }
    
    public int getId(){
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getData() {
        return data;
    }
    
    public int getProjeto(){
        return projeto;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public void setProjeto(int projeto){
        this.projeto = projeto;
    }
}
