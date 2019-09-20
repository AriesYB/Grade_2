package edu.neu.dao;

import edu.neu.entity.Department;

import java.util.List;

/**
 * ClassName:DepartmentDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/8/28 11:53
 * @Author:HetFrame
 */
public interface DepartmentDAO {
    List<Department> findAll();
    Department findById(int id);
    Department findByCode(String code);
    int findIdByCode(String code);
}
