package com.example.pathfindingvisualization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GraphView graphView;
    MovableFloatingActionButton mfaButton;
    ScrollView scrollView;
    RadioGroup radioGroup;
    RadioButton borderRadioBtn, startRadioBtn, endRadioBtn;
    Button runBFS;

    boolean isVisible = true;

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
        runBFS = (Button) findViewById(R.id.button);
    }

    public void onBfsButtonClicked(View view) {
            new myAsynchTask().execute();

    }

    public void onMfaButtonClick(View view) {
        if (isVisible) {
            scrollView.setVisibility(View.INVISIBLE);
            isVisible = false;
        } else {
            scrollView.setVisibility(View.VISIBLE);
            isVisible = true;
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

    class myAsynchTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            scrollView.setVisibility(View.INVISIBLE);
            isVisible = false;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (graphView.startCell != null) {
                graphView.runBFS();
            }
           return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (graphView.startCell == null) {
                Toast.makeText(graphView.getContext(), "No Start Node Selected!", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void onCheckBoxStartClicked() {

    }

    public void onCheckBoxEndClicked() {

    }
}
