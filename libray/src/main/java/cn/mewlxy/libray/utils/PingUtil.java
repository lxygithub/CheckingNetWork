package cn.mewlxy.libray.utils;

import android.annotation.SuppressLint;

import java.io.IOException;

import cn.mewlxy.libray.ConfigBuilder;


/**
 * 类描述：ping命令工具
 * 创建人：luoxingyuan
 * 创建时间：2018/8/12 11:50
 * 修改人：luoxingyuan
 * 修改时间：2018/8/12 11:50
 * 修改备注：
 */
public class PingUtil
{


    private static ConfigBuilder configBuilder = ConfigBuilder.getInstance();

    /**
     * @return 0为成功
     */
    @SuppressLint("DefaultLocale")
    public static int ping()
    {
        Process p;
        try
        {
            //ping -c 3 -w 100  中  ，-c 是指ping的次数 3是指ping 3次 ，-w 100  以秒为单位指定超时间隔，是指超时时间为100秒
            p = Runtime.getRuntime().exec(String.format("ping -c %d -w %d %s", configBuilder.times,
                    configBuilder.timeout, configBuilder.url));
            return p.waitFor();

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return -1;
    }


}
