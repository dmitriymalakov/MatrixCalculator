package com.home.myapplication.worldofmatrix;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by SONY on 27.01.2016.
 */
public class ResultPageActivity extends Activity {

    private TextView tvNum00;
    private TextView tvNum01;
    private TextView tvNum02;
    private TextView tvNum10;
    private TextView tvNum11;
    private TextView tvNum12;
    private TextView tvNum20;
    private TextView tvNum21;
    private TextView tvNum22;

    private Button back;

    private TextView[] tvResult = {tvNum00, tvNum01, tvNum02, tvNum10, tvNum11, tvNum12, tvNum20,
            tvNum21, tvNum22};

    private int[] textViewId = {R.id.tv00, R.id.tv01, R.id.tv02, R.id.tv10, R.id.tv11, R.id.tv12,
            R.id.tv20, R.id.tv21, R.id.tv22};

    @Override
    protected void onCreate(Bundle saveInstanceSaved){

        super.onCreate(saveInstanceSaved);
        setContentView(R.layout.activity_result_page);

        Button back = (Button)findViewById(R.id.buttonBack);
        back.setOnClickListener(buttonBack);

        setupIdTvResult();

    }

    private void setupIdTvResult() {

        int[] result = getIntent().getIntArrayExtra(StartPageActivity.STRING_KEY);
        for (int i = 0; i < tvResult.length; i++) {
            tvResult[i] = (TextView)findViewById(textViewId[i]);
            tvResult[i].setText(result[i]+ "");
        }
    }

    private View.OnClickListener buttonBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.buttonBack:
                    finish();
                    break;
            }
        }
    };

}


