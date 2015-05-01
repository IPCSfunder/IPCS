package com.ipcs.controller.wrapper;

import com.ipcs.model.Contact;
import com.ipcs.model.PersonDetail;
import com.ipcs.model.RelationshipType;
import com.ipcs.service.AdminService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Chen Chao
 */
public class AdminWrapper {

    public static PersonDetail personDetailWrapper(Map<String, String> requestParams) throws ParseException {
        String firstName = requestParams.get("fist_name");
        String lastName = requestParams.get("last_name");
        Date dateOfBirth = (new SimpleDateFormat("yyyy-mm-dd")).parse(requestParams.get("date_of_birth"));
        PersonDetail.Sex sex = requestParams.get("sex") == "FEMALE" ? PersonDetail.Sex.FEMALE : PersonDetail.Sex.MALE;
        Integer age = Integer.valueOf(requestParams.get("age"));
        String nationality = requestParams.get("nationality");
        PersonDetail personDetail = new PersonDetail.PersonBuilder().withFirstName(firstName).withLastName(lastName).withAge(age).withDob(dateOfBirth)
                .withSex(sex).withNationality(nationality).build();
        return personDetail;
    }

    public static Contact primaryContactWrapper(Map<String, String> requestParams, AdminService adminService) throws ParseException {
        String primaryContactName = requestParams.get("primary_contact_name");
        if (null == primaryContactName || "".equals(primaryContactName))
            return null;
        String primaryRelationship = requestParams.get("primary_relationship");
        String primaryMobileNumber = requestParams.get("primary_mobile_number");
        RelationshipType relationshipType = adminService.getRelationshipTypeByName(primaryRelationship);
        Contact primaryContact = new Contact();
        primaryContact.setContacterName(primaryContactName);
        primaryContact.setMobileNumber(primaryMobileNumber);
        primaryContact.setRelationshipType(relationshipType);
        primaryContact.setPrimary(Boolean.valueOf(true));
        return primaryContact;
    }

    public static Contact secondaryContactWrapper(Map<String, String> requestParams, AdminService adminService) throws ParseException {
        String secondaryContactName = requestParams.get("secondary_contact_name");
        if (null == secondaryContactName || "".equals(secondaryContactName))
            return null;
        String secondaryRelationship = requestParams.get("secondary_relationship");
        String secondaryMobileNumber = requestParams.get("secondary_mobile_number");
        RelationshipType relationshipType = adminService.getRelationshipTypeByName(secondaryRelationship);
        Contact secondaryContact = new Contact();
        secondaryContact.setContacterName(secondaryContactName);
        secondaryContact.setMobileNumber(secondaryMobileNumber);
        secondaryContact.setRelationshipType(relationshipType);
        secondaryContact.setPrimary(Boolean.valueOf(false));
        return secondaryContact;
    }

}
