package edu.neu.dao;

import edu.neu.entity.RegisterClass;

/**
 * ClassName:RegisterClassDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/8/31 19:39
 * @Author:HetFrame
 */
public interface RegisterClassDAO {
    RegisterClass findById(int id);
}
