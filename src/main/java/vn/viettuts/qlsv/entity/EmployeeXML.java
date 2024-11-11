package vn.viettuts.qlsv.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXML {
    
    private List<EmployeeModel> employee;

    public List<EmployeeModel> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeModel> employee) {
        this.employee = employee;
    }
    
}
