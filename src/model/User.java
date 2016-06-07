package model;

import java.util.ArrayList;

/**
 * Created by user on 5/30/2016.
 */
public class User {

    private String name = "";
    private String password = "";

    private ArrayList<Score> scores = new ArrayList<>();

    public void addScore(Score score){
        scores.add(score);
    }

    public void setPassword(String pass){

    }

    public boolean checkPassword(String pass){

        return false;
    }


}