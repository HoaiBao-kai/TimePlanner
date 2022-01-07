package com.example.n15_timeplanner;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_danhmuc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_danhmuc extends Fragment {
    TextView topic;
    LottieAnimationView addtopic;
    RecyclerView recyclerViewtopic;
    List<topic> topicList;
    AdapterTopic adapterTopic;
    List<String> mKeys=new ArrayList<>();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_danhmuc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_danhmuc.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_danhmuc newInstance(String param1, String param2) {
        Fragment_danhmuc fragment = new Fragment_danhmuc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_danhmuc, container, false);
        recyclerViewtopic= (RecyclerView) view.findViewById(R.id.recyclerViewtopic);
        topic=view.findViewById(R.id.topic);
        addtopic=view.findViewById(R.id.addtopic);
        getData();
        recyclerViewtopic.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        recyclerViewtopic.addItemDecoration(dividerItemDecoration);
        adapterTopic=new AdapterTopic(getActivity(),topicList);
        recyclerViewtopic.setAdapter(adapterTopic);
        addtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER);
            }
        });
        return view;
    }

    private void getData() {
        topicList=new ArrayList<>();
        _myRef=database.getReference("topic");
        _myRef.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                topic t =snapshot.getValue(topic.class);
                if(t!=null){
                    topicList.add(t);
                    String key=snapshot.getKey();
                    mKeys.add(key);
                    adapterTopic.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                topic t=snapshot.getValue(topic.class);
                if(t==null|| topicList==null || topicList.isEmpty()){
                    return;
                }
                int index=mKeys.indexOf(snapshot.getKey());
                topicList.set(index,t);
                adapterTopic.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                topic t=snapshot.getValue(topic.class);
                if(t==null|| topicList==null || topicList.isEmpty()){
                    return;
                }
                int index=mKeys.indexOf(snapshot.getKey());
                if(index !=-1){
                    topicList.remove(index);
                    mKeys.remove(index);
                }
                adapterTopic.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void openDialog(int gravity){
        final Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_topic);

        Window window=dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAtrributes= window.getAttributes();
        windowAtrributes.gravity=gravity;
        window.setAttributes(windowAtrributes);

        if(Gravity.BOTTOM==gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }
        dialog.show();

    }
}