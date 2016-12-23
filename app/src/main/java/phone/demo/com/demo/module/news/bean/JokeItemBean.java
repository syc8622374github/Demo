package phone.demo.com.demo.module.news.bean;

/**
 * Created by cyc on 2016/12/22 0022.
 */

public class JokeItemBean {
    private String id; //唯一标识符
    private String title; //笑话标题
    private String text; //笑话正文
    private String img; //展示图
    private int type; //类型
    private String ct; //生成时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }
}
