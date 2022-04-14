package com.example.letstalk.ui.home;

import android.os.Bundle;
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

import com.example.letstalk.models.RoomModelClass;
import com.example.letstalk.utils.DBUtils;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ArrayList<RoomModelClass> newList = new ArrayList<>();

    HomeAdapterV2 mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        newList.addAll(DBUtils.readAllItems());
        mAdapter = new HomeAdapterV2(getContext(), newList);
        binding.rvHome.setItemAnimator(new DefaultItemAnimator());
        binding.rvHome.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvHome.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnRoomModelClassClickListener(new HomeAdapterV2.OnRoomModelClassClickListener() {
            @Override
            public void onRoomModelClassClicked(int position, RoomModelClass roomModelClass) {
                newList.remove(roomModelClass);
                mAdapter.notifyDataSetChanged();

//                mAdapter.notifyItemRemoved(position);
                DBUtils.deleteItem(roomModelClass.getId());
            }
        });


        binding.extendedFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<RoomModelClass> missingItems = intersection(newList,Constants.DATA);

                if(missingItems.isEmpty()){
                    Toast.makeText(requireContext(), "No new data found", Toast.LENGTH_SHORT).show();
                    return;
                }

                RoomModelClass roomModelClass = missingItems.get(0);

//                if (roomModelClass == null) {
//                    Toast.makeText(requireContext(), "No new data found", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                newList.add(roomModelClass);
                mAdapter.notifyDataSetChanged();
                DBUtils.saveItem(roomModelClass);
            }
        });
        return root;
    }

//    private RoomModelClass getFirstMissingItem() {
//        for (RoomModelClass datum : Constants.DATA) {
//            boolean exists = false;
//            for (RoomModelClass roomModelClass : newList) {
//                Log.d(Constants.TAG, String.format("%s.equals(%s) = %s", roomModelClass.getBtnText(), datum.getBtnText(), roomModelClass.getId().equals(datum.getId())));
//                if (!roomModelClass.getId().equals(datum.getId())) {
//                    exists = true;
//                }
//            }
//            if(!exists){
//                return datum;
//            }
//        }
//        return null;
//    }

    public <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            Log.d(Constants.TAG, "intersection: ");
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}