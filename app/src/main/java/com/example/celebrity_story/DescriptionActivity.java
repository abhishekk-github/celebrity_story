package com.example.celebrity_story;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celebrity_story.model.Story;
import com.example.celebrity_story.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {

  Story story;
  User user;
  DBHelper dbHelper;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_description);
    init();
    setUpToolBar();
  }

  private void init() {
    dbHelper = new DBHelper(this);
    ImageView imageView = (ImageView)findViewById(R.id.imgBackdrop);
    ImageView userImageView = (ImageView)findViewById(R.id.user_image);
    TextView userNameTV = (TextView)  findViewById(R.id.user_name);
    TextView userInfoTV = (TextView)  findViewById(R.id.user_info);
    TextView userFollowTV = (TextView)  findViewById(R.id.user_follow);

    TextView descriptionTV = (TextView)  findViewById(R.id.story_description);
    TextView likes = (TextView)  findViewById(R.id.story_likes);
    TextView comments = (TextView)  findViewById(R.id.story_comments);

    Intent intent = getIntent();
    String id = intent.getStringExtra("StoryID");
    story = Story.getStory(this,id);
    Picasso.with(this)
        .load(story.getSi())
        .error(R.drawable.dummyimage)
        .into(imageView);
    user = User.getUser(story.getDb(),this);
    
    descriptionTV.setText(story.getDescription());
    likes.setText(story.getLikesCount() + " likes" );
    comments.setText(story.getCommentCount() + " comments" );

    if(user != null) {
      Picasso.with(this)
          .load(user.getImage())
          .error(R.drawable.ic_favorite_black_24dp)
          .into(userImageView);

      userNameTV.setText(user.getUsername());
      userInfoTV.setText(user.getAbout());
      userFollowTV.setText(user.getFollowers() + " followers");

      updateFAB();
    }
  }

  private void updateFAB() {
    final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    ArrayList<String> ids = dbHelper.getAllID();
    if(ids.contains(user.getId())) {
      fab.setImageResource(R.drawable.ic_favorite_black_24dp);
    }
      else{
        fab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ArrayList<String> ids = dbHelper.getAllID();
        if(ids.contains(user.getId())){
          dbHelper.deleteFollow(user.getId());
          fab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
          Snackbar.make(view, "You unfollow " + user.getUsername(), Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();
        }
        else{
          dbHelper.insertFollow(user.getId(),true);
          fab.setImageResource(R.drawable.ic_favorite_black_24dp);
          Snackbar.make(view, "You are following " + user.getUsername(), Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();
        }
      }
    });
  }

  private void setUpToolBar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(story.getTitle());
    setSupportActionBar(toolbar);
  }
}
