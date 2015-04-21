package com.ipcs.webservice;

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
        return "Hello World!";
    }

    @RequestMapping(value= "/loginService", method = RequestMethod.GET)
    public boolean loginService(@RequestParam(value="name", defaultValue="Person") String userName,@RequestParam(value="pwd", defaultValue="11") String passWord){
        boolean authStatus = securityService.authenticateLoginInfo(userName, passWord);
        return authStatus;
    }
}
