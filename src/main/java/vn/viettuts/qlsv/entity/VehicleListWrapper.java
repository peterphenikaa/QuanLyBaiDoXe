package vn.viettuts.qlsv.entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
public class VehicleListWrapper {
    private List<Vehicle> vehicles;

    public VehicleListWrapper() {}

    public VehicleListWrapper(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @XmlElement(name = "vehicle")
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
