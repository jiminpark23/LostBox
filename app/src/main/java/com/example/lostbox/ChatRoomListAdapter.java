package com.example.lostbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ChatRoomListAdapter extends RecyclerView.Adapter<ChatRoomListAdapter.ChatRoomViewHolder> {
    private List<ChatRoom> chatRooms;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ChatRoom chatRoom);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ChatRoomListAdapter(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public class ChatRoomViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileImage;
        private TextView name;
        private TextView lastMessage;
        private TextView time;

        public ChatRoomViewHolder(View view) {
            super(view);
            profileImage = view.findViewById(R.id.profile_image);
            name = view.findViewById(R.id.chat_room_name);
            lastMessage = view.findViewById(R.id.last_message);
            time = view.findViewById(R.id.chat_time);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(chatRooms.get(position));
                        }
                    }
                }
            });
        }
    }

    @Override
    public ChatRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_room_item, parent, false);
        return new ChatRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatRoomViewHolder holder, int position) {
        ChatRoom chatRoom = chatRooms.get(position);
        holder.profileImage.setImageResource(chatRoom.getProfileImage());
        holder.name.setText(chatRoom.getRoomName());
        holder.lastMessage.setText(chatRoom.getLastMessage());
        holder.time.setText((int) chatRoom.getTime());
    }

    @Override
    public int getItemCount() {
        return chatRooms.size();
    }
}
