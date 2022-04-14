package com.example.letstalk.ui.home;

public class HomeModelClass {

    int btnImg;
    String btnText;

    public HomeModelClass(int btnImg, String btnText) {
        this.btnImg = btnImg;
        this.btnText = btnText;
    }

    public int getBtnImg() {
        return btnImg;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnImg(int btnImg) {
        this.btnImg = btnImg;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }
}
