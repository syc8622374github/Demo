package phone.demo.com.demo.module.news.bean;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class NewsResBody {
    private int ret_code;
    private NewsPageBean pagebean; //返回的分页结构

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public NewsPageBean getPageBean() {
        return pagebean;
    }

    public void setPageBean(NewsPageBean pagebean) {
        this.pagebean = pagebean;
    }
}
