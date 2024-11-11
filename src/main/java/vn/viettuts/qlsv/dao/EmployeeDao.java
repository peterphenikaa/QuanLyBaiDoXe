package vn.viettuts.qlsv.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import vn.viettuts.qlsv.entity.EmployeeModel;
import vn.viettuts.qlsv.entity.EmployeeXML;
import vn.viettuts.qlsv.entity.Vehicle;
import vn.viettuts.qlsv.entity.VehicleListWrapper;
import vn.viettuts.qlsv.utils.FileUtils;

public class EmployeeDao {
    private static final String EMPLOYEE_FILE_NAME = "employee.xml";
    private List<EmployeeModel> listEmployees;

    public EmployeeDao() {
    this.listEmployees = readListEmployees();
    if (listEmployees == null) {
        listEmployees = new ArrayList<>();
    }
}
    public void writeListEmployees(List<EmployeeModel> employees) {
    EmployeeXML employeeXML = new EmployeeXML();
    employeeXML.setEmployee(employees);
    FileUtils.writeXMLtoFile(EMPLOYEE_FILE_NAME, employeeXML);
    System.out.println("Đã lưu " + employees.size() + " nhân viên vào file XML.");
}
    public List<EmployeeModel> readListEmployees() {
    List<EmployeeModel> list = new ArrayList<>();
    try {
        EmployeeXML employeeXML = (EmployeeXML) FileUtils.readXMLFile(EMPLOYEE_FILE_NAME, EmployeeXML.class);
        if (employeeXML != null) {
            list = employeeXML.getEmployee();
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Lỗi khi đọc file: " + EMPLOYEE_FILE_NAME);
    }
    return list;
}
    public void add(EmployeeModel employee) {
    int id = listEmployees.isEmpty() ? 1 : listEmployees.size() + 1; 
    employee.setId(id);
    listEmployees.add(employee);
    writeListEmployees(listEmployees); 
}

public void edit(EmployeeModel employee) {
    for (int i = 0; i < listEmployees.size(); i++) {
        if (listEmployees.get(i).getId() == employee.getId()) {
            listEmployees.set(i, employee); 
            writeListEmployees(listEmployees); 
            break;
        }
    }
}
public boolean delete(EmployeeModel employee) {
    boolean isFound = listEmployees.removeIf(e -> e.getId() == employee.getId()); 
    if (isFound) {
        writeListEmployees(listEmployees); 
    }
    return isFound;
}
    public List<EmployeeModel> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<EmployeeModel> listEmployees) {
        this.listEmployees = listEmployees;
    }  
}
