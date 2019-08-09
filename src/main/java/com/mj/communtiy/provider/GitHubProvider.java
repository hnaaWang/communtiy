package com.mj.communtiy.provider;

import com.alibaba.fastjson.JSON;
import com.mj.communtiy.dto.AccessTokeDTO;
import com.mj.communtiy.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Administrator on 2019/8/9.
 */

@Component
public class GitHubProvider {

    public static final String ACCESSTOKEURL = "https://github.com/login/oauth/access_token";

    public static final String ACCESSTOKEUSERURL = "https://api.github.com/user";

    public String getAccessToken(AccessTokeDTO accessTokeDTO)  {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokeDTO));
        Request request = new Request.Builder()
                .url(ACCESSTOKEURL)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();

            System.out.println(result);
            return result.split("&")[0].split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ACCESSTOKEUSERURL + "?access_token=" + accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            GithubUser githubUser = JSON.parseObject(result,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
