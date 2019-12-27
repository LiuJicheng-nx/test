package com.waoqi.api.config;//package com.waoqi.api.config;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.waoqi.common.utils.CodeMsg;
//import com.waoqi.common.utils.Result;
//import com.waoqi.rongbao.utils.Md5Utils;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Map;
//import java.util.TreeMap;
//
///**
// * 解密参数
// */
//@WebFilter(filterName = "EncodeResponse", urlPatterns = "/lm/v1/*")
//public class EncodeResponse_bak implements config {
//
//    private static String SIGN_STR = "chiaRKJJY7mxITyjV1M0aWDDgiId7UlqF53JBSNdd8PGCig6IIimG3Fdm3ZkknyyivWblLR3b3tZjMQmxDArqyAINk4mJexuUHJ";
//
//
//    @Override
//    public void doFilter(ServletRequest request,
//                         ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//
//        System.out.println("开始过滤请求参数");
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String method = (httpServletRequest.getMethod());
//        Map<String, String[]> map = httpServletRequest.getParameterMap();
//        if(map.size() > 0){
//            //校验sign参数是否存在
//            String[] sign = map.get("sign");
//            if(sign == null || sign.length == 0){
//                try {
//                    this.returnJson(response);
//                    return;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            //校验参数
//            Map<String,Object> treeMap = new TreeMap<>();
//            for (String key : map.keySet()) {
//                if(!key.equals("sign")){
//                    treeMap.put(key , map.get(key)[0]);
//                }
//
//            }
//
//            String paramJsonString = JSONObject.toJSONString(treeMap);
//            paramJsonString = paramJsonString.trim() + SIGN_STR;
//            String paramMd5 = Md5Utils.md5(paramJsonString, "utf-8");
//            if(!paramMd5.equals(sign[0])){
//                try {
//                    this.returnJson(response);
//                    return;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//
//
//        chain.doFilter(request, response);
//
//    }
//
//
//    private void returnJson(ServletResponse response) throws Exception {
//        String json = JSONObject.toJSONString(Result.error(CodeMsg.SIGN_ERROR));
//        PrintWriter writer = null;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=utf-8");
//        writer = response.getWriter();
//        writer.print(json);
//    }
//
//
//    @Override
//    public void destroy() {
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//
//}
