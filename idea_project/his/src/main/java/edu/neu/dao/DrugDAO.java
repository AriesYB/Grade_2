package edu.neu.dao;

import edu.neu.entity.Drug;

import java.util.List;

/**
 * ClassName:DrugDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/9/4 22:43
 * @Author:HetFrame
 */
public interface DrugDAO {
    Drug findById(int id);
    List<Drug> findByName(String name);
}
