package com.sheriffs.babysheriff.model;

public class DiaryData {

    private int iv_diarylist;
    private String tv_day;
    private String tv_diary;

    public DiaryData(int iv_diarylist, String tv_day, String tv_diary) {
        this.iv_diarylist = iv_diarylist;
        this.tv_day = tv_day;
        this.tv_diary = tv_diary;
    }

    public int getIv_diarylist() {
        return iv_diarylist;
    }

    public void setIv_diarylist(int iv_diarylist) {
        this.iv_diarylist = iv_diarylist;
    }

    public String getTv_day() {
        return tv_day;
    }

    public void setTv_day(String tv_day) {
        this.tv_day = tv_day;
    }

    public String getTv_diary() {
        return tv_diary;
    }

    public void setTv_diary(String tv_diary) {
        this.tv_diary = tv_diary;
    }
}
