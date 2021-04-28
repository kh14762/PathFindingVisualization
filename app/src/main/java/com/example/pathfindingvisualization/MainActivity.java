package com.example.pathfindingvisualization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    GraphView graphView;
    MovableFloatingActionButton mfaButton;
    ScrollView scrollView;
    CheckBox wallCheckBox, startCheckBox, endCheckBox;

    boolean isVisable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphView = (GraphView) findViewById(R.id.graphView);
        mfaButton = (MovableFloatingActionButton) findViewById(R.id.movableFloatingActionButton);
        //  Add scrollView along with children
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        wallCheckBox = (CheckBox) findViewById(R.id.checkBoxWalls);
        startCheckBox = (CheckBox) findViewById(R.id.checkBoxStart);
        endCheckBox = (CheckBox) findViewById(R.id.checkBoxEnd);
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

    public void onCheckBoxWallsClicked(View view) {
        if (wallCheckBox.isChecked()) {
            graphView.setWallsClicked(true);
        } else {
            graphView.setWallsClicked(false);
        }
    }

    public void onCheckBoxStartClicked() {

    }

    public void onCheckBoxEndClicked() {

    }
}
