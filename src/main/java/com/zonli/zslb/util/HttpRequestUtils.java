package com.zonli.zslb.util;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URLDecoder;

public class HttpRequestUtils {

    private static Logger logger =Logger.getLogger (HttpRequestUtils.class);    //日志记录

    private static String charset = "UTF-8";

    /**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }

    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), charset);
                entity.setContentEncoding(charset);
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, charset);
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity(),charset);
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
//                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @return
     */
    public static String httpPut(String url,JSONObject jsonParam){
        //put请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String str = "false";
        HttpPut method = new HttpPut(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), charset);
                entity.setContentEncoding(charset);
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, charset);
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity(),charset);

                } catch (Exception e) {
                    logger.error("put请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("put请求提交失败:" + url, e);
        }
        return str;
    }
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static String httpGet(String url){
        //get请求返回结果
        String strResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                 strResult = EntityUtils.toString(response.getEntity(),charset);
                /**把json字符串转换成json对象**/
//                    jsonResult = JSONObject.fromObject(str);
                url = URLDecoder.decode(url, charset);
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return strResult;
    }


    public static void main(String[] args){
        String url="http://192.168.1.116:8500/v1/catalog/nodes?dc=dc1";
        String jsonObject=HttpRequestUtils.httpGet(url);
        System.out.println(jsonObject);

//        String url="http://192.168.1.116:8500/v1/catalog/register";
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("Node","test");
//        jsonObject1.put("Address","192.168.1.11");
//        JSONObject jsonObject = HttpRequestUtils.httpPost(url,jsonObject1,true);
//        System.out.println(jsonObject);

    }
}