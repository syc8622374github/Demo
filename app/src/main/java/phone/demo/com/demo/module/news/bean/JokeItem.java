package phone.demo.com.demo.module.news.bean;

/**
 * Created by cyc on 2016/12/22 0022.
 */

public class JokeItem {
    private String title; //笑话标题
    private String text; //笑话正文
    private String ct; //生成时间

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

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }
}
