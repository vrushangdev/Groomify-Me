package com.example.medico.Model;

public class MyPostModel extends BlogPostId{
    private String postKey;
    private String uploadImageUrl;
    private String uploadTitle;
    private String uploadSubject;
    private String uploadId;

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getUploadImageUrl() {
        return uploadImageUrl;
    }

    public void setUploadImageUrl(String uploadImageUrl) {
        this.uploadImageUrl = uploadImageUrl;
    }

    public String getUploadTitle() {
        return uploadTitle;
    }

    public void setUploadTitle(String uploadTitle) {
        this.uploadTitle = uploadTitle;
    }

    public String getUploadSubject() {
        return uploadSubject;
    }

    public void setUploadSubject(String uploadSubject) {
        this.uploadSubject = uploadSubject;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUploadTitleHindi() {
        return uploadTitleHindi;
    }

    public void setUploadTitleHindi(String uploadTitleHindi) {
        this.uploadTitleHindi = uploadTitleHindi;
    }

    public String getUploadSubjectHindi() {
        return uploadSubjectHindi;
    }

    public void setUploadSubjectHindi(String uploadSubjectHindi) {
        this.uploadSubjectHindi = uploadSubjectHindi;
    }

    private Object timeStamp ;
    private String uploadTitleHindi;
    private String uploadSubjectHindi;

}
