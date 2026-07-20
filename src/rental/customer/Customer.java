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
public class Customer implements Serializable {

    // Arrtibuts
    private String customerId;
    private String customerName;
    private String phone;
    private String email;
    private String address;
    private String licenseNumber;
    private String licenseExpiryDate;
    private String nationalityOrId;
    private String customerStatus;
    
    // constrecture
    public Customer(String customerId,String customerName,String phone,String email,String address,String licenseNumber,
    String licenseExpiryDate,String nationalityOrId,String customerStatus){
    
        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.licenseExpiryDate = licenseExpiryDate;
        this.address = address;
        this.nationalityOrId = nationalityOrId;
        this.customerStatus = customerStatus;
    
    }
    
    // set&get
    
     public String getCustomerId() { 
        return customerId; 
    }
     public void setCustomerId(String customerId) { 
        this.customerId = customerId; 
    }
     public String getCustomerName() { 
        return customerName; 
    }
     public void setCustomerName(String customerName) { 
        this.customerName = customerName; 
    }
     public String getPhone() { 
        return phone; 
    }
    public void setPhone(String phone) { 
        this.phone = phone; 
    }
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    public String getLicenseNumber() { 
        return licenseNumber; 
    }
    public void setLicenseNumber(String licenseNumber) { 
        this.licenseNumber = licenseNumber; 
    }
    public String getLicenseExpiryDate() { 
        return licenseExpiryDate; 
    }
    public void setLicenseExpiryDate(String licenseExpiryDate) { 
        this.licenseExpiryDate = licenseExpiryDate; 
    }
    public String getAddress() { 
        return address; 
    }
    public void setAddress(String address) { 
        this.address = address; 
    }
    public String getNationalityOrId() { 
        return nationalityOrId; 
    }
    public void setNationalityOrId(String nationalityOrId) { 
        this.nationalityOrId = nationalityOrId; 
    }
    public String getCustomerStatus() { 
        return customerStatus; 
    }
    public void setCustomerStatus(String status) {
    if (status != null) {
        this.customerStatus = status;
    }
}
   @Override
    public String toString() {
        return "Customer{" + "ID='" + customerId + '\'' + ", Name='" + customerName + '\'' + '}';
    }
      
}
