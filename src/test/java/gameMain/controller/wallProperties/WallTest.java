package gameMain.controller.wallProperties;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    Wall wall = new Wall(new Rectangle(0,0,600,450), 30, 3, 6/2, new Point(300,430));

    @Test
    void findImpacts() {
        assertEquals("Pass", wall.findImpacts());
    }

    @Test
    void nextLevel() {
        assertEquals("Pass", wall.nextLevel());
    }
}