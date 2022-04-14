package com.example.letstalk.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

public class RoomModelClass {
    String id;
    int btnImage;
    String btnText;

    public RoomModelClass(int btnImage, String btnText) {
        id = UUID.randomUUID().toString();
        this.btnImage = btnImage;
        this.btnText = btnText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBtnImage() {
        return btnImage;
    }

    public void setBtnImage(int btnImage) {
        this.btnImage = btnImage;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    @Override
    public String toString() {
        return "RoomModelClass{" +
                "id='" + id + '\'' +
                ", btnImage=" + btnImage +
                ", btnText='" + btnText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomModelClass that = (RoomModelClass) o;
        return btnImage == that.btnImage && id.equals(that.id) && btnText.equals(that.btnText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, btnImage, btnText);
    }
}
