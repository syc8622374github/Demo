package phone.demo.com.demo.module.news.bean;

import java.util.ArrayList;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class NewsChannelResBody {
    private int totalNum; //总记录数
    private ArrayList<Channel> channelList; //频道列表

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(ArrayList<Channel> channelList) {
        this.channelList = channelList;
    }
}
