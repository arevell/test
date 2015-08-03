package com.ttc.ch2.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.FunctionDAO;
import com.ttc.ch2.domain.Function;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class FunctionDAOImpl extends BaseDao<Function, Long> implements FunctionDAO {

}
