package dk.sema.whoisspy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class StartGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
    }

    public void createNewGame(View view) {

        try {
            EditText editText = (EditText) findViewById(R.id.number_of_players);
            int num_players = Integer.parseInt(editText.getText().toString());

            if (num_players < 3) {
                Toast.makeText(StartGameActivity.this, "Minimum 3 players.", Toast.LENGTH_LONG).show();
                return;
            }

            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(GameActivity.NUMBER_OF_PLAYERS, num_players);
            startActivity(intent);

        } catch (NumberFormatException ex) {
            Toast.makeText(StartGameActivity.this, "Number of players must be a positive number.", Toast.LENGTH_SHORT).show();
        }


    }
}
