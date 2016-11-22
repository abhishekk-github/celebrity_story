package com.example.celebrity_story;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.celebrity_story.model.Story;
import com.example.celebrity_story.model.User;
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

  Story story;
  User user;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_description);
    init();
    setUpToolBar();
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
  }

  private void init() {
    ImageView imageView = (ImageView)findViewById(R.id.imgBackdrop);
    Intent intent = getIntent();
    String id = intent.getStringExtra("StoryID");
    story = Story.getStory(this,id);
    Picasso.with(this)
        .load(story.getSi())
        .error(R.drawable.dummyimage)
        .into(imageView);
  }

  private void setUpToolBar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(story.getTitle());
    setSupportActionBar(toolbar);
  }
}
