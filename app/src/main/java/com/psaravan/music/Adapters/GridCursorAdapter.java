package com.psaravan.music.Adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.psaravan.music.R;
import com.psaravan.music.Utils.App;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Grid adapter implementation for RecyclerViews.
 *
 * @author Saravan Pantham
 */
public class GridCursorAdapter extends CursorAdapter {

    public static String IMAGE_CURSOR_FIELD;
    public static String TITLE_CURSOR_FIELD;
    public static String SUBTEXT_CURSOR_FIELD;
    public static String ID_CURSOR_FIELD;
    private int mLastPosition = -1;

    /**
     * @param cursor The cursor to use for this adapter.
     * @param imageCursorField The column within the cursor that points to the image path.
     * @param titleCursorField  The column within the cursor that holds the title text.
     * @param subtextCursorField The column within the cursor that holds the sub text.
     */
    public GridCursorAdapter(Cursor cursor, String imageCursorField, String titleCursorField,
                             String subtextCursorField, String idCursorField) {
        super(cursor);
        IMAGE_CURSOR_FIELD = imageCursorField;
        TITLE_CURSOR_FIELD = titleCursorField;
        SUBTEXT_CURSOR_FIELD = subtextCursorField;
        ID_CURSOR_FIELD = idCursorField;
    }

    @Override
    public void onBindViewHolderCursor(RecyclerView.ViewHolder holder, Cursor cursor) {
        String imagePath = null;
        String title = null;
        String subtext = null;
        long id = -1;
        if (IMAGE_CURSOR_FIELD!=null)
            imagePath = cursor.getString(cursor.getColumnIndex(IMAGE_CURSOR_FIELD));

        if (TITLE_CURSOR_FIELD!=null)
            title = cursor.getString(cursor.getColumnIndex(TITLE_CURSOR_FIELD));

        if (SUBTEXT_CURSOR_FIELD!=null)
            subtext = cursor.getString(cursor.getColumnIndex(SUBTEXT_CURSOR_FIELD));

        if (ID_CURSOR_FIELD!=null)
            id = cursor.getLong(cursor.getColumnIndex(ID_CURSOR_FIELD));

        final GridViewHolder gridViewHolder = (GridViewHolder) holder;
        gridViewHolder.mTitle.setText(title);
        gridViewHolder.mSubText.setText(subtext);
        Picasso.with(App.getContext()).load(new File(imagePath)).into(gridViewHolder.mImage);
        setAnimation(((GridViewHolder) holder).mGridItemContainer, cursor.getPosition());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_grid_item, viewGroup, false);

        //Set the dimensions of the ImageView to be square.
        int[] screenDimens = App.getScreenDimensions();
        ImageView imageView = (ImageView) itemView.findViewById(R.id.grid_item_image);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        params.height = screenDimens[0]/2;
        imageView.setLayoutParams(params);

        return new GridViewHolder(itemView);
    }

    /**
     * Slides the specified view from the bottom to the top.
     * @param view The view to animate.
     * @param position The position of the view.
     */
    private void setAnimation(View view, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated.
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(App.getContext(),
                    R.anim.slide_in_bottom);
            view.startAnimation(animation);
            mLastPosition = position;
        }
    }

    /**
     * Holder implementation for the GridView adapter.
     *
     * @author Saravan Pantham
     */
    static class GridViewHolder extends RecyclerView.ViewHolder {
        protected TextView mTitle;
        protected TextView mSubText;
        protected LinearLayout mInfoLayout;
        protected ImageView mImage;
        protected LinearLayout mGridItemContainer;

        public GridViewHolder(View v) {
            super(v);
            mTitle =  (TextView) v.findViewById(R.id.grid_item_title);
            mSubText = (TextView) v.findViewById(R.id.grid_item_subtext);
            mInfoLayout = (LinearLayout) v.findViewById(R.id.grid_item_info_layout);
            mImage = (ImageView)  v.findViewById(R.id.grid_item_image);
            mGridItemContainer = (LinearLayout) v.findViewById(R.id.grid_item_container);
        }
    }
}

