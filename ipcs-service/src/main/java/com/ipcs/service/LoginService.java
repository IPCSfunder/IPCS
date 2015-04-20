package com.ipcs.service;

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

    @RequestMapping(value = "/loginService", method = RequestMethod.GET)
    public boolean login(@RequestParam(value = "user" ) String userName,@RequestParam(value = "pwd" ) String passWord){
       boolean authStatus = securityService.authenticateLoginInfo(userName,passWord);
        return authStatus;
    }
}
