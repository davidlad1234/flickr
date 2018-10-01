package com.hsbc.flickr.remote;

import com.hsbc.flickr.pojos.Photos;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FMDataService {
    @GET("photos_public.gne")
    Single<Photos> getPhotos(@Query("tags") String photo,@Query("format") String format,@Query("nojsoncallback") String handlerValue);

}
