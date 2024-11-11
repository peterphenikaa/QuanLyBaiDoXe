package vn.viettuts.qlsv.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fullName;        
    private String phoneNumber;     
    private String gender;          
    private String address;         
    private Date dateOfBirth;       
    private String shift;          
    private int id;
   
    public EmployeeModel() {
    }

    
    public EmployeeModel(String fullName, String phoneNumber, String gender, String address, 
                    Date dateOfBirth, String shift, int id) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.shift = shift;
        this.id = id;
    }
    @XmlElement
 
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @XmlElement
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @XmlElement
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @XmlJavaTypeAdapter(DateAdapter.class)  
    public Date getDateOfBirth() {
    return dateOfBirth;
}


    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @XmlElement
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
    return "ID: " + id + ", Name: " + fullName + ", Phone: " + phoneNumber + ", "
            + "Gender: " + gender + ", Address: " + address + ", "
            + "Date of Birth: " + dateOfBirth + ", Shift: " + shift;
    }

}
