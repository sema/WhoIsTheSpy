package dk.sema.whoisspy;

import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PlayerAdapter extends BaseAdapter {
    private Context mContext;
    private Game mGame;

    public PlayerAdapter(Context c, Game game) {
        mContext = c;
        mGame = game;
    }

    public int getCount() {
        return mGame.getNumPlayers();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ImageView imageView;
        TextView labelView;

        if (convertView == null) {  // if it's not recycled, initialize some attributes

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(210, 210));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setId(R.id.player_image);

            labelView = new TextView(mContext);
            labelView.setGravity(Gravity.CENTER_HORIZONTAL);
            labelView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            labelView.setId(R.id.player_name);

            LinearLayout layout = new LinearLayout(mContext);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);
            layout.addView(imageView);
            layout.addView(labelView);

            view = layout;

        } else {

            LinearLayout layout = (LinearLayout)convertView;
            imageView = (ImageView)layout.findViewById(R.id.player_image);
            labelView = (TextView)layout.findViewById(R.id.player_name);
            view = convertView;
        }


        if (mGame.isPlayerAlive(position)) {
            imageView.setImageResource(R.drawable.player);
        } else {
            imageView.setImageResource(R.drawable.player_dead);
        }

        labelView.setText(mGame.getPlayerName(position));

        return view;
    }
}