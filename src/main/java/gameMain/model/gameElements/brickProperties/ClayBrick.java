package gameMain.model.gameElements.brickProperties;

import java.awt.*;

/**
 * This class specifies the properties of the gray clay brick seen in the gameplay
 *
 * Refactored by:
 * @author LooXuenYi
 *
 */
public class ClayBrick extends Brick {

    //Variable Declaration
    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * This constructor specifies the point (location) of the clay brick and dimensions, with the properties specified in the abstract class "Brick"
     *
     * @param point
     * @param size
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * This method returns the brick's looks from the super class "Brick"
     *
     * @return super.brickFace
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }

}
