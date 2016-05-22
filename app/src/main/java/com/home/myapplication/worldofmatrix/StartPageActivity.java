package com.home.myapplication.worldofmatrix;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class StartPageActivity extends Activity {

    public static final String STRING_KEY = "Matrix";

    private EditText num00;
    private EditText num01;
    private EditText num02;
    private EditText num10;
    private EditText num11;
    private EditText num12;
    private EditText num20;
    private EditText num21;
    private EditText num22;

    private EditText numB00;
    private EditText numB01;
    private EditText numB02;
    private EditText numB10;
    private EditText numB11;
    private EditText numB12;
    private EditText numB20;
    private EditText numB21;
    private EditText numB22;


    private Button result;
    private Button autoFill;
    private Button minus;
    private Button plus;
    private Button multi;


    private final int SIZE = 9;

    private int[] tvResult = new int[SIZE];
    private int[] editTextA_intValue;
    private int[] editTextB_intValue;


    private String[] editBnum;
    private String[] editAnum;


    private EditText[] editTextA = {num00, num01, num02, num10, num11, num12, num20, num21, num22};
    private int[] editTextIdA = {R.id.num00, R.id.num01, R.id.num02, R.id.num10, R.id.num11,
            R.id.num12, R.id.num20, R.id.num21, R.id.num22};

    private EditText[] editTextB = {numB00, numB01, numB02, numB10, numB11, numB12, numB20, numB21,
            numB22};
    private int[] editTextIdB = {R.id.numB00, R.id.numB01, R.id.numB02, R.id.numB10, R.id.numB11,
            R.id.numB12, R.id.numB20, R.id.numB21, R.id.numB22};

    private Context context;

    private String oper = "";

    private ProgressBar progressBar;

    public MatrixUtil matrOper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        context = StartPageActivity.this;

        Button autoFill = (Button) findViewById(R.id.auto_fill);
        autoFill.setOnClickListener(auto_fill);

        Button result = (Button) findViewById(R.id.buttonResult);
        result.setOnClickListener(resultListener);

        Button plus = (Button) findViewById(R.id.buttonPlus);
        plus.setOnClickListener(operation);

        Button minus = (Button) findViewById(R.id.buttonMinus);
        minus.setOnClickListener(operation);

        Button multi = (Button) findViewById(R.id.buttonMulti);
        multi.setOnClickListener(operation);

        progressBar =(ProgressBar) findViewById(R.id.progressBar);

        setupIdEt();

    }

    // this method initialize id number for our EditText View.
    private void setupIdEt() {

        for (int i = 0; i < editTextA.length; i++) {
            editTextA[i] = (EditText) findViewById(editTextIdA[i]);
            editTextB[i] = (EditText) findViewById(editTextIdB[i]);
            editTextA[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            editTextB[i].setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    private void setAutoFill(EditText[] etA, EditText[] etB) {

        for (int i = 0; i < SIZE; i++) {
            etA[i].setText("1");
            etB[i].setText("1");
        }
    }

    // on this method, we will get numbers, that was entered in to EditText matrix A;
    private String[] getStringArrayA() {

        editAnum = new String[editTextA.length];

        for (int i = 0; i < editTextA.length; i++) {

            editAnum[i] = editTextA[i].getText().toString();
        }

        return editAnum;
    }

    // on this method, we will get numbers, that was entered in EditText of matrix B and Value
    // will be return;
    private String[] getStringArrayB() {

        editBnum = new String[editTextA.length];

        for (int i = 0; i < editTextA.length; i++) {

            editBnum[i] = editTextB[i].getText().toString();
        }
        return editBnum;
    }

    private int[] conversionStringToIntA(String[] stringA) {

        editTextA_intValue = new int[editTextA.length];

        for (int i = 0; i < editTextA.length; i++) {

            editTextA_intValue[i] = Integer.parseInt(stringA[i]);
        }

        return editTextA_intValue;

    }


    private int[] conversionStringToIntB(String[] stringB) {

        editTextB_intValue = new int[editTextB.length];

        for (int i = 0; i < editTextA.length; i++) {

            editTextB_intValue[i] = Integer.parseInt(stringB[i]);
        }

        return editTextB_intValue;

    }

    private View.OnClickListener auto_fill = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.auto_fill:
                    setAutoFill(editTextA, editTextB);
                    break;
            }
        }
    };

    private View.OnClickListener operation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!isValidMatrix(editTextA)) {
                showAlertDialog(R.string.alert_title, R.string.alert_empty_matrixA_msg);

            } else if (!isValidMatrix(editTextB)) {
                showAlertDialog(R.string.alert_title, R.string.alert_empty_matrixB_msg);

          }

            switch (v.getId()) {

                case R.id.buttonPlus:
                    oper = "+";
                    break;

                case R.id.buttonMinus:
                    oper = "-";
                    break;

                case R.id.buttonMulti:
                    oper = "*";
                    break;

            }
        }
    };

    private View.OnClickListener resultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progressBar.setVisibility(View.VISIBLE);

            if (oper.equals("")) {

                showAlertDialogForSign();

            } else if (!isValidMatrix(editTextA)) {

                showAlertDialog(R.string.alert_title, R.string.alert_empty_matrixA_msg);

            } else if (!isValidMatrix(editTextB)) {

                showAlertDialog(R.string.alert_title, R.string.alert_empty_matrixB_msg);

            } else  {

                calculationThread();

            }
        }

    };

    private void calculationThread() {

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if (isValidMatrix(editTextA) && isValidMatrix(editTextB) && !oper.equals("")){

                    getStringArrayA();
                    getStringArrayB();

                    conversionStringToIntA(editAnum);
                    conversionStringToIntB(editBnum);

                    switch (oper) {

                        case "+":

                            tvResult = matrOper.add(editTextA_intValue, editTextB_intValue);
                            break;

                        case "-":

                            tvResult = matrOper.subtract(editTextA_intValue, editTextB_intValue);
                            break;

                        case "*":

                            int[][] matrA = new int [SIZE/3][SIZE/3];
                            int[][] matrB = new int [SIZE/3][SIZE/3];

                            matrA = MatrixUtil.conversionArrayToMatrix(editTextA_intValue);
                            matrB = MatrixUtil.conversionArrayToMatrix(editTextB_intValue);
                            matrA = MatrixUtil.multi(matrA, matrB);

                            tvResult = MatrixUtil.conversionMatrixToArray(matrA);
                            break;

                    }

                    Intent intentTo = new Intent(context, ResultPageActivity.class);
                    intentTo.putExtra(STRING_KEY, tvResult);
                    startActivity(intentTo);
                    progressBar.setVisibility(View.INVISIBLE);

            }
        }

    }, 5000);
    }

    private void showAlertDialog(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showAlertDialog(int titleId, int msgId) {

        showAlertDialog(getString(titleId), getString(msgId));
    }

    private void showAlertDialogForSign() {

        showAlertDialog(getString(R.string.alert_title_of_sign_title), getString(R.string.alert_title_of_sign_msg));
    }

    private void print(String s) {
        Log.wtf("MainActivity", s);
    }

    private boolean isValidMatrix(EditText[] matrix) {

        for (EditText et : matrix) {
            if (et.getText().toString().isEmpty()) {
                return false;
            }
        }

        return true;
    }

}


