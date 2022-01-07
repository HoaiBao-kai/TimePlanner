package com.example.n15_timeplanner;

import static com.example.n15_timeplanner.R.id.btnsave;
import static com.example.n15_timeplanner.R.id.image;
import static com.example.n15_timeplanner.R.id.layoutMiscellaneous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.airbnb.lottie.animation.keyframe.GradientColorKeyframeAnimation;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Activity_addwork extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    EditText etnoteSub;
    TextView  etdate,etnote;
    Spinner spchude;
    ImageView btnsave, back;
    ImageView btndate;
    List<topic> topicList;
    ArrayAdapter<topic> topicArrayAdapter;
    Notetitle notetitle;
    private  String selectedNote;
    private View viewSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwork);
        init();
        setSubColor();
        Topic();
        initMiscellaneous();
//        btndate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                int ngay = calendar.get(Calendar.DATE);
//                int thang = calendar.get(Calendar.MONTH);
//                int nam = calendar.get(Calendar.YEAR);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(Activity_addwork.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        calendar.set(year, month, dayOfMonth);
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
//                        etdate.setText(simpleDateFormat.format(calendar.getTime()));
//                    }
//                }, nam, thang, ngay);
//                datePickerDialog.show();
//            }
//        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(notetitle);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_addwork.this,activity_work.class);
                startActivity(intent);
            }
        });
    }

    public void init() {
        etdate = findViewById(R.id.eddate);
        etnote = findViewById(R.id.edNotetitle);
        etnoteSub = findViewById(R.id.ednoteSub);
        spchude = findViewById(R.id.spchude);
        btndate = findViewById(R.id.dialogcalender);
        btnsave = findViewById(R.id.btnsave);
        back = findViewById(R.id.back);
        selectedNote="#333333";
        etdate.setText( new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        viewSub=findViewById(R.id.viewcolor);



    }

    public void Topic() {
        _myRef = firebaseDatabase.getReference("topic");
        topicList = new ArrayList<>();

        _myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (topicList != null || !topicList.isEmpty()) {
                    topicList.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    topic t;
                    t = dataSnapshot.getValue(topic.class);
                    topicList.add(t);
                }
                topicArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        topicArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, topicList);
        topicArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_list_item_single_choice);
        spchude.setAdapter(topicArrayAdapter);

    }

    public void setData(Notetitle notetitle) {
        if (notetitle == null) {
            notetitle = new Notetitle();

            notetitle.setTitle(etnote.getText().toString());
            notetitle.setNoteSub(etnoteSub.getText().toString());
            notetitle.setDate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
            notetitle.setColornote(selectedNote);
//        notetitle.setSptopic((topic) spchude.getSelectedItem());
            _myRef = firebaseDatabase.getReference("Note");
            String id = _myRef.push().getKey();
            notetitle.setId(id);
            _myRef.child(id).setValue(notetitle);

        }

    }
    private void setSubColor(){
        GradientDrawable gradientDrawable=(GradientDrawable) viewSub.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedNote));
    }
    private void initMiscellaneous(){
        final LinearLayout layout=findViewById(R.id.layoutMiscellaneous);
        final ImageView imgcolor0=layout.findViewById(R.id.imgcolor0);
        final ImageView imgcolor1=layout.findViewById(R.id.imgcolor1);
        final ImageView imgcolor2=layout.findViewById(R.id.imgcolor2);
        final ImageView imgcolor3=layout.findViewById(R.id.imgcolor3);
        final ImageView imgcolor4=layout.findViewById(R.id.imgcolor4);
        layout.findViewById(R.id.notecolor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNote="#333333";
                imgcolor0.setImageResource(R.drawable.ic_baseline_done_24);
                imgcolor1.setImageResource(0);
                imgcolor2.setImageResource(0);
                imgcolor3.setImageResource(0);
                imgcolor4.setImageResource(0);
                setSubColor();
            }
        });
        layout.findViewById(R.id.notecolor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNote="#FDBE3B";
                imgcolor0.setImageResource(0);
                imgcolor1.setImageResource(R.drawable.ic_baseline_done_24);
                imgcolor2.setImageResource(0);
                imgcolor3.setImageResource(0);
                imgcolor4.setImageResource(0);
                setSubColor();
            }
        });
        layout.findViewById(R.id.notecolor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNote="#FF4842";
                imgcolor0.setImageResource(0);
                imgcolor1.setImageResource(0);
                imgcolor2.setImageResource(R.drawable.ic_baseline_done_24);
                imgcolor3.setImageResource(0);
                imgcolor4.setImageResource(0);
                setSubColor();
            }
        });
        layout.findViewById(R.id.notecolor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNote="#3A52FC";
                imgcolor0.setImageResource(0);
                imgcolor1.setImageResource(0);
                imgcolor2.setImageResource(0);
                imgcolor3.setImageResource(R.drawable.ic_baseline_done_24);
                imgcolor4.setImageResource(0);
                setSubColor();
            }
        });
        layout.findViewById(R.id.notecolor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNote="#000000";
                imgcolor0.setImageResource(0);
                imgcolor1.setImageResource(0);
                imgcolor2.setImageResource(0);
                imgcolor3.setImageResource(0);
                imgcolor4.setImageResource(R.drawable.ic_baseline_done_24);
                setSubColor();
            }
        });

    }
}