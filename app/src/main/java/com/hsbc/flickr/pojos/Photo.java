package com.hsbc.flickr.pojos;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Photo implements Parcelable {

    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("media")
    private Media media;

    @SerializedName("date_taken")
    private Date dateTaken;
    @SerializedName("description")
    private String description;

    @SerializedName("published")
    private Date published;
    @SerializedName("author")
    private String author;
    @SerializedName("author_id")
    private String authorId;
    @SerializedName("tags")
    private String tags;


    public Photo(Parcel in) {
        title = in.readString();
        link = in.readString();
        media = in.readParcelable(Media.class.getClassLoader());
        long tmpDate = in.readLong();
        published = tmpDate == -1 ? null : new Date(tmpDate);
        long tmp1Date = in.readLong();
        dateTaken = tmp1Date == -1 ? null : new Date(tmp1Date);

        description = in.readString();
        author = in.readString();
        authorId = in.readString();
        tags = in.readString();

    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public Photo() {

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

    public void setMedia(Media media) {
         this.media = media;
     }
     public Media getMedia() {
         return media;
     }

    public void setDateTaken(Date dateTaken) {
         this.dateTaken = dateTaken;
     }
     public Date getDateTaken() {
         return dateTaken;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setPublished(Date published) {
         this.published = published;
     }
     public Date getPublished() {
         return published;
     }

    public void setAuthor(String author) {
         this.author = author;
     }
     public String getAuthor() {
         return author;
     }

    public void setAuthorId(String authorId) {
         this.authorId = authorId;
     }
     public String getAuthorId() {
         return authorId;
     }

    public void setTags(String tags) {
         this.tags = tags;
     }
     public String getTags() {
         return tags;
     }

    @Override
    public String toString() {
        return "Photo{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", media=" + media +
                ", dateTaken=" + dateTaken +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", author='" + author + '\'' +
                ", authorId='" + authorId + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(link);
        dest.writeParcelable(media, flags);
        dest.writeLong(published != null ? published.getTime() : -1);
        dest.writeLong(dateTaken != null ? dateTaken.getTime() : -1);

        dest.writeString(description);
        dest.writeString(author);
        dest.writeString(authorId);
        dest.writeString(tags);
    }
}