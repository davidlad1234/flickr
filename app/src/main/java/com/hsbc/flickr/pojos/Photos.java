package com.hsbc.flickr.pojos;/* Copyright 2018 freecodeformat.com */

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/* Time: 2018-09-28 18:32:4 @author freecodeformat.com @website http://www.freecodeformat.com/json2javabean.php */
public class Photos {

    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("description")
    private String description;
    @SerializedName("modified")
    private Date modified;
    @SerializedName("generator")
    private String generator;
    @SerializedName("items")
    private Photo[] photos;

    @Override
    public String toString() {
        return "Photos{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", modified=" + modified +
                ", generator='" + generator + '\'' +
                ", photos=" + photos +
                '}';
    }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setLink(String link) {
         this.link = link;
     }
     public String getLink() {
         return link;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setModified(Date modified) {
         this.modified = modified;
     }
     public Date getModified() {
         return modified;
     }

    public void setGenerator(String generator) {
         this.generator = generator;
     }
     public String getGenerator() {
         return generator;
     }

    public void setPhotos(Photo[] photos) {
         this.photos = photos;
     }
     public Photo[] getPhotos() {
         return photos;
     }

}