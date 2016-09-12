package com.elin4it.ssm.pojo;

public class News {
    private Integer newsId;

    private String newsType;

    private String newsTitle;

    private String newsAuthor;

    private String newsContent;

    private String editorTime;

    private String newsProgram;

    private String newsStatus;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType == null ? null : newsType.trim();
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor == null ? null : newsAuthor.trim();
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }

    public String getEditorTime() {
        return editorTime;
    }

    public void setEditorTime(String editorTime) {
        this.editorTime = editorTime == null ? null : editorTime.trim();
    }

    public String getNewsProgram() {
        return newsProgram;
    }

    public void setNewsProgram(String newsProgram) {
        this.newsProgram = newsProgram == null ? null : newsProgram.trim();
    }

    public String getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(String newsStatus) {
        this.newsStatus = newsStatus == null ? null : newsStatus.trim();
    }
}