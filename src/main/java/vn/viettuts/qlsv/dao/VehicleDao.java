package vn.viettuts.qlsv.dao;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Unmarshaller;
import vn.viettuts.qlsv.entity.Vehicle;
import vn.viettuts.qlsv.entity.VehicleListWrapper;

public class VehicleDao {
    public static void saveToXML(List<Vehicle> vehicleList, String filePath) {
    try {
        JAXBContext context = JAXBContext.newInstance(VehicleListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        VehicleListWrapper wrapper = new VehicleListWrapper(vehicleList);
        marshaller.marshal(wrapper, new File(filePath));
        System.out.println("Tệp XML đã được lưu tại: " + filePath);  
    } catch (Exception e) {
        e.printStackTrace();
    }
}


   public static List<Vehicle> readFromXML(String filePath) {
    try {
        File xmlFile = new File(filePath);
        if (!xmlFile.exists()) {
            System.out.println("Tệp XML không tồn tại. Tạo mới...");
            return new ArrayList<>(); 
        }

        JAXBContext context = JAXBContext.newInstance(VehicleListWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        VehicleListWrapper wrapper = (VehicleListWrapper) unmarshaller.unmarshal(xmlFile);
        return wrapper.getVehicles();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return new ArrayList<>(); 
}

}

