package com.example.letstalk.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.letstalk.databinding.FragmentHomeBinding;
import com.example.letstalk.ui.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.letstalk.models.RoomModelClass;
import com.example.letstalk.utils.DBUtils;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ArrayList<RoomModelClass> newList = new ArrayList<>();
    TextToSpeech textToSpeech;
    HomeAdapterV2 mAdapter;
    boolean isInitialized = false, shouldIncrementPhrase = false;
    String lastPlayedText = "";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textToSpeech = new TextToSpeech(requireContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                    isInitialized = true;
                }
            }

        });

        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {

            }

            @Override
            public void onDone(String utteranceId) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show();
                        if(shouldIncrementPhrase){
                        String currentText = binding.etPhrases.getText().toString();
                            binding.etPhrases.setText(currentText + " " + lastPlayedText);

                        }
                    }
                });
            }

            @Override
            public void onError(String utteranceId) {

            }
        });

        binding.btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = binding.etPhrases.getText().toString();
                if(!currentText.isEmpty()){
                    shouldIncrementPhrase = false;
                    speak(currentText);
                }
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etPhrases.setText("");
            }
        });
        if (DBUtils.getIsFirstTime()) {
            Log.d(Constants.TAG, "onCreate: getIsFirstTime true");
            DBUtils.saveIsFirstTime(false);
            for (int i = 0; i < 7; i++) {
                Log.d(Constants.TAG, "onCreate: item " + Constants.DATA.get(i));
                DBUtils.saveItem(Constants.DATA.get(i));
            }
        } else {
            Log.d(Constants.TAG, "onCreate: getIsFirstTime false");
        }

        newList.addAll(DBUtils.readAllItems());
        mAdapter = new HomeAdapterV2(getContext(), newList);
        binding.rvHome.setItemAnimator(new DefaultItemAnimator());
        binding.rvHome.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvHome.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnRoomModelClassClickListener(new HomeAdapterV2.OnRoomModelClassClickListener() {
            @Override
            public void onRoomModelClassClicked(int position, RoomModelClass roomModelClass) {
//                if(textToSpeech.isSpeaking()){
//                    textToSpeech.stop();
//                }
                shouldIncrementPhrase = true;
                speak(roomModelClass.getBtnText());
                lastPlayedText = roomModelClass.getBtnText();
            }

            @Override
            public void onDeleteClicked(int position, RoomModelClass roomModelClass) {
                newList.remove(roomModelClass);
                mAdapter.notifyItemRemoved(position);
                DBUtils.deleteItem(roomModelClass.getId());

            }
        });


        binding.extendedFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<RoomModelClass> missingItems = intersection(newList, Constants.DATA);

//                if (missingItems.isEmpty()) {
//                    Toast.makeText(requireContext(), "No new data found", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                RoomModelClass roomModelClass = missingItems.get(0);
                RoomModelClass roomModelClass = getFirstMissingItem();

                if (roomModelClass == null) {
                    Toast.makeText(requireContext(), "No new data found", Toast.LENGTH_SHORT).show();
                    return;
                }
                newList.add(roomModelClass);
                mAdapter.notifyItemInserted(newList.size() - 1);
//                mAdapter.notifyDataSetChanged();
                DBUtils.saveItem(roomModelClass);
            }
        });
        return root;
    }

    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }

    private RoomModelClass getFirstMissingItem() {
        for (RoomModelClass datum : Constants.DATA) {
            boolean exists = false;
            for (RoomModelClass roomModelClass : newList) {
                Log.d(Constants.TAG, String.format("%s == %s: %s", roomModelClass, datum, roomModelClass.equals(datum)));
                if (roomModelClass.equals(datum)) {
                    exists = true;
                }
            }
            if (!exists) {
                Log.d(Constants.TAG, "!exists: returning " + datum.getBtnText());
                return datum;
            }
        }
        return null;
    }

    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}