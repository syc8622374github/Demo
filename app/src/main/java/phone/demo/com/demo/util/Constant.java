package phone.demo.com.demo.util;

/**
 * Created by LiCola on  2015/12/19  18:15
 * 常量类 用于保存字段的key值
 */
public class Constant {
    public static final String EMPTY_STRING = "";
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static final String Authorization="Authorization";

    //token information
    public static final String TOKENACCESS = "TokenAccess";
    public static final String TOKENREFRESH = "TokenRefresh";
    public static final String TOKENTYPE = "TokenType";
    public static final String TOKENEXPIRESIN = "TokeExpiresIn";

    //user information
    public static final String ISLOGIN = "isLogin";
    public static final String LOGINTIME = "loginTime";
    public static final String USERACCOUNT = "userAccount";
    public static final String USERPASSWORD = "userPassword";


    public static final String USERNAME = "userName";
    public static final String USERID = "userID";

    public static final String USERHEADKEY = "userHeadKey";

    public static final String USEREMAIL = "userEmail";

    //board info
    public static final String BOARDTILTARRAY="boardTitleArray";
    public static final String BOARDIDARRAY="boardIdArray";

    //,
    public static final String SEPARATECOMMA=",";

    //search
    public static final String HISTORYTEXT = "historyText";

    //http limit number
    public static final int LIMIT = 10;

    //RxView点击防止抖动时间间隔
    public static final long throttDuration=300;

    //用户喜欢操作的操作字段
    public static final String OPERATELIKE = "like";
    public static final String OPERATEUNLIKE = "unlike";

    //用户对画板的关注操作字段
    public static final String OPERATEFOLLOW = "follow";
    public static final String OPERATEUNFOLLOW = "unfollow";

    //获得用户画板列表详情的操作符
    public static final String OPERATEBOARDEXTRA="recommend_tags";
    public static final boolean OPERATECHECK=true;

    //删除画板的操作符
    public static final String OPERATEDELETEBOARD="DELETE";

    /*******************全局常量***********************/
    public static final String TYPE_KEY = "key"; //搜索关键字
    public static final String TITLE = "title"; //标题
    public static final String URL = "url"; //地址

    //bugly appid
    public static final String BUGLY_APP_ID = "900060052";

     /*==showAPI 易源常量==*/

    //ShowAPI secret
    public static final String SECRET = "8140bd31383242c0ba08eb912f4f5002";
    //ShowAPI appid
    public static final String APPID = "27035";

    /**
     * 黑白漫画类型
     * type=/category/weimanhua/kbmh 恐怖漫画
     * type=/category/weimanhua/gushimanhua 故事漫画
     * type=/category/duanzishou 段子手
     * type=/category/lengzhishi 冷知识
     * type=/category/qiqu 奇趣
     * type=/category/dianying 电影
     * type=/category/gaoxiao 搞笑
     * type=/category/mengchong 萌宠
     * type=/category/xinqi 新奇
     * type=/category/huanqiu 环球
     * type=/category/sheying 摄影
     * type=/category/wanyi 玩艺
     * type=/category/chahua 插画
     */
    public static final String CARTTON_BAW_TYPE_KBMH = "/category/weimanhua/kbmh";
    public static final String CARTTON_BAW_TYPE_GSMH = "/category/weimanhua/gushimanhua";
    public static final String CARTTON_BAW_TYPE_DZS = "/category/weimanhua/duanzishou";
    public static final String CARTTON_BAW_TYPE_LZS = "/category/weimanhua/lengzhishi";
    public static final String CARTTON_BAW_TYPE_QIQU = "/category/weimanhua/qiqu";
    public static final String CARTTON_BAW_TYPE_DY = "/category/weimanhua/dianying";
    public static final String CARTTON_BAW_TYPE_GX = "/category/weimanhua/gaoxiao";
    public static final String CARTTON_BAW_TYPE_MC = "/category/weimanhua/mengchong";
    public static final String CARTTON_BAW_TYPE_XQ = "/category/weimanhua/xinqi";
    public static final String CARTTON_BAW_TYPE_HQ = "/category/weimanhua/huanqiu";
    public static final String CARTTON_BAW_TYPE_SY = "/category/weimanhua/sheying";
    public static final String CARTTON_BAW_TYPE_WY = "/category/weimanhua/wanyi";
    public static final String CARTTON_BAW_TYPE_CH = "/category/weimanhua/chahua";
    public static final String CARTTON_CONNOTATION_TYPE_ALL = "connotation_cartoon";

    /**
     * 新闻类型
     */
    public static final String NEWS_JOKES_TYPE = "/341"; //笑话大全

}
