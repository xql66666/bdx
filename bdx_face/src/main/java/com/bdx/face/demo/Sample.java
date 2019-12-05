package com.bdx.face.demo;

import com.baidu.aip.face.AipFace;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author: 磊大大
 * @date: 2019/11/7 10:18
 */
public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "17714754";
    public static final String API_KEY = "EhyoZ0etsm9RPOQCR2EzoI7H";
    public static final String SECRET_KEY = "uVd4Q82wpRUFImPnXkxn81h0RmAiNETp";

    private static AipFace client;

    public static void main(String[] args) {
        // 初始化一个AipFace
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口

        String image = "";
        String imageType = "URL";

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age,beauty");
        options.put("max_face_num", "1");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        // 人脸检测
        JSONObject res = client.detect(image, imageType, options);
        if (res == null) {
            System.out.println("识别失败");
        }else {
            JSONObject result = (JSONObject) res.get("result");
            JSONArray face_list = result.getJSONArray("face_list");
            JSONObject o = (JSONObject) face_list.get(0);
            Double a = (Double) o.get("face_probability");
            if (a > 0.8) {
                System.out.println(a);
                System.out.println("是一个人脸");
            }else {
                System.out.println("不是一个人脸");
            }

            System.out.println(res.toString(2));
        }


    }


}
