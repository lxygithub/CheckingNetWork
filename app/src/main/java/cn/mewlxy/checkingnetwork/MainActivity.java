package cn.mewlxy.checkingnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.mewlxy.libray.ConfigBuilder;
import cn.mewlxy.libray.NetworkChangeObserver;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private NetworkChangeObserver observer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        observer = new NetworkChangeObserver();
        ConfigBuilder configBuilder = ConfigBuilder.getInstance();
        configBuilder
                .setLayoutResId(R.layout.layout_network_tip)
                .setMarginTop(50)
                .setClickListener(this);
        observer.register(this);

    }



    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        observer.unRegister(this);
    }

    @Override
    public void onClick(View v)
    {
        Toast.makeText(this,"view is clicked",Toast.LENGTH_SHORT).show();
    }
}
