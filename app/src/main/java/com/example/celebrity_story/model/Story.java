package com.example.celebrity_story.model;

import android.content.Context;

import com.example.celebrity_story.Utility.JsonLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by abhishek on 21/11/16.
 */

public class Story {
  String description;
  String id;
  String verb;
  String db;
  String type;
  String title;
  int likesCount;
  int commentCount;
  String si;
  String url;
  Boolean like_flag;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getVerb() {
    return verb;
  }

  public void setVerb(String verb) {
    this.verb = verb;
  }

  public String getDb() {
    return db;
  }

  public void setDb(String db) {
    this.db = db;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(int likesCount) {
    this.likesCount = likesCount;
  }

  public int getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(int commentCount) {
    this.commentCount = commentCount;
  }

  public String getSi() {
    return si;
  }

  public void setSi(String si) {
    this.si = si;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public Boolean getLike_flag() {
    return like_flag;
  }

  public void setLike_flag(Boolean like_flag) {
    this.like_flag = like_flag;
  }

  public static ArrayList<Story> getStoryList(Context context){
    ArrayList<Story> storyArrayList = new ArrayList<>();
    String jsonData = JsonLoader.loadJSONFromAsset(context);
    try {
      JSONArray obj = new JSONArray(jsonData);
      for(int i = 2 ; i < obj.length() ; i++){
        JSONObject userData = obj.getJSONObject(i);
        Story story = new Story();
        story.setDescription(userData.optString("description"));
        story.setId(userData.optString("id"));
        story.setVerb(userData.optString("verb"));
        story.setLikesCount(userData.optInt("likes_count"));
        story.setCommentCount(userData.optInt("comment_count"));
        story.setDb(userData.optString("db"));
        story.setSi(userData.optString("si"));
        story.setUrl(userData.optString("url"));
        story.setType(userData.optString("type"));
        story.setLike_flag(userData.getBoolean("like_flag"));
        story.setTitle(userData.getString("title"));
        storyArrayList.add(story);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return storyArrayList;
  }

  public Story getStory(String id, Context context){
    ArrayList<Story> storyArrayList = getStoryList(context);
    for (Story story: storyArrayList) {
      if(id.equalsIgnoreCase(story.getId())){
        return  story;
      }
    }
    return null;
  }
}
