package com.psaravan.music.NavigationActivity;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psaravan.music.Adapters.GridCursorAdapter;
import com.psaravan.music.Decorations.GridDividerDecoration;
import com.psaravan.music.R;

/**
 * The Artists fragment that is displayed within
 * {@link com.psaravan.music.NavigationActivity.NavigationActivity}.
 *
 * @author Saravan Pantham
 */
public class AlbumsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int URL_LOADER = 0;
    private RecyclerView mRecyclerView;
    private GridCursorAdapter mAdapter;

    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        /*
         * Initializes the CursorLoader. The URL_LOADER value is eventually passed
         * to onCreateLoader().
         */
        getLoaderManager().initLoader(URL_LOADER, null, this);

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.albums_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new GridDividerDecoration(getActivity()));

        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        /*
         * Takes action based on the ID of the Loader that's being created
         */
        switch (id) {
            case URL_LOADER:
                String[] projection = new String[] {MediaStore.Audio.Albums._ID,
                        MediaStore.Audio.Albums.ALBUM_ART,
                        MediaStore.Audio.Albums.ALBUM,
                        MediaStore.Audio.Albums.ARTIST};

                // Returns a new CursorLoader
                return new CursorLoader(getActivity(),
                    MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    MediaStore.Audio.Albums.ALBUM + " ASC"
                );

            default:
                // An invalid id was passed in
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (mAdapter==null) {
            mAdapter = new GridCursorAdapter(cursor, MediaStore.Audio.Albums.ALBUM_ART,
                    MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST,
                    MediaStore.Audio.Albums._ID);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
