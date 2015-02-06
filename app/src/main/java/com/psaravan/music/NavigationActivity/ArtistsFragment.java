package com.psaravan.music.NavigationActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psaravan.music.R;

/**
 * The Artists fragment that is displayed within
 * {@link com.psaravan.music.NavigationActivity.NavigationActivity}.
 *
 * @author Saravan Pantham
 */
public class ArtistsFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public ArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artists, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.artists_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        // mRecyclerView.setAdapter(new NumberedAdapter(30));

        return view;
    }

}
