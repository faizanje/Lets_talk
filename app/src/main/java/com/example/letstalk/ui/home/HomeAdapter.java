//package com.example.letstalk.ui.home;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;
//
//import com.example.letstalk.databinding.LayoutButtonBinding;
//
//import java.util.List;
//
//import com.example.letstalk.models.RoomModelClass;
//import io.paperdb.Paper;
//
//public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
//    Context context;
//    List<RoomModelClass> itemList;
//    LayoutButtonBinding binding;
//    MyDatabase database;
////    HomeModelClass newlist[];
//    public HomeAdapter(Context context, List<RoomModelClass> itemList) {
//        this.context = context;
//        this.itemList = itemList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        binding = LayoutButtonBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
//        return new ViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
////        newlist = Paper.book().read("PhraseDB");
//        RoomModelClass list = itemList.get(position);
////        HomeModelClass myList =  newlist[position];
//        holder.binding.btnImage.setImageResource(list.getBtnImage());
//        holder.binding.tvPhrase.setText(list.getBtnText());
////        holder.binding.btnImage.setImageResource(list.getBtnImg());
////        holder.binding.tvPhrase.setText(list.getBtnText());
//
//
//        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(context, "List" + Paper.book().getAllKeys().size(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                database.dao().deleteBtn(position);
//                database = Room.databaseBuilder(v.getContext(), MyDatabase.class, "TalkDB").allowMainThreadQueries().build();
//                database.dao().deleteBtn(itemList.get(position).getId());
//                itemList.remove(position);
//                Toast.makeText(context, "Index" + database.dao().getTalkList().size(), Toast.LENGTH_SHORT).show();
//                notifyDataSetChanged();
//
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return itemList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        LayoutButtonBinding binding;
//
//        public ViewHolder(LayoutButtonBinding binding) {
//            super(binding.getRoot());
//            this.binding=binding;
//        }
//    }
//}
