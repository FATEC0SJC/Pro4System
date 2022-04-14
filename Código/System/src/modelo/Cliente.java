/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vlb45
 */
public class Cliente {    
    String nome;
    String empresa;
    String cargo;
    String telefone;
    String email;
    String projeto;

    public Cliente(String nome, String empresa, String cargo, String telefone, String email) {
        this.nome = nome;
        this.empresa = empresa;
        this.cargo = cargo;
        this.telefone = telefone;
        this.email = email;
    }

    public Cliente() {
    }
    
    

    public String getNome() {
        return nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }
    
    public String toString(){
        return getNome();
    }
}
