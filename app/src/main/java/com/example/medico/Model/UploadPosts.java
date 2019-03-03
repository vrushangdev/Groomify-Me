package com.example.medico.Model;

import com.example.medico.Model.BlogPostId;
import com.google.firebase.database.ServerValue;


public class UploadPosts extends BlogPostId{

    private String postKey;
    private String uploadImageUrl;
    private String uploadTitle;
    private String uploadSubject;
    private String uploadId;
    private Object timeStamp ;
    private String uploadTitleHindi;
    private String uploadSubjectHindi;
    private String uploadCat;

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public UploadPosts(String uploadImageUrl, String uploadTitle, String uploadSubject,String uploadTitleHindi,String uploadSubjectHindi, String uploadId, String uploadCat) {
        this.uploadImageUrl = uploadImageUrl;
        this.uploadTitle = uploadTitle;
        this.uploadSubject = uploadSubject;
        this.uploadId = uploadId;
        this.timeStamp = ServerValue.TIMESTAMP;
        this.uploadTitleHindi=uploadTitleHindi;
        this.uploadSubjectHindi=uploadSubjectHindi;
        this.uploadCat=uploadCat;
    }

    public UploadPosts() {

    }

    public String getUploadImageUrl() {
        return uploadImageUrl;
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

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
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

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getUploadCat() {
        return uploadCat;
    }

    public void setUploadCat(String uploadCat) {
        this.uploadCat=uploadCat;
    }
}