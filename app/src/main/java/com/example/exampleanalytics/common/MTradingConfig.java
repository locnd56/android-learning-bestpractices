package com.example.exampleanalytics.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by Mr.Incredible on 11/11/2015.
 */
public class MTradingConfig {

    public static final String authServer = "https://auth-api.vndirect.com.vn/";
    public static final String tradeServer = "https://trade-api.vndirect.com.vn/";

//    public static final String authServer = "https://auth-api-suat.vndirect.com.vn/";
//    public static final String tradeServer = "https://trade-api-suat.vndirect.com.vn/";

    public static final String priceServiceServer = "http://priceservice.vndirect.com.vn/";
    public static final String websocketServer = "https://websocket.vndirect.com.vn/notisocket/";

    public static final String UserAgentValue = "VndirectAndroidApp-v1.0";
    //public static final String getAllSyms = "https://finfo-api-suat.vndirect.com.vn/stocks";
    public static final String getAllSyms = "https://finfo-api.vndirect.com.vn/stocks/mini";
    /*check version app*/
    public static final String getNewVersionApp = "https://finfo-api.vndirect.com.vn/android/version?playUrl=vn.com.vndirect.stocks";

    public static final String gotoCHPlay = "https://play.google.com/store/apps/details?id=vn.com.vndirect.stocks";
    public static final String link_RegisterPortal = "https://www.vndirect.com.vn/portal/mo-tai-khoan-nha-dau-tu.shtml";
    public static final String link_ForgotPasswordPortal = "https://www.vndirect.com.vn/portal/quen-mat-khau.shtml";
    public static final String link_WatchList = "http://banggia.vndirect.com.vn/webview/bang-gia";
    public static final String link_News = "http://banggia.vndirect.com.vn/webview/tin-tuc";

    public static final String link_VtosGuide = "https://www.vndirect.com.vn/portal/tro-giup/hoi-dap-mat-khau-the-vtos.shtml";

    /* feedback mail config*/
    public final static String feedbackHostMail = "mail.info.vndirect.com.vn";
    public final static String feedbackPort = "587";
    public final static String feedbackProtocol = "starttls";
    public final static String feedbackSenderEmail = "itho@info.vndirect.com.vn";
    public final static String feedbackSenderPassword = "Fss@1234";
    public final static String feedbackReceiveEmail = "dev.vndirect@gmail.com";

    /* Ty le cac dinh va kich thuoc trong anh VTOS*/
    public final static float XYPositionRate = 161f / 784f;      // tỷ lệ giữa vị trí của ma trận (161) và chiều dài ảnh ma trận (784) tính theo góc trái trên cùng (ic_framecamera_vtos.png)
    public final static float matrixLengthRate = 553f / 784f;      // tỷ lệ giữa chiều dài của cell và chiều dài của ảnh ma trận (784) (ic_framecamera_vtos.png)

    public final static String folderVndirectDir = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            + "/VNDIRECT";
    public final static String picturePathFull = folderVndirectDir + File.separator + "%s - VTOSFULL.jpg";
    public final static String picturePathMatrix = folderVndirectDir + File.separator + "%s - VTOSMATRIX.jpg";

    /*Key lưu trạng thái của ảnh vtos*/
    public final static String mZoomKey = "%s_VTOSZOOM";
    public final static String mRotateKey = "%s_VTOSROTATE";
    public final static String mFocusxKey = "%s_VTOSPOSX";
    public final static String mFocusyKey = "%s_VTOSPOSY";

}
