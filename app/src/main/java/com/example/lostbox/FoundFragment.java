package com.example.lostbox;

//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class FoundFragment extends Fragment {

    private RecyclerView recyclerView;
    private  FoundAdapter adapter;

    public FoundFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_found, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.found_recycle);
        init();
        getData();

        Button btn_findwrite = (Button) view.findViewById(R.id.found_write);
        btn_findwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FoundWrittingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void init(){

        if(recyclerView != null){
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);

            adapter = new FoundAdapter();
            recyclerView.setAdapter(adapter);
        }

    }
    private void getData(){
        List<String> listTitle = Arrays.asList("자전거 주인", "지갑 주웠음", "강아지 찾음", "이어폰 주인", "지갑 주인");
        List<String> listPlace = Arrays.asList("단국대 1", "단국대 2", "단국대 3", "단국대 4", "단국대 5");
        List<String> listDate = Arrays.asList("2023-01-01", "2023-02-15", "2023-03-21", "2023-05-31","2023-06-06");
        List<String> listContent = Arrays.asList(
                "자전거를 찾았습니다.",
                "지갑을 찾았습니다.",
                "강아지를 찾았습니다.",
                "이어폰 찾았습니다.",
                "지갑을 찾았습니다."
        );
        List<Integer> listImg = Arrays.asList(R.drawable.bicycle1,
                R.drawable.wallet,
                R.drawable.dog,
                R.drawable.earphone,
                R.drawable.wallet2
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            FoundItem data = new FoundItem();
            data.setTitle(listTitle.get(i));
            data.setPlace(listPlace.get(i));
            data.setDate(listDate.get(i));
            data.setContent(listContent.get(i));
            data.setImg(listImg.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();

    }
}
