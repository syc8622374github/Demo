package phone.demo.com.demo.bean;

import java.util.ArrayList;
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
    private ArrayList<String> thumbnailList;
    private ArrayList<String> imgList;

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getImgList() {
        return imgList;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public List<String> getThumbnailList() {
        return thumbnailList;
    }

    @Override
    public String toString() {
        return "ShowAPIItemBean{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", thumbnailList=" + thumbnailList +
                ", imgList=" + imgList +
                '}';
    }
}
