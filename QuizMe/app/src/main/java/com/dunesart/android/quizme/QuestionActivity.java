package com.dunesart.android.quizme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * Created by LindySteph on 1/31/2018.
 */

public class QuestionActivity extends AppCompatActivity {

    Firebase mRef;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Reference to firebase database
        mRef = new Firebase("https://quizme-c27c2.firebaseio.com/");
        setContentView(R.layout.activity_grid);

        getSupportActionBar().setTitle("Choose Your Questtion");
        recyclerView =  (RecyclerView) findViewById(R.id.recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<String, QuizViewHolder> adapter = new FirebaseRecyclerAdapter<String, QuizViewHolder>(
                String.class,
                R.layout.grid_item,
                QuizViewHolder.class,
                mRef
        ) {
            @Override
            protected void populateViewHolder(QuizViewHolder quizViewHolder, String s, int i) {

                String[] colorArray = {"EB5757","56CCF2","F2C94C","27AE60","2F80ED","BB6BD9"};
                if (colorArray.length > i){
                    quizViewHolder.relativeLayout.setBackgroundColor(Integer.getInteger(colorArray[i]));
                }
                Log.e("Question Activity",s);
                //quizViewHolder.textView.setText(s);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        RelativeLayout relativeLayout;

        public QuizViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) findViewById(R.id.gridView);
            relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayer);
        }
    }
}
