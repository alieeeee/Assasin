package com.parse.starter;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Victim Description")
    public class victimDescription extends ParseObject {
        public victimDescription(){

        }
        public int getDistance(){
            return getInt("distance");
        }

        public void setDistance(int distance){
            put("distance", distance);
        }

        public int getScore(){
            return getInt("score");
        }

        public void setScore(int score){
            put("score", score);
        }
    }