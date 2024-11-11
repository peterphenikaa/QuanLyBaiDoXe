package vn.viettuts.qlsv.view;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vn.viettuts.qlsv.entity.Vehicle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import vn.viettuts.qlsv.dao.VehicleDao;

public class VehicleView extends JFrame implements ActionListener, ListSelectionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    private ParkingManagementSystem parentFrame;
    private Date selectedVehicleEntryTime;
    private JButton addVehicleBtn;
    private JButton enterBtn;
    private JButton exitBtn;
    private JButton clearBtn;
    private JScrollPane jScrollPaneVehicleTable;
    private JTable vehicleTable;
    private JLabel vehicleNameLabel;
    private JLabel licensePlateLabel;
    private JLabel vehicleTypeLabel;
    private JLabel entryTimeLabel;
    private JLabel exitTimeLabel;
    private JLabel feeLabel;
    private JTextField vehicleNameField;
    private JTextField licensePlateField;
    private JComboBox<String> vehicleTypeComboBox;
    private JTextField entryTimeField;
    private JTextField exitTimeField;
    private JTextField feeField;
    public JPopupMenu jPopupMenu;
    private String[] columnNames = new String[] {
            "Vehicle Name", "License Plate", "Vehicle Type", "Entry Time", "Exit Time", "Fee"
    };
    private List<Vehicle> vehicleList; 

    public VehicleView(ParkingManagementSystem parent) {
        vehicleList = VehicleDao.readFromXML("vehicles.xml"); 
        if (vehicleList == null) {
        vehicleList = new ArrayList<>();
        }
        initComponents();
        showListVehicles(vehicleList);     
        this.parentFrame = parent;
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VehicleView.this.dispose();
                parent.setVisible(true);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);    
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jPopupMenu = new JPopupMenu();       
        JMenu jMenuPopupFont = new JMenu("Font");
        JMenuItem jMenuItemType = new JMenuItem("Type");
        JMenuItem jMenuItemSize = new JMenuItem("Size");
        jMenuPopupFont.add(jMenuItemType);
        jMenuPopupFont.add(jMenuItemSize);        
        JMenuItem jMenuItemCut = new JMenuItem("Cut");
        JMenuItem jMenuItemCopy = new JMenuItem("Copy");
        JMenuItem jMenuItemPaste = new JMenuItem("Paste");       
        jPopupMenu.add(jMenuPopupFont);
        jPopupMenu.add(jMenuItemCut);
        jPopupMenu.add(jMenuItemCopy);
        jPopupMenu.add(jMenuItemPaste);        
        this.addMouseListener(this);        
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu_file = new JMenu("File");
        JMenuItem jMenuItem_open = new JMenuItem("Open", KeyEvent.VK_O);
        jMenuItem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jMenuItem_exit = new JMenuItem("Exit", KeyEvent.VK_F4);
        jMenuItem_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK)); 
        jMenu_file.add(jMenuItem_open);
        jMenu_file.addSeparator();
        jMenu_file.add(jMenuItem_exit);
        jMenuBar.add(jMenu_file);
        this.setJMenuBar(jMenuBar);
        jMenuItem_exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           System.exit(0); 
           }
        });
        jMenuItem_open.addActionListener(new ActionListener() {
        @Override
         public void actionPerformed(ActionEvent e) {   
            JOptionPane.showMessageDialog(VehicleView.this, "Open menu item clicked!");
           }
        });    
        addVehicleBtn = new JButton("Add");
        enterBtn = new JButton("Enter");
        exitBtn = new JButton("Exit");
        clearBtn = new JButton("Clear");
        jScrollPaneVehicleTable = new JScrollPane();
        vehicleTable = new JTable();
        vehicleNameLabel = new JLabel("Vehicle Name");
        licensePlateLabel = new JLabel("License Plate");
        vehicleTypeLabel = new JLabel("Vehicle Type");
        entryTimeLabel = new JLabel("Entry Time");
        exitTimeLabel = new JLabel("Exit Time");
        feeLabel = new JLabel("Fee");
        vehicleNameField = new JTextField(15);
        licensePlateField = new JTextField(15);
        vehicleTypeComboBox = new JComboBox<>(new String[]{"Xe đạp", "Xe máy", "Ô tô"});
        entryTimeField = new JTextField(15);
        exitTimeField = new JTextField(15);
        feeField = new JTextField(15);
        feeField.setEditable(false); 
        vehicleTable.setModel(new DefaultTableModel(new Object[][] {}, columnNames));
        jScrollPaneVehicleTable.setViewportView(vehicleTable);
        jScrollPaneVehicleTable.setPreferredSize(new Dimension(480, 300));
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(jScrollPaneVehicleTable);       
        panel.add(addVehicleBtn);
        panel.add(enterBtn);
        panel.add(exitBtn);
        panel.add(clearBtn);        
        panel.add(vehicleNameLabel);
        panel.add(licensePlateLabel);
        panel.add(vehicleTypeLabel);
        panel.add(entryTimeLabel);
        panel.add(exitTimeLabel);
        panel.add(feeLabel);        
        panel.add(vehicleNameField);
        panel.add(licensePlateField);
        panel.add(vehicleTypeComboBox);
        panel.add(entryTimeField);
        panel.add(exitTimeField);
        panel.add(feeField);
        layout.putConstraint(SpringLayout.WEST, vehicleNameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, vehicleNameLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, vehicleNameField, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, vehicleNameField, 10, SpringLayout.NORTH, panel);       
        layout.putConstraint(SpringLayout.WEST, licensePlateLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, licensePlateLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, licensePlateField, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, licensePlateField, 40, SpringLayout.NORTH, panel);        
        layout.putConstraint(SpringLayout.WEST, vehicleTypeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, vehicleTypeLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, vehicleTypeComboBox, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, vehicleTypeComboBox, 70, SpringLayout.NORTH, panel);       
        layout.putConstraint(SpringLayout.WEST, entryTimeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, entryTimeLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, entryTimeField, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, entryTimeField, 100, SpringLayout.NORTH, panel);       
        layout.putConstraint(SpringLayout.WEST, exitTimeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, exitTimeLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, exitTimeField, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, exitTimeField, 130, SpringLayout.NORTH, panel);       
        layout.putConstraint(SpringLayout.WEST, feeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, feeLabel, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, feeField, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, feeField, 160, SpringLayout.NORTH, panel);        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneVehicleTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneVehicleTable, 10, SpringLayout.NORTH, panel);        
        layout.putConstraint(SpringLayout.WEST, addVehicleBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addVehicleBtn, 200, SpringLayout.NORTH, panel);       
        layout.putConstraint(SpringLayout.WEST, enterBtn, 60, SpringLayout.WEST, addVehicleBtn);
        layout.putConstraint(SpringLayout.NORTH, enterBtn, 200, SpringLayout.NORTH, panel);        
        layout.putConstraint(SpringLayout.WEST, exitBtn, 60, SpringLayout.WEST, enterBtn);
        layout.putConstraint(SpringLayout.NORTH, exitBtn, 200, SpringLayout.NORTH, panel);        
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, exitBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 200, SpringLayout.NORTH, panel);        
        this.add(panel);
        this.pack();
        this.setTitle("Vehicle Information");
        this.setSize(800, 420);
        this.setLocationRelativeTo(null); 
        vehicleTable.getSelectionModel().addListSelectionListener(this);
        addVehicleBtn.addActionListener(this);
        enterBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        clearBtn.addActionListener(this);
}
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    private Vehicle getVehicleInfo() {
        try {
            if (!validateVehicleName() || !validateLicensePlate() || !validateEntryTime() || !validateExitTime()) {
                return null;
            }
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleName(vehicleNameField.getText().trim());
            vehicle.setLicensePlate(licensePlateField.getText().trim());
            vehicle.setVehicleType((String) vehicleTypeComboBox.getSelectedItem());
            Date entryDate = parseDate(entryTimeField.getText().trim());
            if (entryDate == null) return null;
            vehicle.setEntryTime(entryDate);
            String exitTimeStr = exitTimeField.getText().trim();
            if (!exitTimeStr.isEmpty()) {
                Date exitDate = parseDate(exitTimeStr);
                if (exitDate != null) {
                    vehicle.setExitTime(exitDate);
                }
            }
            return vehicle;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    private Date parseDate(String dateString) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
    try {
        return format.parse(dateString);
    } catch (ParseException e) {
        showMessage("Invalid date format. Please use dd/MM/yyyy HH:mm.");
        return null;
    }
    }
    private boolean validateVehicleName() {
        if (vehicleNameField.getText().trim().isEmpty()) {
            showMessage("Vehicle name cannot be empty.");
            return false;
        }
        return true;
    }
    private boolean validateLicensePlate() {
        if (licensePlateField.getText().trim().isEmpty()) {
            showMessage("License plate cannot be empty.");
            return false;
        }
        return true;
    }
    private boolean validateEntryTime() {
        if (entryTimeField.getText().trim().isEmpty()) {
            showMessage("Entry time cannot be empty.");
            return false;
        }
        return true;
    }
    private boolean validateExitTime() {
    if (!exitTimeField.getText().trim().isEmpty()) {
        Date entryTime = parseDate(entryTimeField.getText().trim());
        Date exitTime = parseDate(exitTimeField.getText().trim());
        if (exitTime != null && entryTime != null && exitTime.before(entryTime)) {
            showMessage("Exit time cannot be earlier than entry time.");
            return false;
        }
    }
    return true;
}
    private void clearFields() {
        vehicleNameField.setText("");
        licensePlateField.setText("");
        vehicleTypeComboBox.setSelectedIndex(0);
        entryTimeField.setText("");
        exitTimeField.setText("");
        feeField.setText(""); 
    }
    private String formatDate(Date date) {
    if (date == null) {
        return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    return format.format(date);
}
    public void showListVehicles(List<Vehicle> vehicles) {
    DefaultTableModel model = (DefaultTableModel) vehicleTable.getModel();
    model.setRowCount(0); 
    for (Vehicle vehicle : vehicles) {
        model.addRow(new Object[] {
                vehicle.getVehicleName(),
                vehicle.getLicensePlate(),
                vehicle.getVehicleType(),
                formatDate(vehicle.getEntryTime()),
                formatDate(vehicle.getExitTime()),
                calculateFee(vehicle) 
        });
    }
}
    private String calculateFee(Vehicle vehicle) {
    if (vehicle.getEntryTime() == null || vehicle.getExitTime() == null) {
        return "Invalid time data";
    }
    long diffInMillies = vehicle.getExitTime().getTime() - vehicle.getEntryTime().getTime();
    long diffInHours = diffInMillies / (1000 * 60 * 60); 
    int feePerHour = 0;
    if (vehicle.getVehicleType().equals("Xe máy")) {
        feePerHour = 3000; 
    } else if (vehicle.getVehicleType().equals("Ô tô")) {
        feePerHour = 10000; 
    } else if (vehicle.getVehicleType().equals("Xe đạp")) {
        feePerHour = 0; 
    }
    int totalFee = (int) (diffInHours * feePerHour);
    return String.valueOf(totalFee);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addVehicleBtn) {
            Vehicle vehicle = getVehicleInfo();
            if (vehicle != null) {
                vehicleList.add(vehicle);
                showListVehicles(vehicleList);
                VehicleDao.saveToXML(vehicleList, "vehicles.xml"); 
                clearFields();
            }
        } else if (e.getSource() == enterBtn) {
        } else if(e.getSource() == exitBtn) {
        int selectedRow = vehicleTable.getSelectedRow();
        if (selectedRow >= 0) {
            Vehicle selectedVehicle = vehicleList.get(selectedRow);
            if (selectedVehicleEntryTime != null) {
                String exitTimeStr = exitTimeField.getText().trim();

                Date exitDate = parseDate(exitTimeStr);
                if (exitDate != null) {  
                    if (exitDate.after(selectedVehicleEntryTime)) {
                        selectedVehicle.setExitTime(exitDate);
                        feeField.setText(calculateFee(selectedVehicle));
                        showListVehicles(vehicleList);
                        VehicleDao.saveToXML(vehicleList, "vehicles.xml");
                    } else {
                        showMessage("Thời gian ra không thể sớm hơn thời gian vào.");
                    }
                } else {
                    showMessage("Vui lòng nhập thời gian ra hợp lệ.");
                }
            } else {
                showMessage("Vui lòng nhập thời gian vào trước.");
            }
        } else {
            showMessage("Vui lòng chọn một phương tiện trong bảng.");
        }
    } else if (e.getSource() == clearBtn) {
        clearFields();
    } else if (e.getSource() == addVehicleBtn) {
        Vehicle vehicle = getVehicleInfo();
        if (vehicle != null) {
        vehicleList.add(vehicle);
        showListVehicles(vehicleList);
        VehicleDao.saveToXML(vehicleList, "vehicles.xml"); 
        clearFields();}
        } 
}
    @Override
    public void valueChanged(ListSelectionEvent e) {
    if (e.getSource() == vehicleTable.getSelectionModel()) {
        int selectedRow = vehicleTable.getSelectedRow();
        if (selectedRow >= 0) {
            Vehicle selectedVehicle = vehicleList.get(selectedRow);
            vehicleNameField.setText(selectedVehicle.getVehicleName());
            licensePlateField.setText(selectedVehicle.getLicensePlate());
            vehicleTypeComboBox.setSelectedItem(selectedVehicle.getVehicleType());
            entryTimeField.setText(formatDate(selectedVehicle.getEntryTime()));
            exitTimeField.setText(""); 
            String fee = calculateFee(selectedVehicle);
            feeField.setText(fee); 
            selectedVehicleEntryTime = selectedVehicle.getEntryTime(); 
        }
    }
}
@Override
public void mousePressed(MouseEvent e) {
    showPopup(e);
}
@Override
public void mouseReleased(MouseEvent e) {
    showPopup(e);
}
private void showPopup(MouseEvent e) {
    if (e.isPopupTrigger()) { 
        jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}
@Override
public void mouseClicked(MouseEvent e) {}
@Override
public void mouseEntered(MouseEvent e) {}
@Override
public void mouseExited(MouseEvent e) {}
}