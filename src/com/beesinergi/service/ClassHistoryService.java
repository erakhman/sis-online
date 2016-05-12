package com.beesinergi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.beesinergi.mapper.ClassHistoryMapper;
import com.beesinergi.model.ClassHistory;
import com.beesinergi.util.Paging;

@Service("classHistoryService")
public class ClassHistoryService implements CommonService<ClassHistory> {

	@Autowired
	private ClassHistoryMapper classHistoryMapper;
	@Autowired
	private TahunAjaranService tahunAjaranService;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public ClassHistory findById(int id) {
		return null;
	}

	@Override
	public Paging findAllByPaging(Paging paging, ClassHistory param) {
		return null;
	}

	@Override
	public void save(ClassHistory object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkClassHistory() == null){
				classHistoryMapper.insert(object);
			} else{
				classHistoryMapper.updateByPrimaryKey(object);
			}
			
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<ClassHistory> findAll(ClassHistory param) {
		List<ClassHistory> list = classHistoryMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(ClassHistory param) {
		return null;
	}
	
	public void deleteByPrimaryKey(int pkClassHistory) {
		classHistoryMapper.deleteByPrimaryKey(pkClassHistory);
	}

}
