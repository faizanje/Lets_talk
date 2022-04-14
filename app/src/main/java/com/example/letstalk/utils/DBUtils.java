package com.example.letstalk.utils;

import androidx.room.Room;

import com.example.letstalk.models.RoomModelClass;

import java.util.ArrayList;

import io.paperdb.Paper;

public class DBUtils {

    public static String KEY_IS_FIRST_TIME = "KEY_IS_FIRST_TIME";

    public static void saveIsFirstTime(boolean value) {
        Paper.book("others").write(KEY_IS_FIRST_TIME, value);
    }

    public static boolean getIsFirstTime() {
        return Paper.book("others").read(KEY_IS_FIRST_TIME, true);
    }

    public static void saveItem(RoomModelClass roomModelClass) {
        Paper.book().write(roomModelClass.getId(), roomModelClass);
    }

    public static void deleteItem(String id) {
        Paper.book().delete(id);
    }

    public static RoomModelClass readItem(String id) {
        return Paper.book().read(id);
    }

    public static ArrayList<RoomModelClass> readAllItems() {
        ArrayList<RoomModelClass> roomModelClasses = new ArrayList<>();
        for (String key : Paper.book().getAllKeys()) {
            roomModelClasses.add(readItem(key));
        }
        return roomModelClasses;
    }

}
