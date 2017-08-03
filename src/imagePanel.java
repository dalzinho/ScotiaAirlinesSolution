

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class imagePanel extends JPanel {
	private Image image;
	/**
	 * Create the panel.
	 */
	public imagePanel() {
		try {                
	          image = ImageIO.read(new File("plane.png"));
	       } catch (IOException ex) {
	            // handle exception...
	       }

	}    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
      
        Image imageScaled = image.getScaledInstance(this.getParent().getWidth(), this.getParent().getHeight(), image.SCALE_DEFAULT); 
        g.drawImage(imageScaled, 0, 0, this); 
        
    }


}
