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

public class PalaceMainActivity extends GameMainActivity {

    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2278;

    /**
     * Create the default configuration for this game:
     * - one human player vs. one computer player
     * - minimum of 1 player, maximum of 2
     *
     * @return
     * 		the new configuration object, representing the default configuration
     */
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Pig has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new PalaceHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new PalaceComputerPlayer(name);
            }});

        //playerTypes.add(new GamePlayerType("Smart Computer Player"){
            //public GamePlayer createPlayer(String name){ return new PalaceSmartComputerPlayer(name);}});


        // Create a game configuration class for Pig:
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Palace", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }//createDefaultConfig

    /**
     * create a local game
     *
     * @return
     * 		the local game, a palace game
     */
    @Override
    public LocalGame createLocalGame() {
        GameConfig config = createDefaultConfig();
        return new PalaceLocalGame(config.getNumPlayers());
    }
}