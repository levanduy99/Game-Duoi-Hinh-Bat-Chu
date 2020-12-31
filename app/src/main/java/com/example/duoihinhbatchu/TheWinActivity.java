package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TheWinActivity extends AppCompatActivity {

    Button btnShare;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_the_win);

        setTitle ("Activity The Win");

        Intent intent = getIntent ();
        String answer = intent.getStringExtra ("s");
        String image = intent.getStringExtra ("image");

        TextView textViewShowAnswer = findViewById (R.id.txvShowAnswer);
        textViewShowAnswer.setText (answer);

        ImageView imageAnswer = findViewById (R.id.imageAnswer);
        Glide.with(this)
                .load(image)
                .into(imageAnswer);

        Button btnContinuous = findViewById (R.id.btn_continuous);
        btnContinuous.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent continuousIntent = new Intent ();

                setResult (RESULT_OK,continuousIntent);
                finish ();
            }
        });

        btnShare = findViewById (R.id.btn_share);
        btnShare.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String text = "Link download game: http:///";
                Intent mShareIntent = new Intent (Intent.ACTION_SEND);
                mShareIntent.setType ("text/plain");
                mShareIntent.putExtra (Intent.EXTRA_SUBJECT,"Write your subject here");
                mShareIntent.putExtra (Intent.EXTRA_TEXT,text);
                startActivity (Intent.createChooser (mShareIntent,"Share text via"));
            }
        });

        btnHome = (Button) findViewById (R.id.btn_home);

        btnHome.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (TheWinActivity.this,MainActivity.class);
                startActivity (intent);
            }
        });

    }
}