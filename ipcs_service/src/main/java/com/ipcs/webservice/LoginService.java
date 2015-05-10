package com.ipcs.webservice;

import com.ipcs.message.LoginMsg;
import com.ipcs.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by michael on 20/04/15.
 */

@RestController
public class LoginService {
    @Autowired
    private SecurityService securityService;

    @RequestMapping("*")
    public String index(){
        return "This is the index page!";
    }

    @RequestMapping(value= "/loginService", method = RequestMethod.GET)
    public LoginMsg loginService(@RequestParam(value="name") String userName,@RequestParam(value="pwd") String passWord){
        if(userName == null || passWord == null){
            return new LoginMsg(false,-1,"Null",-1);
        }
        long id = securityService.getAuthenticatedUserID(userName, passWord);
        if(id == -1){
            return new LoginMsg(false,-1,"Null",-1);
        }
        return new LoginMsg(true,id,userName,1);
    }
}
