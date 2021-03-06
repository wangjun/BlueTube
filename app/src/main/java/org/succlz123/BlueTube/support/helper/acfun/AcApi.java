package org.succlz123.bluetube.support.helper.acfun;

import org.succlz123.bluetube.bean.acfun.AcBangumi;
import org.succlz123.bluetube.bean.acfun.AcContentInfo;
import org.succlz123.bluetube.bean.acfun.AcContentReply;
import org.succlz123.bluetube.bean.acfun.AcContentVideo;
import org.succlz123.bluetube.bean.acfun.AcEssay;
import org.succlz123.bluetube.bean.acfun.AcGetH5ByVid;
import org.succlz123.bluetube.bean.acfun.AcReBanner;
import org.succlz123.bluetube.bean.acfun.AcReHot;
import org.succlz123.bluetube.bean.acfun.AcReOther;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import retrofit.http.Streaming;

/**
 * Created by fashi on 2015/7/19.
 */
public class AcApi {
    /**
     * @return 都有而且不用改变的url参数
     */
    public static HashMap getBaseMap() {
        HashMap map = new HashMap();
        map.put(AcString.APP_VERSION, AcString.APP_NUM);
        map.put(AcString.SYS_NAME, AcString.SYS_NAME_ANDROID);
        map.put(AcString.SYS_VERSION, AcString.SYS_VERSION_ANDROID);
        map.put(AcString.RESOLUTION, AcString.RESOLUTION_WIDTH_HEIGHT);
        map.put(AcString.MARKET, AcString.MARKET_NAME);
        return map;
    }

    /**
     * 1
     * http://api.acfun.tv/apiserver
     * /recommend/list
     * ?app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 首页横幅
     */
    public static HashMap getAcReBannerUrl() {
        HashMap map = getBaseMap();
        return map;
    }

    /**
     * http://api.acfun.tv/apiserver
     * /recommend/page
     * ?pageSize=10&pageNo=1
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 热门焦点
     */
    public static HashMap getAcReHotUrl(String pageNo) {
        HashMap<String, String> map = getBaseMap();
        map.put(AcString.PAGE_SIZE, AcString.PAGE_SIZE_NUM_20);
        map.put(AcString.PAGE_NO, pageNo);
        return map;
    }

    /**
     * http://api.acfun.tv/apiserver/
     * content/channel
     * ?channelIds=106,107,108,109,67,120
     * &pageSize=20&pageNo=1
     * &orderBy=5
     * &range=604800000
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @param channelId 根据不同的channelId
     * @param orderBy   按照什么来排序来还返回 最后回复 最新发布等
     * @param range     返回数据是多少时间内统计的 一周 一月 三月 总共
     * @return 返回不同的分区信息
     */
    public static HashMap getAcReOtherUrl(String channelId, String orderBy, String range) {
        HashMap<String, String> map = getBaseMap();
        map.put(AcString.CHANNEL_IDS, channelId);
        map.put(AcString.PAGE_SIZE, AcString.PAGE_SIZE_NUM_10);
        map.put(AcString.PAGE_NO, AcString.PAGE_NO_NUM_1);
        map.put(AcString.ORDER_BY, orderBy);
        map.put(AcString.RANGE, range);
        return map;
    }

    public interface getAcRecommend {

        @GET(AcString.RECOMMEND_LIST)
        Call<AcReBanner> onAcReBannerResult(@QueryMap() Map<String, String> options);

        @GET(AcString.RECOMMEND_PAGE)
        Call<AcReHot> onAcReHotResult(@QueryMap() Map<String, String> options);

        @GET(AcString.CONTENT_CHANNEL)
        Call<AcReOther> onAcReOtherResult(@QueryMap() Map<String, String> options);
    }


    /**
     * http://api.acfun.tv/apiserver
     * /content/channel
     * ?channelIds=67
     * &pageSize=20&pageNo=1
     * &orderBy=7
     * &range=604800000
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 分区内不同板块数据
     */
    public static HashMap getAcPartitionUrl(String channelIds, String orderBy, String range, String pageSize, String pagerNo) {
        HashMap map = getBaseMap();
        map.put(AcString.CHANNEL_IDS, channelIds);
        map.put(AcString.PAGE_SIZE, pageSize);
        map.put(AcString.PAGE_NO, pagerNo);
        map.put(AcString.ORDER_BY, orderBy);
        map.put(AcString.RANGE, range);

        return map;
    }

    public interface getAcPartition {

        @GET(AcString.CONTENT_CHANNEL)
        Call<AcReOther> onResult(@QueryMap() Map<String, String> options);

        @GET(AcString.CONTENT_CHANNEL)
        Call<AcEssay> onEssayResult(@QueryMap() Map<String, String> options);
    }


