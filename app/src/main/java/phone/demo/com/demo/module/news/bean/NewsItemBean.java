package phone.demo.com.demo.module.news.bean;

import java.util.ArrayList;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class NewsItemBean {
    private String title; //新闻标题
    private String link; //新闻详情链接
    private String pubDate; //发布时间
    private String source; //来源网站
    private String desc; //新闻简要描述
    private String channelId; //频道id
    private String channelName; //频道名称
    private String nid; //新闻对应的外网id
    private ArrayList<NewsImageBean> imageurls; //图片列表
    private String content; //新闻正文,txt格式
    private String html; //新闻正文,html格式

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public ArrayList<NewsImageBean> getImageurls() {
        return imageurls;
    }

    public void setImageurls(ArrayList<NewsImageBean> imageurls) {
        this.imageurls = imageurls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
