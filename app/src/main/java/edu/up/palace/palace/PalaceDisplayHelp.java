package edu.up.palace.palace;

import com.example.palace.game.R;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 *
 * CAVEATS: None
 *
 * This class displays a help menu to help the player understand the game
 */
public class PalaceDisplayHelp {

    /**
     * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
     *
     * creates a help window to display the rules of the game
     *
     * @param view
     * External Citation
     * Date: 1 December 2020
     * Problem: Create a Popup window
     * Resource:
     * https://medium.com/@evanbishop/popupwindow-in-android-tutorial-6e5a18f49cc7
     * Solution: I used the example code from this post and modified it to fit our design
     */
    public void showPopupWindow(final View view) {


        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.palace_help_menu, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Initialize the elements of our window, install the handler
        final TextView gameSummary = popupView.findViewById(R.id.gameSummary);
        gameSummary.setText("Game Summary: \n\n");
        gameSummary.setTextSize(26);
        final String summary = "Goal: Get rid of all cards in front of you and in your hand \n" +
                "- Your Face-Up Cards (or top cards) cannot be played until your hand is empty. \n" +
                "- Your Face-Down Cards (or bottom cards) cannot be played until your top cards " +
                "are empty.\nTo play your bottom cards, tap on one of the cards and press play." +
                "That card will be moved to your hand and you can then play from there.\n\n " +
                "Rules: \n " +
                "- You must play a card greater than the last card played. If you cannot \n" +
                "beat that card, you must take the pile. By taking the pile, it is your turn to \n" +
                "to play again. \n" +
                "- There are two cards with special abilities: 2 and 10. These \ncards can be" +
                "played at any time, regardless of the rank of \nthe last played card. \n" +
                "- 2: Resets the ranking of the pile. The next player can play \nany card. \n" +
                "- 10: 'Burns' the pile, getting rid of the play pile cards, essentially" +
                "restarting th pile. The player who plays the 10 immediately plays again. \n\n" +
                "GUI Notes: \n" +
                "- The play pile will change colors depending on how many cards are currently " +
                "in the pile. (Yellow for 4-6 cards, Red for more than 6 cards) \n" +
                "- The cards in your hand are automatically sorted. \n" +
                "- Your hand cards will automatically draw a card whenever you play a card " +
                "with 3 cards in your hand. \n" +
                "- Your cards are also automatically sorted from least to greatest.";
        gameSummary.setText(gameSummary.getText() + summary);
        final ImageView image = popupView.findViewById(R.id.demo);
        final ImageView whiteBackground = popupView.findViewById(R.id.whiteBackground);

        Button dismiss = popupView.findViewById(R.id.dismiss);
        Button next = popupView.findViewById(R.id.next);
        Button previous = popupView.findViewById(R.id.previous);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setVisibility(View.VISIBLE);
                whiteBackground.setVisibility(View.INVISIBLE);
                gameSummary.setText("");
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setVisibility(View.INVISIBLE);
                whiteBackground.setVisibility(View.VISIBLE);
                gameSummary.setTextSize(48);
                gameSummary.setText("Game Summary: \n\n");
                gameSummary.setTextSize(30);
                gameSummary.setText(gameSummary.getText() + summary);
            }
        });
    }
}