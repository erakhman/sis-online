package com.beesinergi.service;

import java.util.Date;
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

import com.beesinergi.mapper.CoaMapper;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.Coa;
import com.beesinergi.model.Coa;
import com.beesinergi.util.Paging;

@Service("coaService")
public class CoaService implements CommonService<Coa> {

	@Autowired
	private CoaMapper coaMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public Coa findById(int id) {
		Coa param = new Coa();
		param.setPkCoa(id);
		List<Coa> list = coaMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Coa param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.CoaMapper.selectAll";
		List<Coa> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Coa object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkCoa() == null){
				object.setCreatedDate(new Date());
				coaMapper.insert(object);
			} else{
				object.setChangedDate(new Date());
				coaMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<Coa> findAll(Coa param) {
		List<Coa> list = coaMapper.selectAll(param);
		return list;
	}
	
	public List<Coa> findAllParent() {
		List<Coa> list = coaMapper.selectAllParent();
		return list;
	}

	@Override
	public Integer getCount(Coa param) {
		int count = coaMapper.selectCount(param);
		return count;
	}

}
