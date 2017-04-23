package com.maltsev.labyrinth;

class LevelInfo {

    private String description = "";
    private String title = "";
    private String subTitle = "";

    LevelInfo(String title,String subTitle,String description) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
    }

    LevelInfo() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}