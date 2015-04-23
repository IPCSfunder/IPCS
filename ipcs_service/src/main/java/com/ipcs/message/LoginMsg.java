package com.ipcs.message;

import java.util.List;

/**
 * Created by michael on 23/04/15.
 */
public class LoginMsg {

     private boolean authStatus;
     private String account;

     public LoginMsg(boolean authStatus, String account) {
          this.authStatus = authStatus;
          this.account = account;
     }


     public boolean isAuthStatus() {
          return authStatus;
     }

     public String getAccount() {
          return account;
     }
}
