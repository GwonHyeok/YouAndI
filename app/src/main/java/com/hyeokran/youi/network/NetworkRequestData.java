package com.hyeokran.youi.network;

import java.util.HashMap;

/**
 * Created by GwonHyeok on 15. 9. 2..
 *
 * @author GwonHyeok
 * @since 2015.09.02
 */
public class NetworkRequestData {
    private HashMap<String, Object> mParameter;

    public void setParameter(HashMap<String, Object> parameter) {
        this.mParameter = parameter;
    }

    public HashMap<String, Object> getParameter() {
        return this.mParameter;
    }
}
