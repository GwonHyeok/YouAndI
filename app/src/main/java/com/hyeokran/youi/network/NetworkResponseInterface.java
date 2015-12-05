package com.hyeokran.youi.network;

/**
 * 서버와 통신 후 넘어온 데이터 들을 Activity 에 넘겨주는 Interface
 * <p/>
 * Created by GwonHyeok on 15. 9. 3..
 *
 * @author GwonHyeok
 * @since 2015.09.03
 */
public interface NetworkResponseInterface {
    void getNetworkData(String apiName, String errorMsg, int code);
}