package com.ipcs.controller.validator;

import com.ipcs.model.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Chen Chao
 */
public class PersonValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return Person.class.equals(clazz);
    }



    @Override
    public void validate(Object obj, Errors errors) {
        Person user = (Person) obj;
        if (null == user.getPersonDetail().getFirstName()||"".equals(user.getPersonDetail().getFirstName()))
            errors.rejectValue("personDetail.firstName", null, "First name not provided.");
        if (null == user.getPersonDetail().getLastName()||"".equals(user.getPersonDetail().getLastName()))
            errors.rejectValue("personDetail.lastName", null, "Last name not provided.");
        if (null == user.getPersonDetail().getDateOfBirth()||"".equals(user.getPersonDetail().getDateOfBirth()))
            errors.rejectValue("personDetail.dateOfBirth", null, "Date of birth not provided.");
        if (null == user.getPersonDetail().getSex()||"".equals(user.getPersonDetail().getSex()))
            errors.rejectValue("personDetail.sex", null, "Gender not provided.");
        if (null == user.getPersonDetail().getNationality()||"".equals(user.getPersonDetail().getNationality()))
            errors.rejectValue("personDetail.nationality", null, "Nationality not provided.");

        if (null == user.getPersonDetail().getAddress()||"".equals(user.getPersonDetail().getAddress()))
            errors.rejectValue("personDetail.address", null, "Address not provided.");
        if (null == user.getPersonDetail().getPostcode()||"".equals(user.getPersonDetail().getPostcode()))
            errors.rejectValue("personDetail.postcode", null, "Postcode not provided.");
        if(null!= user.getContacts()&&user.getContacts().size()!=0){
            if (null == user.getContacts().get(0).getContacterName()||"".equals(user.getContacts().get(0).getContacterName()))
                errors.rejectValue("contacts[0].contacterName", null, "Primary contact name not provided.");
            if (null == user.getContacts().get(0).getMobileNumber()||"".equals(user.getContacts().get(0).getMobileNumber()))
                errors.rejectValue("contacts[0].mobileNumber", null, "Mobile number not provided.");        }


    }
}
