package com.games.oleg.snake;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by oleg.shlemin on 24.03.2015.
 */
public class ChooseLevelImageAdapter extends BaseAdapter{
    private Context mContext;
    private final int SUQARE_X = 60;
    private final int SUQARE_Y = 60;
    private final int TEXT_SIZE = 5;
    private final int PADDING = 6;

    public ChooseLevelImageAdapter(Context c) {
        mContext = c;
    }


    public int getCount() {
        return 20;
        //return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @TargetApi(16)
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            int dpX = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, SUQARE_X, parent.getResources().getDisplayMetrics());
            int dpY = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, SUQARE_Y, parent.getResources().getDisplayMetrics());
            textView.setLayoutParams(new GridView.LayoutParams(dpX, dpY));
            int sdk = android.os.Build.VERSION.SDK_INT;
            Drawable levelIcon = parent.getResources().getDrawable(R.drawable.blue_square);
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                textView.setBackgroundDrawable(levelIcon);
            } else {
                textView.setBackground(levelIcon);
            }
            textView.setText(Integer.toString(position+1));
            textView.setTextColor(Color.RED);
            textView.setGravity(Gravity.CENTER);
            int textSize = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE, parent.getResources().getDisplayMetrics());
            textView.setTextSize(textSize);
            int paddingSize = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, PADDING, parent.getResources().getDisplayMetrics());
            //textView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView.setPadding(paddingSize, paddingSize, paddingSize, paddingSize);
        } else {
            textView = (TextView) convertView;
        }

        //imageView.setImageResource(R.drawable.blue_square);
        return textView;
    }
}
