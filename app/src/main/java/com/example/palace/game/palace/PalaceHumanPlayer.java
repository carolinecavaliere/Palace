package com.example.palace.game.palace;

import android.graphics.Color;
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
        if(!(info instanceof PalaceGameState))
        {
            flash(Color.RED, 20);
            return;
        }
        else{
            view.setState((PalaceGameState) info);
            view.invalidate();
        }
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
    public void onClick(View button) {
        if(button.equals(quit)){
            System.exit(0);
        }
        else if(button.equals(restart)){
            flash(Color.RED, 100);
        }
        else if(button.equals(takePile)){
            PalaceTakePileAction takepile = new PalaceTakePileAction(this);
            this.game.sendAction(takepile);
        }
        else if(button.equals(playCard)){
            PalacePlayCardAction playcard = new PalacePlayCardAction(this);
            this.game.sendAction(playcard);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return false;
    }

    @Override
    protected void flash(int color, int duration){
        // get the top view, ignoring if null
        if (view == null) return;

        // save the original background color; set the new background
        // color
        int savedColor = getBackgroundColor(view);
        view.setBackgroundColor(color);

        // set up a timer event to set the background color back to
        // the original.
        myHandler.postDelayed(new Unflash(savedColor), duration);
    }

    private class Unflash implements Runnable {

        // the original color
        private int oldColor;

        // constructor
        public Unflash(int oldColor) {
            this.oldColor = oldColor;
        }

        // method to run at the appropriate time: sets background color
        // back to the original
        public void run() {
            if (view== null) return;
            view.setBackgroundColor(oldColor);
        }
    }
}
