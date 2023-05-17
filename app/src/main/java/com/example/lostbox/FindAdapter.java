package com.example.lostbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {

    private ArrayList<FindItem> findItems = new ArrayList<>(); // adapter에 들어갈 list

    public FindAdapter(ArrayList<FindItem> findItemArrayList, Context context) {

    }
//    private Context context;
//
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
        Glide.with(holder.itemView)
                .load(findItems.get(position).getImg())
                .into(holder.iv_img);
        holder.tv_title.setText(findItems.get(position).getTitle());
        holder.tv_place.setText(findItems.get(position).getPlace());
        holder.tv_date.setText(findItems.get(position).getPlace());
        holder.tv_content.setText(findItems.get(position).getContent());

//        FindItem currentItem = findItems.get(position);
        //holder.onBind(findItems.get(position));

//        holder.imageView.setImageResource(currentItem.getImg());
//        holder.textView1.setText(currentItem.getTitle());
//        holder.textView2.setText(currentItem.getPlace());
//        holder.textView3.setText(currentItem.getDate());
//        holder.textView4.setText(currentItem.getContent());
    }

    @Override
    public int getItemCount() {  //recyclerview의 총 개수
        return (findItems != null ? findItems.size() : 0);
    }

    void addItem(FindItem findItem) {  //외부에서 item을 추가시킬 함수?

        findItems.add(findItem);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_place;
        public TextView tv_date;
        public TextView tv_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.find_img);
            tv_title = itemView.findViewById(R.id.find_title);
            tv_place = itemView.findViewById(R.id.find_place);
            tv_date = itemView.findViewById(R.id.find_date);
            tv_content = itemView.findViewById(R.id.find_content);
        }
//        void onBind(FindItem findItem){
//            iv_img.setImageResource(findItem.getImg());
//            tv_title.setText(findItem.getTitle());
//            tv_place.setText(findItem.getPlace());
//            tv_date.setText(findItem.getDate());
//            tv_content.setText(findItem.getContent());
//        }

    }
}