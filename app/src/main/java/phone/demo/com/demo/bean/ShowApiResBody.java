package phone.demo.com.demo.bean;

/**
 * Created by cyc on 2016/11/23 0023.
 *
 */

public class ShowAPIResBody {
    private String title;
    private int ret_code;
    private String img;
    private String currentPage;
    private ShowAPIItemBean item;
    private ShowAPIPageBean pagebean;

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

    public ShowAPIItemBean getItem() {
        return item;
    }

    public ShowAPIPageBean getPagebean() {
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
