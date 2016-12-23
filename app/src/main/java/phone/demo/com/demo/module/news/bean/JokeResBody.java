package phone.demo.com.demo.module.news.bean;

import java.util.ArrayList;

/**
 * Created by cyc on 2016/12/22 0022.
 */

public class JokeResBody {
    private String allNum; //总条数
    private String allPages; //总页数
    private String currentPage; //当前页
    private String maxResult; //每页最大数
    private ArrayList<JokeItemBean> contentlist; //分页实体结构

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    public String getAllPages() {
        return allPages;
    }

    public void setAllPages(String allPages) {
        this.allPages = allPages;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(String maxResult) {
        this.maxResult = maxResult;
    }

    public ArrayList<JokeItemBean> getContentlist() {
        return contentlist;
    }

    public void setContentlist(ArrayList<JokeItemBean> contentlist) {
        this.contentlist = contentlist;
    }
}
