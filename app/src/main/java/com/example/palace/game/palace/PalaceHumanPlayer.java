package com.example.palace.game.palace;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.palace.game.GameHumanPlayer;
import com.example.palace.game.GameMainActivity;
import com.example.palace.game.GamePlayer;
import com.example.palace.game.R;
import com.example.palace.game.infoMsg.GameInfo;

public class PalaceHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {
    private PalaceView view;
    private Button takePile;
    private Button playCard;
    private Button quit;
    private Button restart;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    private GameMainActivity myActivity;
    public PalaceHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.palace_layout);

        view = (PalaceView)activity.findViewById(R.id.surfaceView);
        takePile = (Button)activity.findViewById(R.id.takepile);
        playCard = (Button)activity.findViewById(R.id.playcard);
        quit = (Button)activity.findViewById(R.id.quit_button);
        restart = (Button)activity.findViewById(R.id.restart_button);

        view.setOnTouchListener(this);
        takePile.setOnClickListener(this);
        playCard.setOnClickListener(this);
        quit.setOnClickListener(this);
        restart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
