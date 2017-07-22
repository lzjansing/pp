package com.ms.jansing.pp.service;

import com.ms.jansing.pp.dao.FundDao;
import com.ms.jansing.pp.entity.Fund;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jansing on 17-7-22.
 */
@Service
@Transactional(readOnly = true)
public class FundService {

    @Resource
    private FundDao fundDao;

    public Fund get(String id) {
        return fundDao.get(id);
    }

    public List<Fund> findList() {
        return fundDao.findList();
    }

    @Transactional(readOnly = false)
    public void save(Fund fund) {
        if (fund.getId() == null) {
            fund.preInsert();
            fundDao.insert(fund);
        } else {
            fundDao.update(fund);
        }
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        fundDao.delete(id);
    }
}
