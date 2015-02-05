package com.psaravan.music.NavigationActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.psaravan.music.R;

import java.util.List;

/**
 * ArrayAdapter implementation for the Nav Drawer.
 *
 * @author Saravan Pantham
 */
public class NavigationDrawerAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> mListItems;

    public NavigationDrawerAdapter(Context context, int resource, List<String> list) {
        super(context, resource, list);
        mContext = context;
        mListItems = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_navigation_drawer, null);

            holder = new Holder();
            holder.mTitleTextView = (TextView) view.findViewById(R.id.adapter_nav_drawer_text);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.mTitleTextView.setText(mListItems.get(position));

        return view;
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    static class Holder {
        TextView mTitleTextView;
    }
}
