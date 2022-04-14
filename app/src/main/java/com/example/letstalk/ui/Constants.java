package com.example.letstalk.ui;

import com.example.letstalk.R;

import java.util.ArrayList;
import java.util.List;

import com.example.letstalk.models.RoomModelClass;

public class Constants {
    public final static List<RoomModelClass> DATA = new ArrayList<RoomModelClass>(){
        {
            add(new RoomModelClass(R.drawable.more,"more"));
            add(new RoomModelClass(R.drawable.name,"name"));
            add(new RoomModelClass(R.drawable.now,"now"));
            add(new RoomModelClass(R.drawable.play,"play"));
            add(new RoomModelClass(R.drawable.she,"she"));
            add(new RoomModelClass(R.drawable.stop,"stop"));
            add(new RoomModelClass(R.drawable.thanks,"thanks"));
            add(new RoomModelClass(R.drawable.them,"them"));
            add(new RoomModelClass(R.drawable.to,"to"));
            add(new RoomModelClass(R.drawable.toilet,"toilet"));
            add(new RoomModelClass(R.drawable.want,"want"));
            add(new RoomModelClass(R.drawable.what,"what"));
            add(new RoomModelClass(R.drawable.you,"you"));
            add(new RoomModelClass(R.drawable.i,"i"));
            add(new RoomModelClass(R.drawable.go,"go"));
            add(new RoomModelClass(R.drawable.and,"and"));
            add(new RoomModelClass(R.drawable.done,"done"));
            add(new RoomModelClass(R.drawable.he,"he"));
            add(new RoomModelClass(R.drawable.drink,"drink"));
            add(new RoomModelClass(R.drawable.food,"food"));
        }
    };
    public static final String TAG = "hazrat";
}
