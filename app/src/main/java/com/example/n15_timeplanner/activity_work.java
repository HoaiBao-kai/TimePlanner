package com.example.n15_timeplanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class activity_work extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    RecyclerView recyclerView;
    List<Notetitle> notetitleList;
    List<String> mKeys=new ArrayList<>();
    AdapterNotetitlr adapterNotetitlr;
    LottieAnimationView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        recyclerView=findViewById(R.id.recyclerView);
        getData();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapterNotetitlr=new AdapterNotetitlr(this,notetitleList);
        recyclerView.setAdapter(adapterNotetitlr);
        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_work.this, Activity_addwork.class);
                startActivity(intent);
            }
        });

    }
    public void getData(){
        notetitleList=new ArrayList<>();
        _myRef=firebaseDatabase.getReference("Note");
        _myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Notetitle notetitle=snapshot.getValue(Notetitle.class);
                if(notetitle!=null){
                    notetitleList.add(notetitle);
                    String key=snapshot.getKey();
                    mKeys.add(key);
                    adapterNotetitlr.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Notetitle notetitle=snapshot.getValue(Notetitle.class);
                if(notetitle==null|| notetitleList==null|| notetitleList.isEmpty()){
                    return;
                }
                int index=mKeys.indexOf(snapshot.getKey());
                notetitleList.set(index, notetitle);
                adapterNotetitlr.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Notetitle notetitle=snapshot.getValue(Notetitle.class);
                if(notetitle==null|| notetitleList==null||notetitleList.isEmpty()){
                    return;
                }
                int index=mKeys.indexOf(snapshot.getKey());
                if(index!=-1){
                    notetitleList.remove(index);
                    mKeys.remove(index);
                }
                adapterNotetitlr.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}