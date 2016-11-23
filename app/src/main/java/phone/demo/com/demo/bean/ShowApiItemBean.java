package phone.demo.com.demo.bean;

import java.util.List;

/**
 * Created by cyc on 2016/11/23 0023.
 * 易源api item 实体类
 */

public class ShowApiItemBean {
    private String id;
    private String time;
    private String title;
    private String link;
    private List<String> imgList;

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getImgList() {
        return imgList;
    }

    @Override
    public String toString() {
        return "ShowApiItemBean{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", imgList=" + imgList +
                '}';
    }
}
