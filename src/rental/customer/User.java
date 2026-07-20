/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental.customer;

/**
 *
 * @author Qout Alatrouzi
 */
import java.io.Serializable;
public class User implements Serializable {
    
 private static final long serialVersionUID = 1L;
 private String username;
 private String password;
 private String customerId;
 private String name;
 public User(String username, String password,
 String customerId, String name) {
 this.username = username;
 this.password = password;
 this.customerId = customerId;
 this.name = name;
 }
 public String getUsername() {
 return username;
 }
 public String getPassword() {
 return password;
 }
 public String getCustomerId() {
 return customerId;
 }
 public String getName() {
 return name;
 }
}

