package vn.viettuts.qlsv.view;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vn.viettuts.qlsv.entity.EmployeeModel;
import vn.viettuts.qlsv.entity.EmployeeXML;
import vn.viettuts.qlsv.entity.Vehicle;
import vn.viettuts.qlsv.entity.VehicleListWrapper;
import vn.viettuts.qlsv.utils.FileUtils;

public class SearchView extends JFrame implements ActionListener, ListSelectionListener {
    private JTextField licensePlateField;
    private JTextField employeeIdField;
    private JTextArea resultArea;
    private JButton searchVehicleButton;
    private JButton searchEmployeeButton;
    private ParkingManagementSystem parentFrame;
    
    public SearchView(ParkingManagementSystem parent) {
        setTitle("Searching Parking Lot Information");
        setSize(900, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.parentFrame = parent;
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchView.this.dispose();
                parent.setVisible(true);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        licensePlateField = new JTextField(15);
        employeeIdField = new JTextField(15);
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        searchVehicleButton = new JButton("Search Vehicle");
        searchEmployeeButton = new JButton("Search Employee");
        JPanel panel = new JPanel();
        panel.add(new JLabel("License Plate:"));
        panel.add(licensePlateField);
        panel.add(searchVehicleButton);
        panel.add(new JLabel("Employee ID:"));
        panel.add(employeeIdField);
        panel.add(searchEmployeeButton);
        add(panel, "North");
        add(new JScrollPane(resultArea), "Center");
        searchVehicleButton.addActionListener(this);
        searchEmployeeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchVehicleButton) {
            performSearch("vehicle");  
        } else if (e.getSource() == searchEmployeeButton) {
            performSearch("employee");  
        }
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
    }
    private void performSearch(String type) {
        String licensePlate = licensePlateField.getText();
        String employeeId = employeeIdField.getText();
        String resultText = "";
        if (type.equals("vehicle") && !licensePlate.isEmpty()) {           
            VehicleListWrapper vehicles = (VehicleListWrapper) FileUtils.readXMLFile("vehicles.xml", VehicleListWrapper.class); 
            Vehicle matchedVehicle = vehicles.getVehicles().stream()
                .filter(v -> v.getLicensePlate().equalsIgnoreCase(licensePlate))
                .findFirst()
                .orElse(null);
            if (matchedVehicle != null) {
                resultText = "Thông tin phương tiện:\n" + matchedVehicle.toString();
            } else {
                resultText = "Không tìm thấy phương tiện nào.";
            }
        } else if (type.equals("employee") && !employeeId.isEmpty()) {
    EmployeeXML employees = (EmployeeXML) FileUtils.readXMLFile("employee.xml", EmployeeXML.class);
      if (employees == null) {
    resultText = "Lỗi khi đọc dữ liệu nhân viên.";
    return;
} else {
    System.out.println("Employees data read successfully.");
    employees.getEmployee().forEach(emp -> System.out.println(emp.getFullName()));
}
    if (employees == null) {
        resultText = "Lỗi khi đọc dữ liệu nhân viên.";
        return;
    }
    EmployeeModel matchedEmployee = employees.getEmployee().stream()
    .filter(emp -> String.valueOf(emp.getId()).equalsIgnoreCase(employeeId))
    .findFirst()
    .orElse(null);
    if (matchedEmployee != null) {
        resultText = "Thông tin nhân viên:\n" + matchedEmployee.toString();
    } else {
        resultText = "Không tìm thấy nhân viên.";
    }
}
        resultArea.setText(resultText);
    }
}