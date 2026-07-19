/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental.customer;

/**
 *
 * @author Qout Alatrouzi
 */
import java.io.*;
import java.util.ArrayList;
public class CustomerHelpers {
  
public static ArrayList<Customer> customers = new ArrayList<>();
   
public static boolean exists(String customerId) {
  for (Customer c : customers) {
    if (c.getCustomerId().trim().equalsIgnoreCase(customerId.trim())) {
        return true;
            }
        }
        return false;
    }
    
public static void writeFile() {
        try {
            String fileName = CustomerHelpers.class.getResource("").getPath() + "/customers.txt";
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(customers); // Save the entire list at once
            out.close();
            fileOut.close();

            System.out.println("Customer list saved successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
public static void readFile() {
        try {
            String fileName = CustomerHelpers.class.getResource("").getPath() + "/customers.txt";
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            customers = (ArrayList<Customer>) in.readObject(); // Load the entire list
            in.close();
            fileIn.close();

            System.out.println("Customers loaded from file successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

