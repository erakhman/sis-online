package com.beesinergi.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.beesinergi.mapper.PenaltyTypeMapper;
import com.beesinergi.model.PenaltyType;
import com.beesinergi.util.Paging;

@Service("penaltyTypeService")
public class PenaltyTypeService implements CommonService<PenaltyType> {

	@Autowired
	private PenaltyTypeMapper penaltyTypeMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public PenaltyType findById(int id) {
		PenaltyType param = new PenaltyType();
		param.setId(id);
		List<PenaltyType> list = penaltyTypeMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, PenaltyType param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.PenaltyTypeMapper.selectAll";
		List<PenaltyType> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(PenaltyType object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getId() == null){
				penaltyTypeMapper.insert(object);
			} else{
				penaltyTypeMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<PenaltyType> findAll(PenaltyType param) {
		List<PenaltyType> list = penaltyTypeMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(PenaltyType param) {
		int count = penaltyTypeMapper.selectCount(param);
		return count;
	}

}
