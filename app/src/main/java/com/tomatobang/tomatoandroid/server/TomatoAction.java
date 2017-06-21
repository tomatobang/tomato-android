package com.tomatobang.tomatoandroid.server;

/**
 * Created by kobepeng on 2017/6/19.
 */


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.tomatobang.tomatoandroid.server.network.http.HttpException;
import com.tomatobang.tomatoandroid.server.request.RegisterRequest;
import com.tomatobang.tomatoandroid.server.response.RegisterResponse;

import com.tomatobang.tomatoandroid.server.request.LoginRequest;
import com.tomatobang.tomatoandroid.server.response.LoginResponse;

import com.tomatobang.tomatoandroid.server.utils.NLog;
import com.tomatobang.tomatoandroid.server.utils.json.JsonMananger;

/**
 * Created by AMing on 16/1/14.
 * Company RongCloud
 */
@SuppressWarnings("deprecation")
public class TomatoAction extends BaseAction {
    private final String CONTENT_TYPE = "application/json";
    private final String ENCODING = "utf-8";

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public TomatoAction(Context context) {
        super(context);
    }


    /**
     * 注册
     *
     * @param username           用户名
     * @param password           密码
     * @param verification_token 验证码
     * @throws HttpException
     */
    public RegisterResponse register(String username, String password, String verification_token) throws HttpException {
        String url = getURL("user/register");
        StringEntity entity = null;
        try {
            entity = new StringEntity(JsonMananger.beanToJson(new RegisterRequest(username, password, verification_token)), ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RegisterResponse response = null;
        String result = httpManager.post(mContext, url, entity, CONTENT_TYPE);
        if (!TextUtils.isEmpty(result)) {
            NLog.e("RegisterResponse", result);
            response = jsonToBean(result, RegisterResponse.class);
        }
        return response;
    }

    /**
     * 登录: 登录成功后，会设置 Cookie，后续接口调用需要登录的权限都依赖于 Cookie。
     *
     * @param region   国家码
     * @param phone    手机号
     * @param password 密码
     * @throws HttpException
     */
    public LoginResponse login(String region, String phone, String password) throws HttpException {
        String uri = getURL("user/login");
        String json = JsonMananger.beanToJson(new LoginRequest(region, phone, password));
        StringEntity entity = null;
        try {
            entity = new StringEntity(json, ENCODING);
            entity.setContentType(CONTENT_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = httpManager.post(mContext, uri, entity, CONTENT_TYPE);
        LoginResponse response = null;
        if (!TextUtils.isEmpty(result)) {
            NLog.e("LoginResponse", result);
            response = JsonMananger.jsonToBean(result, LoginResponse.class);
        }
        return response;
    }

}