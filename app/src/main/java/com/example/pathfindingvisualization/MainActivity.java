package com.example.pathfindingvisualization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.MovementMethod;
import android.view.Window;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GraphView graphView;
    MovableFloatingActionButton mfaButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphView = (GraphView) findViewById(R.id.graphView);
        mfaButton = (MovableFloatingActionButton) findViewById(R.id.movableFloatingActionButton);
    }

}
