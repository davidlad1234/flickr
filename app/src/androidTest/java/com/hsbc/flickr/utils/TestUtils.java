package com.hsbc.flickr.utils;

import com.hsbc.flickr.pojos.Media;
import com.hsbc.flickr.pojos.Photo;

import java.util.Date;

public class TestUtils {

    public static Photo[] getPhotos() {

        Photo photo = new Photo();
        photo.setTitle("A bebida");
        photo.setAuthor("Dr Who");
        photo.setLink("http://www.flickr.com/photos/142158088@N05/30083918837/");
        Media media = new Media();
        media.setM("");
        photo.setDateTaken(new Date());
        photo.setPublished(new Date());
        photo.setDescription("<p><a href=\"http://www.flickr.com/people/142158088@N05/\">Prizinha Artista Plástica</a> posted a photo:</p>");
        return new Photo[]{photo};
    }
}
