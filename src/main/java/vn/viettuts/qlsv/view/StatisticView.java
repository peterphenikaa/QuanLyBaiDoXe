package vn.viettuts.qlsv.view;
import vn.viettuts.qlsv.entity.VehicleListWrapper;
import vn.viettuts.qlsv.entity.Vehicle;
import vn.viettuts.qlsv.utils.FileUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatisticView extends JFrame {
    private JTextField txtDate;
    private JButton btnSearch;
    private JLabel lblTotalMotorbikes;
    private JLabel lblTotalBikes;
    private JLabel lblTotalCars;
    private JLabel lblTotalFee;
    private ParkingManagementSystem parentFrame;
    
    public StatisticView(ParkingManagementSystem parent) {
        this.parentFrame = parent;
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticView.this.dispose();
                parent.setVisible(true);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar); 
        setTitle("Thống Kê");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Ngày (dd/MM/yyyy):"), gbc);
        txtDate = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtDate, gbc);
        btnSearch = new JButton("Tìm kiếm");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnSearch, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Tổng số xe máy:"), gbc);
        lblTotalMotorbikes = new JLabel("0");
        gbc.gridx = 1;
        panel.add(lblTotalMotorbikes, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Tổng số xe đạp:"), gbc);
        lblTotalBikes = new JLabel("0");
        gbc.gridx = 1;
        panel.add(lblTotalBikes, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Tổng số ô tô:"), gbc);
        lblTotalCars = new JLabel("0");
        gbc.gridx = 1;
        panel.add(lblTotalCars, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Tổng phí thu được:"), gbc);
        lblTotalFee = new JLabel("0 VND");
        gbc.gridx = 1;
        panel.add(lblTotalFee, gbc);
        add(panel);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = txtDate.getText();
                performStatistic(date);
            }
        });
    }

    private void performStatistic(String date) {
    VehicleListWrapper vehiclesWrapper = (VehicleListWrapper) FileUtils.readXMLFile("vehicles.xml", VehicleListWrapper.class);
    if (vehiclesWrapper == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu trong file XML!");
        return;
    }
    List<Vehicle> vehicles = vehiclesWrapper.getVehicles();
    int totalMotorbikes = 0;
    int totalBikes = 0;
    int totalCars = 0;
    int totalFee = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    for (Vehicle vehicle : vehicles) {
        try {
            Date entryDate = vehicle.getEntryTime();  
            if (entryDate == null) {
                System.out.println("Entry time is null for vehicle: " + vehicle);
                continue; 
            }
            String formattedEntryDate = sdf.format(entryDate);
            if (formattedEntryDate.equals(date)) {
                switch (vehicle.getVehicleType().toLowerCase()) {
                    case "xe máy":
                        totalMotorbikes++;
                        break;
                    case "xe đạp":
                        totalBikes++;
                        break;
                    case "ô tô":
                        totalCars++;
                        break;
                }
                System.out.println("Fee for vehicle " + vehicle.getVehicleType() + ": " + vehicle.getFee());
                totalFee += vehicle.getFee();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    lblTotalMotorbikes.setText(String.valueOf(totalMotorbikes));
    lblTotalBikes.setText(String.valueOf(totalBikes));
    lblTotalCars.setText(String.valueOf(totalCars));
    lblTotalFee.setText(totalFee + " VND");
    }    
}
