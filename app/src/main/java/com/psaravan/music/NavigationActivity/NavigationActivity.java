package com.psaravan.music.NavigationActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.psaravan.music.R;

/**
 * Navigation activity. Holds the navigation drawer and the basic browsers.
 * Also the launcher activity.
 *
 * @author Saravan Pantham
 */
public class NavigationActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    // Fragment IDs for the main body contents.
    public static final int ARTISTS_FRAGMENT_ID = 1;
    public static final int ALBUM_ARTISTS_FRAGMENT_ID = 2;
    public static final int ALBUMS_FRAGMENT_ID = 3;
    public static final int SONGS_FRAGMENT_ID = 4;
    public static final int PLAYLISTS_FRAGMENT_ID = 5;
    public static final int GENRES_FRAGMENT_ID = 6;
    public static final int FOLDERS_FRAGMENT_ID = 7;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        getActionBar().setElevation(0.0f);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = getContentFragment(position+1);

        if (fragment!=null)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
    }

    public Fragment getContentFragment(int number) {
        switch (number) {
            case ARTISTS_FRAGMENT_ID:
                return new ArtistsFragment();
            case ALBUM_ARTISTS_FRAGMENT_ID:
                return null;
            case ALBUMS_FRAGMENT_ID:
                return new AlbumsFragment();
            case SONGS_FRAGMENT_ID:
                return null;
            case PLAYLISTS_FRAGMENT_ID:
                return null;
            case GENRES_FRAGMENT_ID:
                return null;
            case FOLDERS_FRAGMENT_ID:
                return null;
        }

        return null;
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case ARTISTS_FRAGMENT_ID:
                mTitle = getString(R.string.artists);
                break;
            case ALBUM_ARTISTS_FRAGMENT_ID:
                mTitle = getString(R.string.album_artists);
                break;
            case ALBUMS_FRAGMENT_ID:
                mTitle = getString(R.string.albums);
                break;
            case SONGS_FRAGMENT_ID:
                mTitle = getString(R.string.songs);
                break;
            case PLAYLISTS_FRAGMENT_ID:
                mTitle = getString(R.string.playlists);
                break;
            case GENRES_FRAGMENT_ID:
                mTitle = getString(R.string.genres);
                break;
            case FOLDERS_FRAGMENT_ID:
                mTitle = getString(R.string.folders);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.navigation, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_navigation, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((NavigationActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
