package com.example.palace.game.palace;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import com.example.palace.game.GameMainActivity;
import com.example.palace.game.LocalGame;
import com.example.palace.game.config.GameConfig;
import com.example.palace.game.config.GamePlayerType;
import com.example.palace.game.GamePlayer;

import java.util.ArrayList;

/**
 * @author Jimi Hayes, Nathaniel Pon, Caroline Cavaliere, Chloe Gan, Steve Vegdahl
 *
 * Actually creates the game for it to pop up on your device
 *
 * CAVEATS: None
 *
 */
public class PalaceMainActivity extends GameMainActivity {

    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2278;

    /**
     * create the default configuration for this game:
     *
     * CAVEATS: None
     *
     * @return the new configuration object, representing the default configuration
     */
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Palace has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new PalaceHumanPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new PalaceComputerPlayer(name);
            }
        });

        //playerTypes.add(new GamePlayerType("Smart Computer Player"){
        //public GamePlayer createPlayer(String name){ return new PalaceSmartComputerPlayer(name)
        // ;}});


        // Create a game configuration class for Palace:
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Palace", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }

    /**
     * create a local game
     *
     * @return the local game, a palace game
     */
    @Override
    public LocalGame createLocalGame() {
        GameConfig config = createDefaultConfig();
        return new PalaceLocalGame(config.getNumPlayers());
    }
}