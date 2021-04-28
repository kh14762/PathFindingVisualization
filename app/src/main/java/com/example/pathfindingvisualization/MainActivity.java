package com.example.pathfindingvisualization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    GraphView graphView;
    MovableFloatingActionButton mfaButton;
    ScrollView scrollView;
    RadioGroup radioGroup;
    RadioButton borderRadioBtn, startRadioBtn, endRadioBtn;

    boolean isVisable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphView = (GraphView) findViewById(R.id.graphView);
        mfaButton = (MovableFloatingActionButton) findViewById(R.id.movableFloatingActionButton);
        //  Add scrollView along with children
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        //  Add radio group and buttons
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        borderRadioBtn = (RadioButton) findViewById(R.id.borderRadioBtn);
        startRadioBtn = (RadioButton) findViewById(R.id.startRadioBtn);
        endRadioBtn = (RadioButton) findViewById(R.id.endRadioBtn);
    }

    public void onMfaButtonClick(View view) {
        if (isVisable) {
            scrollView.setVisibility(View.INVISIBLE);
            isVisable = false;
        } else {
            scrollView.setVisibility(View.VISIBLE);
            isVisable = true;
        }
    }

    public void onRadioButtonChecked(View view) {

        if (borderRadioBtn.isChecked()) {
            graphView.setWallsClicked(true);

            graphView.setStartClicked(false);
            graphView.setEndClicked(false);
        } else if (startRadioBtn.isChecked()) {
            graphView.setStartClicked(true);

            graphView.setWallsClicked(false);
            graphView.setEndClicked(false);
        } else if (endRadioBtn.isChecked()) {
            graphView.setEndClicked(true);

            graphView.setStartClicked(false);
            graphView.setWallsClicked(false);
        } else {
            graphView.setWallsClicked(false);
        }
    }

    public void onCheckBoxStartClicked() {

    }

    public void onCheckBoxEndClicked() {

    }
}
