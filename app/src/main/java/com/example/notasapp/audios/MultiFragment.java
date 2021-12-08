package com.example.notasapp.audios;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MultiFragment extends Fragment {

    private ActivityResultLauncher<String[]> launcherPermision;
    private ActivityResultLauncher<String[]> launcherSAF;
    private MediaPlayer mediaPlayer;
    private Button btnMul,btnGr,btnRe;
    MediaRecorder recorder;
    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;
    List<Video> videoList = new ArrayList<Video>();
    private boolean permissionToRecordAcepted = false;
    private String[] permissions =
            {Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private boolean permissionToWriteAcepted = false;
    MultiViewModel videoViewModel;

    @Override public void onAttach(@NonNull Context context){
        super.onAttach( context );
        validarAudioWriteExternal();

        launcherSAF = registerForActivityResult(
                new ActivityResultContracts.OpenDocument(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Log.i("SAFUriResult", result.toString());
                        startActivity( new Intent(
                                Intent.ACTION_VIEW
                        ).setData( result ) );
                    }
                }
        );
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        videoViewModel = new ViewModelProvider( this ).get( MultiViewModel.class);
        View root=null;
        return  root;
    }

    private void validarAudioWriteExternal(){
        launcherPermision = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions()
                , new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> result) {
                        if (result.get( permissions[0] )){
                            permissionToRecordAcepted = true;
                        }
                        if (result.get( permissions[1] )){
                            permissionToWriteAcepted = true;
                        }

                        btnGr.setEnabled( permissionToRecordAcepted );
                        btnRe.setEnabled( permissionToWriteAcepted );
                    }
                }
        );
    }

}
