/**
 * 
 */
package com.ipcs.service;

import com.ipcs.model.*;

import java.util.List;

/**
 * @author Chen Chao
 *
 */

public interface SchoolService {
	School getSchoolByName(String name);

	List<School> getSchoolByType(String type);

}
