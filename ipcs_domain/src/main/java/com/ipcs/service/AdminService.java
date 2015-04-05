/**
 * 
 */
package com.ipcs.service;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Chen Chao
 *
 */

public interface AdminService<T> {
	public void addBatchSubodinates(List<T> subodidates);
	
	public void deleteBatchSubodinates(List<T> subodinates);
	
	public void addAdmin(T admin);
	
	public List<T> listAllStudents(String schoolName);
	
	public List<T> listAllTeachers(String schoolName);
	
	public void updateChild(T person);
	
	public void updateTeacher(T teacher);
	
	public boolean broadcaseMessageTo(List<T> subodidates);
	
	
	

}
