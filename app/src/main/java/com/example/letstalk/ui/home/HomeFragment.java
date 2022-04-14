package com.example.letstalk.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.letstalk.R;
import com.example.letstalk.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import Room.MyDatabase;
import Room.RoomModelClass;
import io.paperdb.Paper;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    List<RoomModelClass> talkList= new ArrayList<>();
    List<RoomModelClass> newList = new ArrayList<>();
    MyDatabase myDatabase;
    MyDatabase newDatabase;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Paper.init(getContext());
        setupDB();


        if(newDatabase.dao().getTalkList().size()==0){

            newList.add(new RoomModelClass(R.drawable.more,"more"));
            newList.add(new RoomModelClass(R.drawable.name,"name"));
            newList.add(new RoomModelClass(R.drawable.now,"now"));
            newList.add(new RoomModelClass(R.drawable.play,"play"));
            newList.add(new RoomModelClass(R.drawable.she,"she"));
            newList.add(new RoomModelClass(R.drawable.stop,"stop"));
            newList.add(new RoomModelClass(R.drawable.thanks,"thanks"));
            newList.add(new RoomModelClass(R.drawable.them,"them"));
            newList.add(new RoomModelClass(R.drawable.to,"to"));
            newList.add(new RoomModelClass(R.drawable.toilet,"toilet"));
            newList.add(new RoomModelClass(R.drawable.want,"want"));
            newList.add(new RoomModelClass(R.drawable.what,"what"));
            newList.add(new RoomModelClass(R.drawable.you,"you"));
            newDatabase.dao().talkInsertion(newList);

        }
        else{
            Toast.makeText(getContext(), "New list: "+newList.size(), Toast.LENGTH_SHORT).show();
        }



        if(myDatabase.dao().getTalkList().isEmpty()){
            talkList.add(new RoomModelClass(R.drawable.i,"i"));
            talkList.add(new RoomModelClass(R.drawable.go,"go"));
            talkList.add(new RoomModelClass(R.drawable.and,"and"));
            talkList.add(new RoomModelClass(R.drawable.done,"done"));
            talkList.add(new RoomModelClass(R.drawable.he,"he"));
            talkList.add(new RoomModelClass(R.drawable.drink,"drink"));
            talkList.add(new RoomModelClass(R.drawable.food,"food"));
            myDatabase.dao().talkInsertion(talkList);
            setAdapter(myDatabase.dao().getTalkList());

        }
        else{
//            myDatabase.dao().deleteAllList();
            setAdapter(myDatabase.dao().getTalkList());
        }
        binding.extendedFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newDatabase.dao().getTalkList().size()>=1){
                Toast.makeText(getContext(), "Database list: "+newDatabase.dao().getTalkList().size(), Toast.LENGTH_SHORT).show();
                 talkList.add(new RoomModelClass(newDatabase.dao().getTalkList().get(0).getBtnImage(),newDatabase.dao().getTalkList().get(0).getBtnText()));
                 myDatabase.dao().talkInsertion(talkList);
                 setAdapter(myDatabase.dao().getTalkList());
                    newDatabase.dao().deleteBtnByText(newDatabase.dao().getTalkList().get(0).getBtnText().toString());
                 //                 newList.remove((index));
                }
                else{
                    Toast.makeText(getContext(), "List is empty", Toast.LENGTH_SHORT).show(); }
                }
            });
        return root;
    }

    private void setupDB() {
        myDatabase = Room.databaseBuilder(getContext(),MyDatabase.class,"TalkDB").allowMainThreadQueries().build();
        newDatabase = Room.databaseBuilder(getContext(),MyDatabase.class,"newDB").allowMainThreadQueries().build();
    }

    private void setAdapter(List<RoomModelClass> list) {
        HomeAdapter mAdapter = new HomeAdapter(getContext(),list);
        mAdapter.notifyDataSetChanged();
        binding.rvHome.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        binding.rvHome.setItemAnimator(new DefaultItemAnimator());
        binding.rvHome.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}