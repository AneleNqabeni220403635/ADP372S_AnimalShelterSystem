package za.ac.cput;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImagePanel extends JPanel{

    private String imagePath;

    public ImagePanel()
    {
        super();
        imagePath = "src/za/ac/cput/images/shelter.png";
    }

    public ImagePanel(String imagePath) // if we want to use it for other images potentially
    {
        super();
        this.imagePath = imagePath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            var img2 = ImageIO.read(new File(imagePath));

            g.drawImage(img2, 0, 0, getWidth(), getHeight(), this);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
