package com.example.notasapp.videos;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class GrabarVideo extends Fragment {

    private VideoViewModel videoViewModel;

    private Button btnGr,btnEl;
    private VideoView videoV;
    private Uri urivideo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        videoViewModel = new ViewModelProvider( this ).get( VideoViewModel.class );

        View root = null;
        return root;

    }

}
