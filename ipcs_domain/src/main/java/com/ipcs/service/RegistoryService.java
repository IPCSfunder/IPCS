/**
 * 
 */
package com.ipcs.service;

import com.ipcs.model.Person;

/**
 * @author Chen Chao
 *
 */
public interface RegistoryService {
    public void registerNewPerson(Person person);
    
    public String retrievePasswordByName(String userName);
    
    public String retrievePasswordByContactNumber(String phoneNumber);

}
