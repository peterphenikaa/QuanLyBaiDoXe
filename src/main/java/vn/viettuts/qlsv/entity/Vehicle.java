package vn.viettuts.qlsv.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String vehicleName;       
    private String licensePlate;       
    private String vehicleType;        
    private Date entryTime;            
    private Date exitTime;             
    private float fee;                
    private String parkingSpot;        

    
    public Vehicle() {
    }

    
    public Vehicle(String vehicleName, String licensePlate, String vehicleType, 
                   Date entryTime, Date exitTime, float fee, String parkingSpot) {
        this.vehicleName = vehicleName;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.fee = fee;
        this.parkingSpot = parkingSpot;
    }

    
    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
    private String parkingLot;
    

   
    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }


    @Override
    public String toString() {
        return "Vehicle{" + "vehicleName=" + vehicleName + 
                ", licensePlate=" + licensePlate + ", vehicleType=" + vehicleType + 
                ", entryTime=" + entryTime + ", exitTime=" + exitTime + ", "
                + "fee=" + fee + ", parkingSpot=" + parkingSpot + ", "
                + "parkingLot=" + parkingLot + '}';
    }
    
    
}
