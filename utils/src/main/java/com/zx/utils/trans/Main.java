package com.zx.utils.trans;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20191112000356087";
    private static final String SECURITY_KEY = "YAaRpgaKT3EbdBALjuK6";

    public static void main(String[] args) throws Exception{
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "专利代理合同纠纷";
        ResultDTO resultDTO = api.getTrans(query, "auto", "cht");
        System.out.println(resultDTO);
    }

}
