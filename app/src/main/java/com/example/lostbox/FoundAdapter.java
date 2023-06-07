package com.example.lostbox;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FoundAdapter extends RecyclerView.Adapter<FoundAdapter.ViewHolder> {
    private ArrayList<FoundItem> foundItems = new ArrayList<>(); // adapter에 들어갈 list

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.found_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {  // 아이템을 하나, 하나 보여주는 함수?
//        FindItem currentItem = findItems.get(position);
        holder.onBind(foundItems.get(position));

//        holder.imageView.setImageResource(currentItem.getImg());
//        holder.textView1.setText(currentItem.getTitle());
//        holder.textView2.setText(currentItem.getPlace());
//        holder.textView3.setText(currentItem.getDate());
//        holder.textView4.setText(currentItem.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                int mPosition = holder.getAdapterPosition();
                Context context = view.getContext();
                Intent detailActivity = new Intent(context, FoundDetailActivity.class);

                detailActivity.putExtra("title", foundItems.get(mPosition).getTitle());
                detailActivity.putExtra("image", foundItems.get(mPosition).getImg());
                detailActivity.putExtra("ex", foundItems.get(mPosition).getContent());
                detailActivity.putExtra("date", foundItems.get(mPosition).getDate());
                detailActivity.putExtra("place", foundItems.get(mPosition).getPlace());

                context.startActivity(detailActivity);

            }

        });
    }

    public int getItemCount() {  //recyclerview의 총 개수
        return foundItems.size();
    }

    void addItem(FoundItem foundItem) {  //외부에서 item을 추가시킬 함수?

        foundItems.add(foundItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.found_img);
            textView1 = itemView.findViewById(R.id.found_title);
            textView2 = itemView.findViewById(R.id.found_place);
            textView3 = itemView.findViewById(R.id.found_date);
            textView4 = itemView.findViewById(R.id.found_ex);
        }
        void onBind(FoundItem foundItem){
            imageView.setImageResource(foundItem.getImg());
            textView1.setText(foundItem.getTitle());
            textView2.setText(foundItem.getPlace());
            textView3.setText(foundItem.getDate());
            textView4.setText(foundItem.getContent());
        }

    }

}
