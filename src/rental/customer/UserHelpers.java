/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental.customer;

/**
 *
 * @author Qout Alatrouzi
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class UserHelpers {
public static ArrayList<User> users = new ArrayList<>();
 private static final String FILE_NAME = "users.dat";
 private UserHelpers() {
 }
 public static boolean exists(String username) {
 for (User user : users) {
 if (user.getUsername().equalsIgnoreCase(username)) {
 return true;
 }
 }
 return false;
 }
 public static void addUser(User user) {
 users.add(user);
 }
 public static User authenticate(
 String username, String password) {
 for (User user : users) {
 boolean usernameMatches = user.getUsername().equalsIgnoreCase(username);
 boolean passwordMatches = user.getPassword().equals(password);
 if (usernameMatches && passwordMatches) {
 return user;
 }
 }
 return null;
 }
 public static void writeFile() {
 try (ObjectOutputStream output =
 new ObjectOutputStream(
 new FileOutputStream(FILE_NAME))) {
 output.writeObject(users);
 } catch (IOException ex) {
 System.out.println(
 "User file error: " + ex.getMessage());
 }
 }
 @SuppressWarnings("unchecked")
 public static void readFile() {
 File file = new File(FILE_NAME);
 if (!file.exists()) {
 users = new ArrayList<>();
 return;
 }
 try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
 users = (ArrayList<User>) input.readObject();
 } catch (IOException | ClassNotFoundException ex) {
 users = new ArrayList<>();
 System.out.println(
 "User file error: " + ex.getMessage());
}
 }
}
