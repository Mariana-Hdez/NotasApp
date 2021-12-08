package com.example.notasapp.audios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MultiViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MultiViewModel(){
        mText=new MutableLiveData<>();
        mText.setValue( "Parte de los audios" );
    }

    public LiveData<String> getText(){
        return mText;
    }
}
