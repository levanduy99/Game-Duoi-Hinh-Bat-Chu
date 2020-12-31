package com.example.duoihinhbatchu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duoihinhbatchu.adapter.AnswerAdapter;
import com.example.duoihinhbatchu.model.PlayGameModels;
import com.example.duoihinhbatchu.object.Riddle;

import java.util.ArrayList;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {

    PlayGameModels models;
    Riddle riddle;

    private  String answer = "catinh";

    ArrayList<String> arrayAnswer;
    GridView gdvAnswer;

    ArrayList<String> arrayChoice;
    GridView gdvChoice;
    TextView txvCoin;

    int index = 0;

    ImageView imageRiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_play_game);
        init();
        map ();
        setOnClick ();
        showRiddle ();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                showRiddle ();
            }
        }
    }
    private void map(){
        gdvAnswer = findViewById (R.id.gdvAnswer);
        gdvChoice = findViewById (R.id.gdvChoice);
        imageRiddle = findViewById (R.id.imageRiddle);
        txvCoin = findViewById (R.id.txvCoin);
    }

    private void init(){
        models = new PlayGameModels (this);
        arrayAnswer = new ArrayList<> ();
        arrayChoice = new ArrayList<> ();
    }

    public void showRiddle(){
        riddle = models.getRiddle ();
        answer = riddle.answer_correct;
        choppedData ();
        showAnswer ();
        showChoice ();
        Glide.with(this)
                .load(riddle.image)
                .into(imageRiddle);
        models.getInformation ();
        txvCoin.setText (models.player.coin+"$");
    }

    private void showAnswer(){
        gdvAnswer.setNumColumns (arrayAnswer.size ());
        gdvAnswer.setAdapter (new AnswerAdapter (this,0,arrayAnswer));
    }

    private void showChoice(){
        gdvChoice.setNumColumns (arrayChoice.size ()/2);
        gdvChoice.setAdapter (new AnswerAdapter (this,0,arrayChoice));
    }

    private void setOnClick(){

        gdvChoice.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition (position);
                if(s.length ()!=0 && index < arrayAnswer.size ()){
                    for(int i=0;i<arrayAnswer.size ();i++){
                        if(arrayAnswer.get (i).length () == 0){
                            index = i;
                            break;
                        }
                    }
                    arrayChoice.set(position,"");
                    arrayAnswer.set(index,s);
                    index++;
                    showAnswer ();
                    showChoice ();
                    checkWin ();
                }
            }
        });

        gdvAnswer.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition (position);
                if(s.length () != 0){
                    index = position;
                    arrayAnswer.set(position,"");
                    for(int i=0;i<arrayChoice.size ();i++){
                        if(arrayChoice.get (i).length () == 0){
                            arrayChoice.set (i,s);
                            break;
                        }
                    }
                    showAnswer ();
                    showChoice ();
                }
            }
        });
    }

    private void choppedData(){
        index = 0;
        Random r = new Random ();
        arrayAnswer.clear ();
        arrayChoice.clear ();
        for(int i=0;i<answer.length ();i++){
            arrayAnswer.add("");
            String s1 = ""+(char)(r.nextInt (26)+65);
            arrayChoice.add(s1);
            String s2 = ""+(char)(r.nextInt (26)+65);
            arrayChoice.add(s2);
        }
        for(int i=0;i<answer.length ();i++){
            String s = ""+answer.charAt (i);
            arrayChoice.set (i,s.toUpperCase ());
        }
        for(int i=0;i<answer.length ()*2;i++){
            String s = arrayChoice.get(i);
            int index = r.nextInt (arrayChoice.size ());
            arrayChoice.set(i,arrayChoice.get(index));
            arrayChoice.set(index,s);
        }

    }

    private void checkWin(){
        String s = "";
        for(String s1:arrayAnswer){
            s = s+s1;
        }
        s = s.toUpperCase ();
        if(s.equals (answer.toUpperCase ())){
            Toast.makeText (this,"CORRECT",Toast.LENGTH_SHORT).show ();
            models.getInformation ();
            models.player.coin +=10;
            models.saveInformation ();
            String image = riddle.image;
            Intent intent = new Intent (PlayGameActivity.this,TheWinActivity.class);
            intent.putExtra ("s",s);
            intent.putExtra ("image",image);
            startActivityForResult (intent,1);
            //showRiddle ();
        }
    }

    public void openHints(View view) {
        models.getInformation ();

        if(models.player.coin<5){
            Toast.makeText (this,"The least request has 5$",Toast.LENGTH_SHORT).show ();
            return;
        }
        int id = -1;
        for(int i=0;i<arrayAnswer.size ();i++){
            if(arrayAnswer.get(i).length ()==0){
                id = i;
                break;
            }
        }
        if(id == -1){
            for(int i=0;i<arrayAnswer.size ();i++){
                String s = answer.toUpperCase ().charAt (i)+"";
                if(!arrayAnswer.get(i).toUpperCase ().equals (s)){
                    id = i;
                    break;
                }
            }
            for(int i=0;i<arrayChoice.size ();i++){
                if(arrayChoice.get (i).length () == 0){
                    arrayChoice.set (i,arrayAnswer.get (id));
                }
            }
        }
        String hints = "" + answer.charAt (id);
        hints = hints.toUpperCase ();
        for(int i=0;i<arrayChoice.size ();i++){
            if(hints.equals (arrayChoice.get (i))){
                arrayChoice.set(i,"");
                break;
            }
        }
        arrayAnswer.set (id,hints);
        showAnswer ();
        showChoice ();
        models.getInformation ();
        models.player.coin -= 5;
        models.saveInformation ();
        txvCoin.setText (models.player.coin+"$");
        checkWin();
    }

    public void changeQuiz(View view) {
        models.getInformation ();
        if(models.player.coin<10){
            Toast.makeText (this,"The least request has 10$",Toast.LENGTH_SHORT).show ();
            return;
        }
        models.player.coin -= 10;
        models.saveInformation ();
        txvCoin.setText (models.player.coin+"$");
        checkWin();
        showRiddle ();
    }

}