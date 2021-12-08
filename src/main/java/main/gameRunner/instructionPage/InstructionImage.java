package main.gameRunner.instructionPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This abstract class allows the instruction page to display an image
 *
 * Refactored by:
 * @author LooXuenYi
 */
abstract public class InstructionImage extends JComponent {
    // Variables Declaration
    private BufferedImage img;
    private static final Dimension IMAGE_SIZE = new Dimension(511, 511);

    /**
     * This constructor sets the size of the image display, allows it to be focusable, and checks if there is a valid picture to display
     */
    public InstructionImage()
    {
        setPreferredSize(IMAGE_SIZE);
        this.setFocusable(true);
        this.requestFocusInWindow();

        try {
            img = ImageIO.read(getClass().getResourceAsStream("instructions.jpg"));
            System.out.printf("print");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * This method allows the img to be passed into the constructor
     *
     * @return img
     */
    public BufferedImage img() { return img; }


    /**
     * This method is called each time the image must be redrawn/overwritten
     *
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(img, 0, 0, null);
    }
}
