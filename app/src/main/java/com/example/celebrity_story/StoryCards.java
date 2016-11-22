package com.example.celebrity_story;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.celebrity_story.model.Story;

import java.util.ArrayList;

public class StoryCards extends AppCompatActivity {

  private ArrayList<Story> mStories;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_story_cards);
    mStories = Story.getStoryList(this);
    GridView gridView = (GridView) findViewById(R.id.gridView);

    // Instance of ImageAdapter Class
    gridView.setAdapter(new CustomGridAdapter(this,Story.getStoryList(this)));


    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(StoryCards.this,DescriptionActivity.class);
        intent.putExtra("StoryID",mStories.get(position).getId());
        startActivity(intent);
      }
    });


   }
}
