package gameMain.view.instructionPage;

import gameMain.controller.gameFrameController.GameFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * This class defines the properties in the instruction page
 *
 * Refactored by:
 * @author LooXuenYi
 */
public class InstructionPage extends InstructionImage implements MouseListener, MouseMotionListener {

    // Variables Declaration
    private static final String BACK_TEXT = "Back";
    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(0, 0, 0);//pure black
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};
    private Rectangle menuFace;
    private Rectangle backButton;
    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;
    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;
    private GameFrame owner;
    private InstructionPage instruction;
    private boolean startClicked;
    private boolean drawImage = true;

    /**
     * This constructor includes all the properties that are needed to make the home menu function
     *
     * @param owner
     * @param area
     */
    public InstructionPage(GameFrame owner,Dimension area)
    {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        backButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,backButton.height-2);
    }

    /**
     * This method allows the home menu to be rendered, and includes a new background picture
     *
     * @param g
     */
    public void paint(Graphics g)
    {
        super.paint(g);
        drawMenu((Graphics2D)g);
    }

    /**
     * This method renders the home menu
     *
     * @param g2d
     */
    public void drawMenu(Graphics2D g2d)
    {
        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        drawButton(g2d);

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * This method defines the container that holds the content of the home menu
     *
     * @param g2d
     */
    private void drawContainer(Graphics2D g2d)
    {
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    /**
     * This method renders the shape and size of the button on the home menu
     *
     * @param g2d
     */
    private void drawButton(Graphics2D g2d)
    {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(BACK_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - backButton.width) / 2;
        int y =(int) ((menuFace.height - backButton.height) * 0.8);

        //Start Button Placement
        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - txtRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);

        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,x,y);
        }

        x = backButton.x;
        y = backButton.y;

        y *= 1.2;

    }

    /**
     * This method checks of the mouse is clicked on a button
     *
     * @param mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.enableHomeMenu();
        }

    }

    /**
     * This method checks if a button on the mouse is pressed but not released
     *
     * @param mouseEvent
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            startClicked = true;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    /**
     * This method checks if a pressed button is released on the mouse
     *
     * @param mouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {
        if(startClicked ){
            startClicked = false;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    /**
     * This method checks if a pointer enters the window of the home menu
     *
     * @param mouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    /**
     * This method checks if the pointer has exited the window of the home menu
     *
     * @param mouseEvent
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) { }

    /**
     * This method checks if the pointer is dragging (the window)
     *
     * @param mouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) { }

    /**
     * This method checks if the pointer is moving in the window
     * @param mouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent)
    {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}