    /**
     * http://api.acfun.tv/apiserver
     * /content/info?contentId=2069095
     * &version=2
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 视频具体信息
     */
    public static HashMap getAcContentInfoUrl(String contentId) {
        HashMap map = getBaseMap();
        map.put(AcString.VERSION, AcString.VERSION_NUM_2);
        map.put(AcString.CONTENT_ID, contentId);

        return map;
    }

    public interface getAcContentInfo {
        @GET(AcString.CONTENT_INFO)
        Call<AcContentInfo> onContentInfoResult(@QueryMap() Map<String, String> options);
    }

    /**
     * http://www.acfun.tv
     * /comment/content/list?version=4&contentId=2086956
     * &pageSize=20&pageNo=1
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 视频的评论信息
     */
    public static HashMap getAcContentReplyUrl(String contentId, String pageSize, String pageNo) {
        HashMap map = getBaseMap();
        map.put(AcString.VERSION, AcString.VERSION_NUM_4);
        map.put(AcString.CONTENT_ID, contentId);
        map.put(AcString.PAGE_SIZE, pageSize);
        map.put(AcString.PAGE_NO, pageNo);

        return map;
    }

    public interface getAcContentReply {
        @GET(AcString.CONTENT_REPLY)
        Call<AcContentReply> onContentReplyResult(@QueryMap() Map<String, String> options);
    }

    /**
     * http://api.letvcloud.com/gpc.php
     * ?uu=2d8c027396
     * &vu=311043eae0
     * &cf=android
     * &format=json
     * &ver=2.0
     * &sign=signxxxxx
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 乐视视频的播放地址
     */
    public static HashMap getAcContentVideoUrl(String sourceId) {
        HashMap map = getBaseMap();
        map.put(AcString.UU, AcString.UU_STRING);
        map.put(AcString.CF, AcString.CF_TYPE);
        map.put(AcString.FORMAT, AcString.FORMAT_TYPE);
        map.put(AcString.LETV_VER, AcString.LETV_VER_NUM);
        map.put(AcString.SIGN, AcString.SIGNXXXXX);
        map.put(AcString.VU, sourceId);

        return map;
    }

    public interface getAcContentLeTvVideo {
        @GET(AcString.LETV_URL_GPC)
        Call<AcContentVideo> onContentResult(@QueryMap() Map<String, String> options);
    }

    /**
     * http://www.acfun.tv
     * /video/getH5ByVid.aspx
     * ?vid=2268176
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 其他视频的播放地址 html5(优酷 土豆 渣浪 企鹅)
     */
    public static HashMap getAcContentHtml5VideoUrl(String videoId) {
        HashMap map = getBaseMap();
        map.put(AcString.VID, videoId);

        return map;
    }

    public interface getAcContentHtml5Video {
        @GET(AcString.VIDEO_GET_H5_BY_VID)
        Call<AcGetH5ByVid> onContentResult(@QueryMap() Map<String, String> options);
    }

    public interface getVideoDownloadUrl {
        @GET("/{url}")
        Call onResult(@Path(value = "url", encoded = false) String url);
    }

    /**
     * http://danmu.aixifan.com
     * /2522561
     * ?app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 弹幕信息地址
     */
    public static HashMap getAcContentDanMuUrl() {
        HashMap map = getBaseMap();

        return map;
    }

    public interface getAcContentDanMu {
        @GET("{sourceId}")
        Call onContentResult(@QueryMap() Map<String, String> options, @Path("sourceId") String sourceId);
    }

    /**
     * http://icao.acfun.tv
     * /bangumi/week
     * ?bangumiTypes=1
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 每周新番时间表
     */
    public static HashMap getAcBangumiUrl(String types) {
        HashMap map = getBaseMap();
        map.put(AcString.BANGUMI_TYPES, types);
        return map;
    }

    public interface getAcBangumi {
        @GET(AcString.BANGUMI_WEEK)
        Call<AcBangumi> onBangumiResult(@QueryMap() Map<String, String> options);
    }

    /**
     * http://api.acfun.tv/apiserver/
     * content/rank
     * ?pageSize=20&pageNo=1
     * &channelIds=96,97,98,99,100,93,94,95,101,102,103,104,105,86,87,88,89,121,106,107,108,109,67,120,90,91,92,122,83,84,85,82
     * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
     *
     * @return 每周新番时间表
     */
    public static HashMap getAcRankingUrl(String channelIds, String pageNo) {
        HashMap map = getBaseMap();
        map.put(AcString.CHANNEL_IDS, channelIds);
        map.put(AcString.PAGE_SIZE, AcString.PAGE_SIZE_NUM_20);
        map.put(AcString.PAGE_NO, pageNo);
        return map;
    }

    public interface getAcRanking {
        @GET(AcString.CONTENT_RANK)
        Call<AcReOther> onRankingResult(@QueryMap() Map<String, String> options);
    }

    public interface getDownLoad {
        @GET(AcString.CONTENT_RANK)
        @Streaming
        Call<AcReOther> getData(@QueryMap() Map<String, String> options);
    }
}
