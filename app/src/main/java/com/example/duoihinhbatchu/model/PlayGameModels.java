package com.example.duoihinhbatchu.model;

import com.example.duoihinhbatchu.Data;
import com.example.duoihinhbatchu.PlayGameActivity;
import com.example.duoihinhbatchu.object.Player;
import com.example.duoihinhbatchu.object.Riddle;

import java.util.ArrayList;

public class PlayGameModels {

    PlayGameActivity playGameActivity;

    ArrayList<Riddle> arrayList;
    int indexRiddle = -1;

    public Player player;

    public PlayGameModels(PlayGameActivity playGameActivity) {
        this.playGameActivity = playGameActivity;
        player = new Player ();
        createData ();
    }

    private void createData(){
        arrayList = new ArrayList<> (Data.getData ( ).arrRiddle);
        //arrayList.add (new Riddle ("round 4","",""));
        //arrayList.add (new Riddle ("round 3","lygian","https://1.bp.blogspot.com/-5MwDFxQ9ECA/VIB3dpdBjNI/AAAAAAAAG0M/8l53N0LElIY/s1600/LY%2BGI%C3%81N.jpg"));
        //arrayList.add (new Riddle ("round 2","sutu","https://khothuthuat.com/wp-content/uploads/2014/09/dap-an-bat-chu-p14.png"));
        //arrayList.add (new Riddle ("round 1","kienthuc","https://4.bp.blogspot.com/-aGadDAzQf-Y/U88mmWwSeyI/AAAAAAAAD9E/_FyOMLMLhwE/s1600/2014-07-23+08.35.57-1.png"));
    }

    public Riddle getRiddle(){
        indexRiddle++;
        if(indexRiddle>=arrayList.size ()){
            indexRiddle = arrayList.size () - 1;
        }
        return  arrayList.get (indexRiddle);
    }

    public void getInformation(){
        player.getState (playGameActivity);
    }

    public void saveInformation(){
        player.saveState (playGameActivity);
    }
}
