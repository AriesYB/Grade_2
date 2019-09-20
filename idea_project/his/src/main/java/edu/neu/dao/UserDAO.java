package edu.neu.dao;

import edu.neu.entity.User;

import java.util.List;

/**
 * ClassName:DoctorDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/8/28 12:12
 * @Author:HetFrame
 */
public interface UserDAO {
    List<User> findAll();
    List<User> findByDepartment(int id);
    User findById(int id);
    User findByUsername(String username);
}
