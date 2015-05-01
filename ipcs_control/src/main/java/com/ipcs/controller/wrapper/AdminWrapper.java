package com.ipcs.controller.wrapper;

import com.ipcs.model.PersonDetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Chen Chao
 */
public class AdminWrapper {

    public static PersonDetail personDetailWrapper(Map<String,String> requestParams) throws ParseException {
        String firstName = requestParams.get("fist_name");
        String lastName = requestParams.get("last_name");
        Date dateOfBirth = (new SimpleDateFormat("yyyy-mm-dd")).parse(requestParams.get("date_of_birth"));
        PersonDetail.Sex sex = requestParams.get("sex")=="FEMALE"?PersonDetail.Sex.FEMALE:PersonDetail.Sex.MALE;
        Integer age = Integer.valueOf(requestParams.get("age"));
        String nationality = requestParams.get("nationality");
        PersonDetail personDetail = new PersonDetail.PersonBuilder().withFirstName(firstName).withLastName(lastName).withAge(age).withDob(dateOfBirth)
                .withSex(sex).withNationality(nationality).build();
        return personDetail;
    }

}
