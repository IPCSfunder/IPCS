/**
 *
 */
package com.ipcs.service.impl;

import com.ipcs.dao.*;
import com.ipcs.model.*;
import com.ipcs.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chen Chao
 */

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    @Transactional(readOnly=true)
    public School getSchoolByName(String name) {
        return schoolDao.findSchoolByName(name);
    }

    @Transactional(readOnly=true)
    public List<School> getSchoolByType(String typeName) {
        return schoolDao.listSchoolsByType(typeName);
    }

}
