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

public class User {
  String about;
  String id;
  String username;
  int followers;
  int following;
  String image;
  String url;
  String userId;
  Boolean isFollowing;
  Long createdOn;

  public int getFollowers() {
    return followers;
  }

  public void setFollowers(int followers) {
    this.followers = followers;
  }

  public void setFollowing(int following) {
    this.following = following;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Boolean getFollowing() {
    return isFollowing;
  }

  public void setFollowing(Boolean following) {
    isFollowing = following;
  }

  public Long getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Long createdOn) {
    this.createdOn = createdOn;
  }

  public static ArrayList<User> getUserList(Context context){
    ArrayList<User> userList = new ArrayList<>();
    String jsonData = JsonLoader.loadJSONFromAsset(context);
    try {
      JSONArray obj = new JSONArray(jsonData);
      for(int i = 0 ; i < 2 ; i++){
        JSONObject userData = obj.getJSONObject(i);
        User user = new User();
        user.setAbout(userData.optString("about"));
        user.setId(userData.optString("id"));
        user.setUsername(userData.optString("username"));
        user.setFollowers(userData.optInt("followers"));
        user.setFollowing(userData.optInt("following"));
        user.setImage(userData.optString("image"));
        user.setUrl(userData.optString("url"));
        user.setUserId(userData.optString("handle"));
        user.setFollowing(userData.getBoolean("is_following"));
        user.setCreatedOn(userData.getLong("createdOn"));
        userList.add(user);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return userList;
  }

}
