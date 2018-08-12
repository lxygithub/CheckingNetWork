package cn.mewlxy.libray;

import android.view.View;

/**
 * 类描述：全局配置
 * 创建人：luoxingyuan
 * 创建时间：2018/8/12 22:09
 * 修改人：luoxingyuan
 * 修改时间：2018/8/12 22:09
 * 修改备注：
 */
public class ConfigBuilder
{

    private static ConfigBuilder configBuilder;
    /**
     * @param times 次数
     */

    public int times = 3;
    /**
     * @param timeout 超时时间
     */
    public int timeout = 10;
    /**
     * @param url 要测试的主机地址
     */
    public String url;

    /**
     * view 的点击事件
     */
    public View.OnClickListener clickListener;

    /**
     * 自定义view资源id
     */
    public int layoutResId;
    /**
     * 距离顶部的margin值 dp
     */
    public int marginTop;

    private ConfigBuilder()
    {
    }

    public static synchronized ConfigBuilder getInstance()
    {
        try
        {
            if (configBuilder == null)
            {
                configBuilder = new ConfigBuilder();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return configBuilder;
    }


    public ConfigBuilder setTimes(int times)
    {
        this.times = times;
        return this;
    }

    public ConfigBuilder setTimeout(int timeout)
    {
        this.timeout = timeout;
        return this;
    }

    public ConfigBuilder setUrl(String url)
    {
        this.url = url;
        return this;
    }

    public ConfigBuilder setClickListener(View.OnClickListener clickListener)
    {
        this.clickListener = clickListener;
        return this;
    }

    public ConfigBuilder setLayoutResId(int layoutResId)
    {
        this.layoutResId = layoutResId;
        return this;
    }

    public ConfigBuilder setMarginTop(int marginTop)
    {
        this.marginTop = marginTop;
        return this;
    }
}
