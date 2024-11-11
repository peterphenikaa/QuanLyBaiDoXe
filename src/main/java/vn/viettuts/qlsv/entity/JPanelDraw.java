package vn.viettuts.qlsv.entity;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class JPanelDraw extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.setColor(Color.DARK_GRAY); 
        g.setFont(new Font("Arial", Font.BOLD, 15));
        String text = "Lưu ý mỗi nhân viên đăng ký tối thiểu 5 ca/1 tuần.";
        g.drawString(text, 5, 300); 
    }
}
