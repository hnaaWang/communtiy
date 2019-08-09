package com.mj.communtiy.controller;

import com.mj.communtiy.dto.AccessTokeDTO;
import com.mj.communtiy.dto.GithubUser;
import com.mj.communtiy.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2019/8/9.
 */
@Controller
public class AuthorizeController {

    @Autowired
    GitHubProvider gitHubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code") String code ,
                           @RequestParam(value = "state") String state){
        AccessTokeDTO accessTokeDTO = new AccessTokeDTO();
        accessTokeDTO.setClient_id("Iv1.72282759716c3176");
        accessTokeDTO.setClient_secret("0d59e5403cd8258a7ff999c9139e6845a1f92066");
        accessTokeDTO.setCode(code);
        accessTokeDTO.setRedirect_uri("http://localhost:8999/callback");
        accessTokeDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokeDTO);
        GithubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user.toString());
        return "index";
    }
}
