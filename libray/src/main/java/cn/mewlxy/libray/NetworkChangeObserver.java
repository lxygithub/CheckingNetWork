package cn.mewlxy.libray;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import cn.mewlxy.libray.listener.OnNetworkChangeListener;
import cn.mewlxy.libray.receiver.NetWorkChangeBroadcastReceiver;


/**
 * 类描述：
 * 创建人：luoxingyuan
 * 创建时间：2018/8/12 22:32
 * 修改人：luoxingyuan
 * 修改时间：2018/8/12 22:32
 * 修改备注：
 */
public class NetworkChangeObserver implements OnNetworkChangeListener
{

    private NetWorkChangeBroadcastReceiver receiver = new NetWorkChangeBroadcastReceiver(this);
    private View mTipView;
    private ConfigBuilder config = ConfigBuilder.getInstance();

    public void register(Context context)
    {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(receiver, intentFilter);
        initTipView(context, config);
    }

    public void setConfig(ConfigBuilder config)
    {
        this.config = config;
    }

    public void unRegister(Context context)
    {
        if (receiver != null)
        {
            context.unregisterReceiver(receiver);
        }
    }

    /**
     * 初始化view
     * @param context
     * @param config
     */
    private void initTipView(final Context context, ConfigBuilder config)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        mTipView = inflater.inflate(config.layoutResId, null); //提示View布局
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW ,
                PixelFormat.TRANSLUCENT);
        //使用非CENTER时，可以通过设置XY的值来改变View的位置
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = dp2px(context, config.marginTop);
        if (config.clickListener != null)
        {
            mTipView.setOnClickListener(config.clickListener);
        }
        if (mWindowManager != null)
        {
            mWindowManager.addView(mTipView, mLayoutParams);
        }
    }


    @Override
    public void networkChange(boolean isConnect)
    {
        mTipView.setVisibility(isConnect ? View.GONE : View.VISIBLE);
    }

    /**
     * dp转px
     * @param context
     * @return
     */
    private static int dp2px(Context context, float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
