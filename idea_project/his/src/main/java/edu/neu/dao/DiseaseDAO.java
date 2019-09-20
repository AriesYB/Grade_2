package edu.neu.dao;

import edu.neu.entity.Disease;
import edu.neu.entity.DiseaseResult;

import java.util.List;

/**
 * ClassName:DiseaseDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/9/2 18:55
 * @Author:HetFrame
 */
public interface DiseaseDAO {
    List<Disease> findAll();
    Disease findByICD(String icd);
    List<DiseaseResult> findByName(String name);
}
