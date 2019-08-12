package com.mj.communtiy.controller;

import com.mj.communtiy.dto.AccessTokeDTO;
import com.mj.communtiy.dto.GithubUser;
import com.mj.communtiy.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/8/9.
 */
@Controller
public class AuthorizeController {

    @Autowired
    GitHubProvider gitHubProvider;


    @Value("${gitHub.client.clientId}")
    private String clientId;
    @Value("${gitHub.client.clientSecret}")
    private String clientSecret;
    @Value("${gitHub.redirectUri}")
    private String redirectUri;


    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code") String code ,
                           @RequestParam(value = "state") String state,HttpServletRequest request){
        AccessTokeDTO accessTokeDTO = new AccessTokeDTO();
        accessTokeDTO.setClient_id(clientId);
        accessTokeDTO.setClient_secret(clientSecret);
        accessTokeDTO.setCode(code);
        accessTokeDTO.setRedirect_uri(redirectUri);
        accessTokeDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokeDTO);
        GithubUser user = gitHubProvider.getUser(accessToken);
        if(user!=null ){
            request.getSession().setAttribute("user",user);
        }
        System.out.println(user.toString());
        return "index";
    }
}
