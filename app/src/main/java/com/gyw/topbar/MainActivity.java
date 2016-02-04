package com.gyw.topbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.topbar)
    TopBar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTopBar.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onTopBarRightClick(View v) {
                Toast.makeText(MainActivity.this, "点击右边分享", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopBarLeftClick(View v) {
                Toast.makeText(MainActivity.this, "点击左边返回", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
