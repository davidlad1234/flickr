package com.hsbc.flickr.presenters;

import android.util.Log;

import com.hsbc.flickr.R;
import com.hsbc.flickr.activities.LocalCallback;
import com.hsbc.flickr.pojos.Photo;
import com.hsbc.flickr.pojos.Photos;
import com.hsbc.flickr.remote.FMDataService;
import com.hsbc.flickr.remote.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.hsbc.flickr.activities.PhotoListActivity.PHOTO;

public class PhotoListPresenter implements ListPresenter {


    private static final String TAG = PhotoListPresenter.class.getSimpleName();
    public static final String HANDLER_VALUE = "1";
    public static final String JSON = "json";
    public boolean isStopped;
    private DisposableSingleObserver<Photos> disposableSingleObserver;


    /**
     * Makes the http request and sends the Results to the adapter.
     */
    public void fetchPhotos(final LocalCallback callback ) {
        Retrofit retroClient = RetrofitClient.getRetrofitInstance();
        FMDataService dataService = retroClient.create(FMDataService.class);

        disposableSingleObserver = dataService.getPhotos(PHOTO, JSON, HANDLER_VALUE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Photos>() {


                    @Override
                    public void onSuccess(Photos response) {
                        if (response != null) {

                            Log.d(TAG, "Received from server: " + response);
                            Photo[] photos  = response.getPhotos();
                             callback.showPhotos(photos);

                        } else {
                            Log.d(TAG, "Error getting response ");
                            callback.showErrorMessage(R.string.server_error);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Error: " + e.getMessage());
                        callback.showErrorMessage(e.getMessage());
                    }
                });
    }

    @Override
    public void onStop() {
        if (disposableSingleObserver != null && !disposableSingleObserver.isDisposed()) {
            disposableSingleObserver.dispose();
        }
        isStopped = true;
    }
}
