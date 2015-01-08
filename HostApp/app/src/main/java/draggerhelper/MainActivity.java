package draggerhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hostapp.R;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button mQueen;
    private Button mHidden;
    private OuterLayout mOuterLayout;
    private LinearLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dragger);
        mOuterLayout = (OuterLayout) findViewById(R.id.outer_layout);
        mMainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mHidden = (Button) findViewById(R.id.hidden_button);
        mHidden.setOnClickListener(this);
        mQueen = (Button) findViewById(R.id.queen_button);
        mQueen.setOnClickListener(this);
        mMainLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mOuterLayout.isMoving()) {
                    v.setTop(oldTop);
                    v.setBottom(oldBottom);
                    v.setLeft(oldLeft);
                    v.setRight(oldRight);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        Toast t = Toast.makeText(this, b.getText() + " clicked", Toast.LENGTH_SHORT);
        t.show();
    }
}