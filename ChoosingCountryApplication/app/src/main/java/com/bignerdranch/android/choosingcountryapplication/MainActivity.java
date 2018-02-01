package com.bignerdranch.android.choosingcountryapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridLayout mGridLayout;
//    private ImageView mCanadaFlag, mBrazilFlag, mChinaFlag, mDominicanFlag, mPeruFlag, mJapanFlag, mMexicoFlag, mTaiwanFlag, mTurkeyFlag;
//    private CheckBox mCheckBoxCanada, mCheckBoxBrazil, mCheckBoxChina, mCheckBoxDominica, mCheckBoxPeru, mCheckBoxJapan, mCheckBoxMexico, mCheckBoxTaiwan, mCheckBoxTurkey;
//    private List<ImageView> mAllFlags = new ArrayList<>();
//    private List<CheckBox> mAllCheckBoxs = new ArrayList<>();
    private List<Integer> mAllFlagsRes = new ArrayList<>();
    private List<Integer> mCheckBoxRes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridLayout = findViewById(R.id.main_grid);
        mGridLayout.setColumnCount(3);

//        mCanadaFlag = new ImageView(this);
//        mCanadaFlag.setImageResource(R.drawable.canada);
//
//        mBrazilFlag = new ImageView(this);
//        mBrazilFlag.setImageResource(R.drawable.brazil);
//
//        mChinaFlag = new ImageView(this);
//        mChinaFlag.setImageResource(R.drawable.china);
//
//        mDominicanFlag = new ImageView(this);
//        mDominicanFlag.setImageResource(R.drawable.dominicarepublic);
//
//        mPeruFlag = new ImageView(this);
//        mPeruFlag.setImageResource(R.drawable.flagofperu);
//
//        mJapanFlag = new ImageView(this);
//        mJapanFlag.setImageResource(R.drawable.japan);
//
//        mMexicoFlag = new ImageView(this);
//        mMexicoFlag.setImageResource(R.drawable.mexico);
//
//        mTaiwanFlag = new ImageView(this);
//        mTaiwanFlag.setImageResource(R.drawable.taiwanflagimage);
//
//        mTurkeyFlag = new ImageView(this);
//        mTurkeyFlag.setImageResource(R.drawable.turkey);

//
//        mAllFlags.add(mCanadaFlag);
//        mAllFlags.add(mBrazilFlag);
//        mAllFlags.add(mChinaFlag);
//        mAllFlags.add(mDominicanFlag);
//        mAllFlags.add(mPeruFlag);
//        mAllFlags.add(mJapanFlag);
//        mAllFlags.add(mMexicoFlag);
//        mAllFlags.add(mTaiwanFlag);
//        mAllFlags.add(mTurkeyFlag);

//        for (ImageView flag : mAllFlags){
//            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(350,200);
//            flag.setLayoutParams(params);
//        }

        mAllFlagsRes.add(R.drawable.canada);
        mAllFlagsRes.add(R.drawable.brazil);
        mAllFlagsRes.add(R.drawable.china);
        mAllFlagsRes.add(R.drawable.dominicarepublic);
        mAllFlagsRes.add(R.drawable.flagofperu);
        mAllFlagsRes.add(R.drawable.japan);
        mAllFlagsRes.add(R.drawable.mexico);
        mAllFlagsRes.add(R.drawable.taiwanflagimage);
        mAllFlagsRes.add(R.drawable.turkey);


//        mCheckBoxCanada = new CheckBox(this);
//        mCheckBoxCanada.setText(R.string.canada);
//
//        mCheckBoxBrazil = new CheckBox(this);
//        mCheckBoxBrazil.setText(R.string.brazil);
//
//        mCheckBoxChina = new CheckBox(this);
//        mCheckBoxChina.setText(R.string.china);
//
//        mCheckBoxDominica = new CheckBox(this);
//        mCheckBoxDominica.setText(R.string.dominica);
//
//        mCheckBoxPeru = new CheckBox(this);
//        mCheckBoxPeru.setText(R.string.peru);
//
//        mCheckBoxJapan = new CheckBox(this);
//        mCheckBoxJapan.setText(R.string.japan);
//
//        mCheckBoxMexico = new CheckBox(this);
//        mCheckBoxMexico.setText(R.string.mexico);
//
//        mCheckBoxTaiwan = new CheckBox(this);
//        mCheckBoxTaiwan.setText(R.string.taiwan);
//
//        mCheckBoxTurkey = new CheckBox(this);
//        mCheckBoxTurkey.setText(R.string.turkey);
//
//
//        mAllCheckBoxs.add(mCheckBoxCanada);
//        mAllCheckBoxs.add(mCheckBoxBrazil);
//        mAllCheckBoxs.add(mCheckBoxChina);
//        mAllCheckBoxs.add(mCheckBoxDominica);
//        mAllCheckBoxs.add(mCheckBoxPeru);
//        mAllCheckBoxs.add(mCheckBoxJapan);
//        mAllCheckBoxs.add(mCheckBoxMexico);
//        mAllCheckBoxs.add(mCheckBoxTaiwan);
//        mAllCheckBoxs.add(mCheckBoxTurkey);
//
//        for (CheckBox checkBox: mAllCheckBoxs){
//            checkBox.setTextSize(25);
//        }


        mCheckBoxRes.add(R.string.canada);
        mCheckBoxRes.add(R.string.brazil);
        mCheckBoxRes.add(R.string.china);
        mCheckBoxRes.add(R.string.dominica);
        mCheckBoxRes.add(R.string.peru);
        mCheckBoxRes.add(R.string.japan);
        mCheckBoxRes.add(R.string.mexico);
        mCheckBoxRes.add(R.string.taiwan);
        mCheckBoxRes.add(R.string.turkey);


        for (int i=0; i<mAllFlagsRes.size(); i++){
//            LinearLayout linearLayout = new LinearLayout(this);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearLayout.setLayoutParams(params);
//            linearLayout.setOrientation(LinearLayout.VERTICAL);
//            linearLayout.addView(mAllFlags.get(i));
//            linearLayout.addView(mAllCheckBoxs.get(i));
            View v = getLayoutInflater().inflate(R.layout.single_flag,null);
            ImageView flag =  v.findViewById(R.id.flag);
            flag.setImageResource(mAllFlagsRes.get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(350,200);
            flag.setLayoutParams(params);
            CheckBox checkBox = v.findViewById(R.id.checkbox);
            checkBox.setText(mCheckBoxRes.get(i));

            mGridLayout.addView(v);
        }

    }
}
