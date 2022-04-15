package com.example.letstalk.ui;

import com.example.letstalk.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.letstalk.models.RoomModelClass;

public class Constants {
    public final static List<RoomModelClass> DATA = new ArrayList<RoomModelClass>() {
        {
            add(new RoomModelClass("0", R.drawable.more, "more"));
            add(new RoomModelClass("1", R.drawable.name, "name"));
            add(new RoomModelClass("2", R.drawable.now, "now"));
            add(new RoomModelClass("3", R.drawable.play, "play"));
            add(new RoomModelClass("4", R.drawable.she, "she"));
            add(new RoomModelClass("5", R.drawable.stop, "stop"));
            add(new RoomModelClass("6", R.drawable.thanks, "thanks"));
            add(new RoomModelClass("7", R.drawable.them, "them"));
            add(new RoomModelClass("8", R.drawable.to, "to"));
            add(new RoomModelClass("9", R.drawable.toilet, "toilet"));
            add(new RoomModelClass("10", R.drawable.want, "want"));
            add(new RoomModelClass("11", R.drawable.what, "what"));
            add(new RoomModelClass("12", R.drawable.you, "you"));
            add(new RoomModelClass("13", R.drawable.i, "i"));
            add(new RoomModelClass("14", R.drawable.go, "go"));
            add(new RoomModelClass("15", R.drawable.and, "and"));
            add(new RoomModelClass("16", R.drawable.done, "done"));
            add(new RoomModelClass("17", R.drawable.he, "he"));
            add(new RoomModelClass("18", R.drawable.drink, "drink"));
            add(new RoomModelClass("19", R.drawable.food, "food"));
        }
    };
    public static final String TAG = "hazrat";
}
