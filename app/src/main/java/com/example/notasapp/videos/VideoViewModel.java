package com.example.notasapp.videos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VideoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VideoViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue( "Este es el fragmento de videos" );
    }

    public LiveData<String> getText(){
        return mText;
    }
}