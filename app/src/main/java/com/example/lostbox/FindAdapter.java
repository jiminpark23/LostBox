package com.example.lostbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {
    private ArrayList<FindItem> findItems = new ArrayList<>(); // adapter에 들어갈 list
//    private Context context;

//    public FindAdapter(ArrayList<FindItem> findItems, Context context) {
//        this.findItems = findItems;
//        this.context = context;
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {  // 아이템을 하나, 하나 보여주는 함수?
//        FindItem currentItem = findItems.get(position);
        holder.onBind(findItems.get(position));

//        holder.imageView.setImageResource(currentItem.getImg());
//        holder.textView1.setText(currentItem.getTitle());
//        holder.textView2.setText(currentItem.getPlace());
//        holder.textView3.setText(currentItem.getDate());
//        holder.textView4.setText(currentItem.getContent());
    }

    @Override
    public int getItemCount() {  //recyclerview의 총 개수
        return findItems.size();
    }

    void addItem(FindItem findItem) {  //외부에서 item을 추가시킬 함수?

        findItems.add(findItem);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.find_img);
            textView1 = itemView.findViewById(R.id.find_title);
            textView2 = itemView.findViewById(R.id.find_place);
            textView3 = itemView.findViewById(R.id.find_date);
            textView4 = itemView.findViewById(R.id.find_ex);
        }
        void onBind(FindItem findItem){
            imageView.setImageResource(findItem.getImg());
            textView1.setText(findItem.getTitle());
            textView2.setText(findItem.getPlace());
            textView3.setText(findItem.getDate());
            textView4.setText(findItem.getContent());
        }

    }
}