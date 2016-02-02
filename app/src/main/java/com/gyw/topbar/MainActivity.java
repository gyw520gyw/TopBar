package com.gyw.topbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        mTopBar.setOnTopBarLeftClickListener(new TopBar.OnTopBarLeftClickListener() {
            @Override
            public void onTopBarLeftClick() {
                Toast.makeText(MainActivity.this, "点击左边返回", Toast.LENGTH_SHORT).show();
            }
        });

        mTopBar.setOnTopBarRightClickListener(new TopBar.OnTopBarRightClickListener() {
            @Override
            public void onTopBarRightClick() {
                Toast.makeText(MainActivity.this, "点击右边分享", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
