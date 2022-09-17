package vttp.ssf.miniproject.ssfminiproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private Integer userId;

    private String username;

    private boolean isLoggedIn;

     private List<Recipe> favourites;

    public User() {
    }

    public User(String username, Integer userId) {
        this.username = username;
        this.userId = userId;
        this.isLoggedIn = false;
        this.favourites = new ArrayList<>();
    }

    public User(String username, Integer userId, boolean isLoggedIn) {
        this.username = username;
        this.userId = userId;
        this.isLoggedIn = isLoggedIn;
        this.favourites = new ArrayList<>();
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public List<Recipe> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Recipe> favourites) {
        this.favourites = favourites;
    }

    public void addToFavourites() {
    }

    public void delFromFavourites() {
    }

    @Override
    public String toString() {
        return "Username: " + this.getUsername() + " with ID " + this.getUserId();
    }
}
