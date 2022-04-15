package com.example.letstalk.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.letstalk.databinding.LayoutButtonBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import com.example.letstalk.models.RoomModelClass;


public class HomeAdapterV2 extends RecyclerView.Adapter<HomeAdapterV2.MyViewHolder> {
    ArrayList<RoomModelClass> roomModelClassArrayList;
    Context context;
    OnRoomModelClassClickListener onRoomModelClassClickListener;

    public HomeAdapterV2(Context context, ArrayList<RoomModelClass> roomModelClassArrayList) {
        this.context = context;
        this.roomModelClassArrayList = roomModelClassArrayList;
    }

    public void setOnRoomModelClassClickListener(OnRoomModelClassClickListener onRoomModelClassClickListener) {
        this.onRoomModelClassClickListener = onRoomModelClassClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutButtonBinding binding = LayoutButtonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        RoomModelClass roomModelClass = roomModelClassArrayList.get(holder.getAdapterPosition());
        holder.binding.btnImage.setImageResource(roomModelClass.getBtnImage());
        holder.binding.tvPhrase.setText(roomModelClass.getBtnText());

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRoomModelClassClickListener != null) {
                    onRoomModelClassClickListener.onRoomModelClassClicked(holder.getAdapterPosition(), roomModelClass);
                }
            }
        });
        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRoomModelClassClickListener != null) {
                    onRoomModelClassClickListener.onDeleteClicked(holder.getAdapterPosition(), roomModelClass);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return roomModelClassArrayList.size();
    }

    public interface OnRoomModelClassClickListener {
        void onRoomModelClassClicked(int position, RoomModelClass roomModelClass);
        void onDeleteClicked(int position, RoomModelClass roomModelClass);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LayoutButtonBinding binding;

        public MyViewHolder(@NotNull LayoutButtonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}



