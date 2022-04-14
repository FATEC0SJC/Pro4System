/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author rodin
 */
public class Projeto {
    private String nome;
    private String empresa;
    private String cliente;
    private int id;

    public Projeto(String nome, String empresa, String cliente, int id) {
        this.nome = nome;
        this.empresa = empresa;
        this.cliente = cliente;
        this.id = id;
    }

    public Projeto() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getCliente() {
        return cliente;
    }
    
    public int getId(){
        return id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public void setId (int id) {
        this.id = id;
    }
    
    public String toString() {
        return getNome();
    }
    
}
