package cn.mewlxy.libray.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 类描述：网络工具
 * 创建人：luoxingyuan
 * 创建时间：2018/8/12 21:47
 * 修改人：luoxingyuan
 * 修改时间：2018/8/12 21:47
 * 修改备注：
 */
public class NetWorkUtil
{
    /**
     * 判断网络是否连接
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                return info.getState() == NetworkInfo.State.CONNECTED;
            }
        }
        return false;
    }

}
