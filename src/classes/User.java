/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ANIS
 */

public class User {
    private int id_user,age,etat;
    private String username,mail,mdp,image;
    private String roles,reset_token;
    private String sexe;
    
    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public User(int id_user, String username, String mail, String mdp, String roles, String image,int age, String sexe) {
        this.id_user = id_user;
        this.age = age;
        this.username = username;
        this.mail = mail;
        this.mdp = hashPassword(mdp);
        this.image = image;
        this.sexe = sexe;
        this.roles = roles;        
        
    }

    public User( String username, String mail, String mdp, String roles, String image,int age, String sexe) {
        this.age = age;
        this.username = username;
        this.mail = mail;
        this.mdp = hashPassword(mdp);
        this.image = image;
        this.sexe = sexe;
        this.roles = roles;
        

    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public User() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRole() {
        return roles;
    }

    public void setRole(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", mail=" + mail + ", role=" + roles +  ", age=" + age + '}';
    }



  
}