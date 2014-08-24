package dk.sema.whoisthespy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import dk.sema.whoisthespy.game.State;


public class GameActivity extends Activity {

    public static final String NUMBER_OF_PLAYERS = "number_of_players";

    private State mState;
    private PlayerAdapter mPlayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int num_players = getIntent().getIntExtra(NUMBER_OF_PLAYERS, 1);
        mState = new State(num_players);

        mPlayerAdapter = new PlayerAdapter(this, mState);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(mPlayerAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle(mState.getPlayerName(position))
                       .setItems(R.array.player_actions,
                               new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog, int which) {

                                        if (which == 0) { // show dialog
                                           Toast.makeText(GameActivity.this, mState.getWord(position), Toast.LENGTH_LONG).show();

                                        } else { // kill
                                            mState.killPlayer(position);
                                            mPlayerAdapter.notifyDataSetInvalidated(); // this should have been implemented as a callback between game and adapter

                                            if (mState.isGameFinished()) {

                                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                                                builder.setTitle("State finished");
                                                builder.setMessage("The spy was " + mState.getPlayerName(mState.getSpyId()));
                                                builder.setCancelable(false);
                                                builder.setPositiveButton("New State", new DialogInterface.OnClickListener() {
                                                   public void onClick(DialogInterface dialog, int which) {
                                                        mState.newGame();
                                                        mPlayerAdapter.notifyDataSetInvalidated();
                                                   }
                                                });

                                                builder.create().show();

                                            }
                                        }

                                   }
                       });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_skip_round) {
            mState.newGame();
            mPlayerAdapter.notifyDataSetInvalidated();
        }
        return super.onOptionsItemSelected(item);
    }
}
