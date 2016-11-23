package com.example.celebrity_story;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celebrity_story.model.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by abhishek on 22/11/16.
 */

public class CustomGridAdapter extends BaseAdapter {

  private Context context;
  private ArrayList<Story> mStories;

  //Constructor to initialize values
  public CustomGridAdapter(Context context, ArrayList<Story> stories) {

    this.context       = context;
    this.mStories     = stories;
  }

  @Override
  public int getCount() {

    // Number of times getView method call depends upon gridValues.length
    return mStories.size();
  }

  @Override
  public Object getItem(int position) {

    return null;
  }

  @Override
  public long getItemId(int position) {

    return 0;
  }


  // Number of times getView method call depends upon gridValues.length

  public View getView(int position, View convertView, ViewGroup parent) {

    // LayoutInflator to call external grid_item.xml file

    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    ViewHolder gridView;

    if (convertView == null) {

      gridView = new ViewHolder();

      // get layout from grid_item.xml ( Defined Below )

      convertView = inflater.inflate( R.layout.grid_item , null);

      // set value into textview
      gridView.mCardView = (CardView) convertView.findViewById(R.id.item_card);
      gridView.title = (TextView) convertView
          .findViewById(R.id.item_title);

      gridView.likes = (TextView) convertView
          .findViewById(R.id.item_likes);

      gridView.comment = (TextView) convertView
          .findViewById(R.id.item_comment);


      // set image based on selected text

      gridView.mImageView= (ImageView) convertView
          .findViewById(R.id.item_imageView);
      int[] androidColors = context.getResources().getIntArray(R.array.androidcolors);
      int randomAndroidColor = androidColors[new Random().nextInt(8)];
      gridView.mCardView.setCardBackgroundColor(randomAndroidColor);
      convertView.setTag(gridView);
    } else {
      gridView = (ViewHolder) convertView.getTag();
    }
    gridView.title.setText(mStories.get(position).getTitle());
    gridView.likes.setText(mStories.get(position).getLikesCount() + " likes");
    gridView.comment.setText(mStories.get(position).getCommentCount() + " comments");
    Picasso.with(context).setLoggingEnabled(true);
    Picasso.with(context)
        .load(mStories.get(position).getSi())
        .error(R.drawable.dummyimage)
        .resize(200,270)
        .into(gridView.mImageView);

    return convertView;
  }

  static class ViewHolder{
    TextView title,likes,comment;
    CardView mCardView;
    ImageView mImageView;
  }
}
