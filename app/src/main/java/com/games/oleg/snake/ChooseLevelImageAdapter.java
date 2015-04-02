package com.games.oleg.snake;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

    /*
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(R.drawable.blue_square);
        return imageView;
    }
*/

    // create a new ImageView for each item referenced by the Adapter
    @TargetApi(16)
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(200, 200));
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
            textView.setTextSize(20);
            //textView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }

        //imageView.setImageResource(R.drawable.blue_square);
        return textView;
    }
}
