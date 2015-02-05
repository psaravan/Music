package com.psaravan.music.PlayerActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.psaravan.music.R;
import com.psaravan.music.Utils.App;

/**
 * Player Activity.
 *
 * @author Saravan Pantham
 */
public class PlayerActivity extends Activity {

    // UI elements.
    private ImageView mArtworkImageView;
    private RelativeLayout mControlsLayout;
    private ImageButton mSkipPreviousImageButton;
    private ImageButton mPlayPauseImageButton;
    private ImageButton mSkipNextImageButton;
    private ImageButton mShuffleImageButton;
    private ImageButton mRepeatImageButton;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mArtworkImageView = (ImageView) this.findViewById(R.id.player_artwork);
        mControlsLayout = (RelativeLayout) this.findViewById(R.id.player_controls_layout);
        mSkipPreviousImageButton = (ImageButton) this.findViewById(R.id.player_skip_previous);
        mPlayPauseImageButton = (ImageButton) this.findViewById(R.id.player_play_pause);
        mSkipNextImageButton = (ImageButton) this.findViewById(R.id.player_skip_next);
        mShuffleImageButton = (ImageButton) this.findViewById(R.id.player_shuffle);
        mRepeatImageButton = (ImageButton) this.findViewById(R.id.player_repeat);
        mSeekBar = (SeekBar) this.findViewById(R.id.player_seekbar);

        applyControlsBackground();
    }

    private void applyControlsBackground() {
        Bitmap bitmap = ((BitmapDrawable) mArtworkImageView.getDrawable()).getBitmap();
        if (bitmap==null)
            return;

        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int backgroundColor = palette.getDarkMutedColor(0xAA000000);
                mControlsLayout.setBackgroundColor(backgroundColor);
                App.setStatusColorBarColor(getWindow(), backgroundColor);
            }
        });
    }

    /**
     * Click listener interface for the skip previous button.
     */
    private View.OnClickListener skipPreviousClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    /**
     * Click listener interface for the play button.
     */
    private View.OnClickListener playPauseClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    /**
     * Click listener interface for the skip next button.
     */
    private View.OnClickListener skipNextClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    /**
     * Click listener interface for the shuffle button.
     */
    private View.OnClickListener shuffleClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    /**
     * Click listener interface for the repeat button.
     */
    private View.OnClickListener repeatForwardClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
