package vn.viettuts.qlsv.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingManagementSystem extends JFrame {

    private JPanel mainPanel;
   
    public ParkingManagementSystem() {
     
        setTitle("Parking Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
  
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 4, 10, 10));
        
       
        JButton employeeButton = createImageButton("/image1.jpg");
        JButton vehicleButton = createImageButton("/image2.jpg");
        JButton parkingLotButton = createImageButton("/iconparkinglot.jpg");
        JButton statsButton = createImageButton("/image4.jpg");
        
        
        mainPanel.add(employeeButton);
        mainPanel.add(vehicleButton);
        mainPanel.add(parkingLotButton);
        mainPanel.add(statsButton);
        
        
        add(mainPanel);
        
        
        setVisible(true);
    }

   
    private JButton createImageButton(String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath)); 
        JButton button = new JButton(icon); 
        button.setPreferredSize(new Dimension(200, 150));
        button.setBorderPainted(false);  
        button.setFocusPainted(false);  
        button.setContentAreaFilled(false); 
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                handleButtonClick(imagePath);
            }
        });
        return button;
    }

    private void handleButtonClick(String imagePath) {
        JFrame newFrame;
        switch (imagePath) {
            case "/image1.jpg":
                newFrame = new EmployeeView(ParkingManagementSystem.this); 
                break;
            case "/image2.jpg":
                newFrame = new VehicleView(ParkingManagementSystem.this); 
                break;
            case "/iconparkinglot.jpg":
                newFrame = new SearchView(ParkingManagementSystem.this); 
                break;
            case "/image4.jpg":
                newFrame = new StatisticView(ParkingManagementSystem.this);
                break;
            default:
                return;
        }
        newFrame.setVisible(true); 
        this.setVisible(false); 
    }
}