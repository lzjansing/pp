package com.ms.jansing.pp.dao;

import com.ms.jansing.common.dao.MyBatisDao;
import com.ms.jansing.pp.entity.Fund;

import java.util.List;

/**
 * Created by jansing on 17-7-22.
 */
@MyBatisDao
public interface FundDao {

    Fund get(String id);

    List<Fund> findList();

    int insert(Fund fund);

    int update(Fund fund);

    int delete(String id);
}
