package edu.neu.dao;

import edu.neu.entity.ChargesDetails;

/**
 * ClassName:ChargesDetailDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/9/7 18:10
 * @Author:HetFrame
 */
public interface ChargesDetailDAO {
    ChargesDetails findById(int id);
    void insertChargesDetail(ChargesDetails chargesDetails);
}
