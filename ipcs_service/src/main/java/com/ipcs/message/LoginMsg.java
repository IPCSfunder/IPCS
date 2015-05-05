package com.ipcs.message;

import java.util.List;

/**
 * Created by michael on 23/04/15.
 */
public class LoginMsg {

     /*
       This is the message model Class for Login Webservice
     */

     // TODO add status code for login service

     private boolean authStatus;
     private long id;
     private String account;
     private int statusCode;

     public LoginMsg(boolean authStatus, long id, String account, int statusCode) {
          this.authStatus = authStatus;
          this.id = id;
          this.account = account;
          this.statusCode = statusCode;
     }

     public long getId() {
          return id;
     }

     public int getStatusCode() {
          return statusCode;
     }

     public boolean isAuthStatus() {
          return authStatus;
     }

     public String getAccount() {
          return account;
     }
}
