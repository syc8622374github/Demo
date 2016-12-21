package phone.demo.com.demo.module.cartoon.bean;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class CartoonResBody {
    private String title;
    private int ret_code;
    private String img;
    private String currentPage;
    private ShowApiItemBean item;
    private ShowApiPageBean pagebean;

    public String getTitle() {
        return title;
    }

    public int getRet_code() {
        return ret_code;
    }

    public String getImg() {
        return img;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public ShowApiItemBean getItem() {
        return item;
    }

    public ShowApiPageBean getPagebean() {
        return pagebean;
    }

    @Override
    public String toString() {
        return "ShowAPIResBody{" +
                "title='" + title + '\'' +
                ", ret_code=" + ret_code +
                ", img='" + img + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", item=" + item +
                ", pagebean=" + pagebean +
                '}';
    }
}
