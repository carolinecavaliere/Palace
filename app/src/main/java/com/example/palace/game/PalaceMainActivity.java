package com.example.palace.game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import com.example.palace.game.GameMainActivity;
import com.example.palace.game.LocalGame;
import com.example.palace.game.config.GameConfig;

public class PalaceMainActivity extends GameMainActivity {

    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    @Override
    public LocalGame createLocalGame() {
        return null;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
       
    //view class contains the drawing methods
}