package vn.viettuts.qlsv.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vn.viettuts.qlsv.dao.EmployeeDao;
import vn.viettuts.qlsv.entity.EmployeeModel;
import vn.viettuts.qlsv.entity.JPanelDraw;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class EmployeeView extends JFrame implements ActionListener, ListSelectionListener,MouseListener{
    private static final long serialVersionUID = 1L;
    private ParkingManagementSystem parentFrame;
    public JPopupMenu jPopupMenu;
    private EmployeeDao employeeDao = new EmployeeDao();
    private JButton addEmployeeBtn;
    private JButton editEmployeeBtn;
    private JButton deleteEmployeeBtn;
    private JButton clearBtn;
    private JButton saveBtn;
    private JScrollPane jScrollPaneEmployeeTable;
    private JTable employeeTable;
    private JLabel idLabel;
    private JLabel fullNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel genderLabel;
    private JLabel addressLabel;
    private JLabel dateOfBirthLabel;
    private JLabel shiftLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JTextField idField;
    private JTextField fullNameField;
    private JTextField phoneNumberField;
    private JTextField addressField;
    private JTextField dateOfBirthField; 
    private JTextField userNameField;
    private JTextField passwordField;
    private JTextField confirmPasswordField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> shiftComboBox;
    private String[] columnNames = new String[]{"ID", "Tên", "Số điện thoại", "Giới tính", "Địa chỉ", "Ngày sinh", "Ca làm việc"};
    private Object data = new Object[][]{};
    private JPanelDraw notePanel;
    
    public EmployeeView(ParkingManagementSystem parent) {
        setTitle("Employee Information");
        initComponents();
        setSize(1100, 600); 
        this.parentFrame = parent;
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeView.this.dispose();
                parent.setVisible(true);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    private void initComponents() {
        notePanel = new JPanelDraw();
        notePanel.setPreferredSize(new Dimension(500, 500));
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
        EmployeeView.this.dispose(); 
        new ParkingManagementSystem().setVisible(true);
         }
        });
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);        
        Font font = new Font("Times New Roman", Font.BOLD, 15);
        addEmployeeBtn = new JButton("Thêm");
        addEmployeeBtn.setFont(font);
        addEmployeeBtn.setForeground(Color.WHITE);
        addEmployeeBtn.setBackground(Color.GRAY);       
        editEmployeeBtn = new JButton("Sửa");
        editEmployeeBtn.setFont(font);
        editEmployeeBtn.setForeground(Color.WHITE);
        editEmployeeBtn.setBackground(Color.GRAY);       
        deleteEmployeeBtn = new JButton("Xóa");
        deleteEmployeeBtn.setFont(font);
        deleteEmployeeBtn.setForeground(Color.WHITE);
        deleteEmployeeBtn.setBackground(Color.GRAY);       
        clearBtn = new JButton("Làm mới");
        clearBtn.setFont(font);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setBackground(Color.GRAY);     
        saveBtn = new JButton("Lưu");
        saveBtn.setFont(font);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setBackground(Color.GRAY);       
        jScrollPaneEmployeeTable = new JScrollPane();
        employeeTable = new JTable();
        idLabel = new JLabel("ID:");
        fullNameLabel = new JLabel("Họ tên:");
        phoneNumberLabel = new JLabel("Số điện thoại:");
        genderLabel = new JLabel("Giới tính:");
        addressLabel = new JLabel("Địa chỉ:");
        dateOfBirthLabel = new JLabel("Ngày sinh:");
        shiftLabel = new JLabel("Ca làm việc:");
        userNameLabel = new JLabel("Tài khoản:");
        passwordLabel = new JLabel("Mật khẩu:");
        confirmPasswordLabel = new JLabel("Nhập lại mật khẩu:");
        idField = new JTextField(15);
        fullNameField = new JTextField(15);
        phoneNumberField = new JTextField(15);
        addressField = new JTextField(15);
        dateOfBirthField = new JTextField(15); 
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);
        genderComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"}); // Giới tính
        shiftComboBox = new JComboBox<>(new String[]{"Sáng", "Chiều", "Tối"}); // Ca làm việc
        employeeTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneEmployeeTable.setViewportView(employeeTable);
        jScrollPaneEmployeeTable.setPreferredSize(new Dimension(480, 200));
        jScrollPaneEmployeeTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneEmployeeTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(800, 500);
        panel.setLayout(layout);
        panel.add(jScrollPaneEmployeeTable);
        panel.add(addEmployeeBtn);
        panel.add(editEmployeeBtn);
        panel.add(deleteEmployeeBtn);
        panel.add(clearBtn);
        panel.add(saveBtn);       
        panel.add(idLabel);
        panel.add(fullNameLabel);
        panel.add(phoneNumberLabel);
        panel.add(genderLabel);
        panel.add(addressLabel);
        panel.add(dateOfBirthLabel);
        panel.add(shiftLabel);
        panel.add(userNameLabel);
        panel.add(passwordLabel);
        panel.add(confirmPasswordLabel);        
        panel.add(idField);
        panel.add(fullNameField);
        panel.add(phoneNumberField);
        panel.add(addressField);
        panel.add(dateOfBirthField); 
        panel.add(userNameField);
        panel.add(passwordField);
        panel.add(confirmPasswordField);
        panel.add(genderComboBox); 
        panel.add(shiftComboBox); 
layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, fullNameLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, fullNameLabel, 40, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, fullNameField, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, fullNameField, 40, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, phoneNumberLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, phoneNumberLabel, 70, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, phoneNumberField, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, phoneNumberField, 70, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, genderLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, genderLabel, 100, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, genderComboBox, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, genderComboBox, 100, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, addressLabel, 130, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, addressField, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, addressField, 130, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, dateOfBirthLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, dateOfBirthLabel, 160, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, dateOfBirthField, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, dateOfBirthField, 160, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, shiftLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, shiftLabel, 190, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, shiftComboBox, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, shiftComboBox, 190, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, userNameLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, userNameLabel, 220, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, userNameField, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, userNameField, 220, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, passwordLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, passwordLabel, 250, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, passwordField, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, passwordField, 250, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, confirmPasswordLabel, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, confirmPasswordLabel, 280, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, confirmPasswordField, 100, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, confirmPasswordField, 280, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, jScrollPaneEmployeeTable, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, jScrollPaneEmployeeTable, 320, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.SOUTH, jScrollPaneEmployeeTable, -10, SpringLayout.SOUTH, panel);
layout.putConstraint(SpringLayout.WEST, addEmployeeBtn, 10, SpringLayout.WEST, panel);
layout.putConstraint(SpringLayout.NORTH, addEmployeeBtn, 300, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, editEmployeeBtn, 110, SpringLayout.WEST, panel);  
layout.putConstraint(SpringLayout.NORTH, editEmployeeBtn, 300, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, deleteEmployeeBtn, 210, SpringLayout.WEST, panel);  
layout.putConstraint(SpringLayout.NORTH, deleteEmployeeBtn, 300, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, clearBtn, 310, SpringLayout.WEST, panel);  
layout.putConstraint(SpringLayout.NORTH, clearBtn, 300, SpringLayout.NORTH, panel);
layout.putConstraint(SpringLayout.WEST, saveBtn, 410, SpringLayout.WEST, panel);  
layout.putConstraint(SpringLayout.NORTH, saveBtn, 300, SpringLayout.NORTH, panel);
        add(panel);      
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(notePanel, BorderLayout.EAST);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
        addEmployeeBtn.addActionListener(this);
        editEmployeeBtn.addActionListener(this);
        deleteEmployeeBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        employeeTable.getSelectionModel().addListSelectionListener(this);        
        jMenuItem_exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           System.exit(0); // Thoát ứng dụng
           }
        });
        jMenuItem_open.addActionListener(new ActionListener() {
        @Override
         public void actionPerformed(ActionEvent e) {       
            JOptionPane.showMessageDialog(EmployeeView.this, "Open menu item clicked!");
           }
        });  
}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEmployeeBtn) {            
            addEmployee();
        } else if (e.getSource() == editEmployeeBtn) {
            editEmployee();
        } else if (e.getSource() == deleteEmployeeBtn) {
            deleteEmployee();
        } else if (e.getSource() == clearBtn) {
            clearFields();
        } else if (e.getSource() == saveBtn) {
            saveEmployees();
        } 
    }
    private void addEmployee() {
    String fullName = fullNameField.getText();
    String phoneNumber = phoneNumberField.getText();
    String gender = (String) genderComboBox.getSelectedItem();
    String address = addressField.getText();
    String dateOfBirth = dateOfBirthField.getText();
    String shift = (String) shiftComboBox.getSelectedItem();
    String idString = idField.getText();
    if (idString.isEmpty() || fullName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || dateOfBirth.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
    Date dateOfBirthDate = null;
    try {
        dateOfBirthDate = formatter.parse(dateOfBirth); 
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Định dạng ngày tháng không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    int id;
    try {
        id = Integer.parseInt(idString);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    EmployeeModel employee = new EmployeeModel(fullName, phoneNumber, gender, address, dateOfBirthDate, shift, id);
    employeeDao.add(employee);
    DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
    model.addRow(new Object[]{id, fullName, phoneNumber, gender, address, dateOfBirth, shift});
    clearFields();
    }
    private void editEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            String id = idField.getText();
            String fullName = fullNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String address = addressField.getText();
            String dateOfBirth = dateOfBirthField.getText();
            String shift = (String) shiftComboBox.getSelectedItem();
            employeeTable.setValueAt(id, selectedRow, 0);
            employeeTable.setValueAt(fullName, selectedRow, 1);
            employeeTable.setValueAt(phoneNumber, selectedRow, 2);
            employeeTable.setValueAt(gender, selectedRow, 3);
            employeeTable.setValueAt(address, selectedRow, 4);
            employeeTable.setValueAt(dateOfBirth, selectedRow, 5);
            employeeTable.setValueAt(shift, selectedRow, 6);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            model.removeRow(selectedRow);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearFields() {
        idField.setText("");
        fullNameField.setText("");
        phoneNumberField.setText("");
        addressField.setText("");
        dateOfBirthField.setText("");
        userNameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        genderComboBox.setSelectedIndex(0);
        shiftComboBox.setSelectedIndex(0);
    }
    private void saveEmployees() {
        JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được lưu!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            idField.setText(employeeTable.getValueAt(selectedRow, 0).toString());
            fullNameField.setText(employeeTable.getValueAt(selectedRow, 1).toString());
            phoneNumberField.setText(employeeTable.getValueAt(selectedRow, 2).toString());
            genderComboBox.setSelectedItem(employeeTable.getValueAt(selectedRow, 3));
            addressField.setText(employeeTable.getValueAt(selectedRow, 4).toString());
            dateOfBirthField.setText(employeeTable.getValueAt(selectedRow, 5).toString());
            shiftComboBox.setSelectedItem(employeeTable.getValueAt(selectedRow, 6));
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
