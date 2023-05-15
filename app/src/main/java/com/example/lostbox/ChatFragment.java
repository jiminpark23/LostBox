package com.example.lostbox;

//import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatFragment extends Fragment {
    private ChatRoomListAdapter adapter;
    private RecyclerView recyclerView;
    private List<ChatRoom> chatRoomList;
    public ChatFragment() {

    }

/*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
    */
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_chat, container, false);
//        recyclerView = view.findViewById(R.id.chat_list);
//        init();
//        getData();
//        return view;
//    }
//
//    private void init(){
//       recyclerView = getView().findViewById(R.id.chat_list);
//
//       if(recyclerView != null){
//           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//           recyclerView.setLayoutManager(linearLayoutManager);
//
//           adapter = new ChatRoomListAdapter();
//           recyclerView.setAdapter(adapter);
//       }
//
//    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = view.findViewById(R.id.chat_list);
        init();
        getData();
        return view;
    }

    private void init() {
        if(recyclerView != null){
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);

            adapter = new ChatRoomListAdapter();
            recyclerView.setAdapter(adapter);
        }
    }


    private void getData(){
        List<String> listTitle = Arrays.asList("국화", "사막", "수국", "해파리", "코알라");
        List<Integer> listProf = Arrays.asList(R.drawable.bicycle1,
                R.drawable.bicycle2,
                R.drawable.bicycle3,
                R.drawable.bicycle4,
                R.drawable.bicycle5
        );
        List<String> listLast = Arrays.asList(
                "이 꽃은 국화입니다.",
                "여기는 사막입니다.",
                "이 꽃은 수국입니다.",
                "이 동물은 해파리입니다.",
                "이 동물은 코알라입니다."
        );

        List<Integer> listTime = Arrays.asList(1, 2, 3, 4, 5);

        for (int i = 0; i < listTitle.size(); i++) {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setProfileImage(listProf.get(i));
            chatRoom.setRoomName(listTitle.get(i));
            chatRoom.setLastMessage(listLast.get(i));

            adapter.addItem(chatRoom);
        }

        adapter.notifyDataSetChanged();

    }
}
