package cn.mewlxy.libray.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.lang.ref.WeakReference;

import cn.mewlxy.libray.ConfigBuilder;
import cn.mewlxy.libray.listener.OnNetworkChangeListener;
import cn.mewlxy.libray.utils.NetWorkUtil;
import cn.mewlxy.libray.utils.PingUtil;

/**
 * 类描述：网络变化广播接收器
 * 创建人：luoxingyuan
 * 创建时间：2018/8/12 11:33
 * 修改人：luoxingyuan
 * 修改时间：2018/8/12 11:33
 * 修改备注：
 */
public class NetWorkChangeBroadcastReceiver extends BroadcastReceiver
{

    private OnNetworkChangeListener networkChangeListener;

    public NetWorkChangeBroadcastReceiver(OnNetworkChangeListener networkChangeListener)
    {
        this.networkChangeListener = networkChangeListener;
    }


    @Override
    public void onReceive(Context context, Intent intent)
    {
        boolean isConnect = NetWorkUtil.isConnected(context);
        if (!TextUtils.isEmpty(ConfigBuilder.getInstance().url))
        {
            if (isConnect)
            {
                new PingAsyncTask(this).execute();
            }
        } else
        {
            networkChangeListener.networkChange(isConnect);
        }

    }


    @SuppressLint("StaticFieldLeak")
    private class PingAsyncTask extends AsyncTask<Void, Integer, Integer>
    {
        private final WeakReference<NetWorkChangeBroadcastReceiver> weakReference;

        PingAsyncTask(NetWorkChangeBroadcastReceiver receiver) {
            this.weakReference = new WeakReference<>(receiver);
        }
        @Override
        protected Integer doInBackground(Void... voids)
        {
            return PingUtil.ping();
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            super.onPostExecute(integer);
            NetWorkChangeBroadcastReceiver receiver = weakReference.get();
            if (receiver!=null)
            {
                receiver.networkChangeListener.networkChange(integer == 0);
            }
        }
    }
}
