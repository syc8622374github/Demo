package phone.demo.com.demo.module.news.bean;

import java.util.ArrayList;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class NewsPageBean {
    private int allNum; //所有记录数
    private int allPages; //所有页数
    private int currentPage; //当前页
    private int maxResult; //每页最大记录数
    private ArrayList<NewsItemBean> contentlist; //数据条目列表

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public ArrayList<NewsItemBean> getContentlist() {
        return contentlist;
    }

    public void setContentlist(ArrayList<NewsItemBean> contentlist) {
        this.contentlist = contentlist;
    }
}
