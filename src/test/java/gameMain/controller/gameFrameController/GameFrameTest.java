package gameMain.controller.gameFrameController;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameFrameTest {

    GameFrame game = new GameFrame();

    GameFrameTest() throws IOException {
    }

    @Test
    void initialize() {
        assertEquals("Initialized", game.initialize());
    }

    @Test
    void enableGameBoard() {
        assertEquals("Initialized", game.enableGameBoard());
    }

    @Test
    void enableInstructionPage() {
        assertEquals("Initialized", game.enableInstructionPage());
    }
}
