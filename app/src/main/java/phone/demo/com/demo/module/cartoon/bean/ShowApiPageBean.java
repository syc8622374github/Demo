package phone.demo.com.demo.module.cartoon.bean;

import java.util.ArrayList;

/**
 * Created by cyc on 2016/11/23 0023.
 * 易源api pagebean 实体类
 */

public class ShowApiPageBean {
    private String allPages;
    private String maxResult;
    private int currentPage;
    private ArrayList<ShowApiItemBean> contentlist;
    private boolean hasMorePage;

    public String getAllPages() {
        return allPages;
    }

    public String getMaxResult() {
        return maxResult;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public ArrayList<ShowApiItemBean> getContentlist() {
        return contentlist;
    }

    public boolean isHasMorePage() {
        return hasMorePage;
    }

    @Override
    public String toString() {
        return "ShowApiPageBean{" +
                "allPages='" + allPages + '\'' +
                ", maxResult='" + maxResult + '\'' +
                ", currentPage=" + currentPage +
                ", contentlist=" + contentlist +
                ", hasMorePage=" + hasMorePage +
                '}';
    }
}
