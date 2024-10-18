package za.ac.cput;
import javax.swing.*;
import java.awt.*;
public class ImagePanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon image = new ImageIcon("shelter.png");
        g.drawImage(image.getImage(), 300, 300, 44, 24, this);
    }
}
