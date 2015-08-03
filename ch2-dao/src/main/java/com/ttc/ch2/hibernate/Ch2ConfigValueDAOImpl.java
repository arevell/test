package com.ttc.ch2.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.Ch2ConfigValueDAO;
import com.ttc.ch2.domain.Ch2ConfigValue;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class Ch2ConfigValueDAOImpl extends BaseDao<Ch2ConfigValue,  Long> implements Ch2ConfigValueDAO {


}
