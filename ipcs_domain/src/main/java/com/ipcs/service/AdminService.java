/**
 * 
 */
package com.ipcs.service;

import java.util.List;

/**
 * @author Chen Chao
 *
 */
public interface AdminService<T> {
	
	public List<T> listAllChild(String adminName);
	
	public List<T> listAllTeachers(String adminName);
	
	public boolean updateChild(T person);
	
	public boolean updateTeacher(T teacher);
	
	public boolean broadcaseMessageTo(List<T> subodidates);
	
//	pulbic List
	
	

}
