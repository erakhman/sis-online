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

import com.beesinergi.mapper.PelajaranUjianMapper;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.PelajaranUjian;
import com.beesinergi.model.PelajaranUjian;
import com.beesinergi.util.Paging;

@Service("pelajaranUjianService")
public class PelajaranUjianService implements CommonService<PelajaranUjian> {

	@Autowired
	private PelajaranUjianMapper pelajaranUjianMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public PelajaranUjian findById(int id) {
		PelajaranUjian param = new PelajaranUjian();
		param.setPkPelajaranUjian(id);
		List<PelajaranUjian> list = pelajaranUjianMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, PelajaranUjian param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.PelajaranUjianMapper.selectAll";
		List<PelajaranUjian> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(PelajaranUjian object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkPelajaranUjian() == null){
				object.setCreatedDate(new Date());
				pelajaranUjianMapper.insert(object);
			} else{
				object.setChangedDate(new Date());
				pelajaranUjianMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<PelajaranUjian> findAll(PelajaranUjian param) {
		List<PelajaranUjian> list = pelajaranUjianMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(PelajaranUjian param) {
		int count = pelajaranUjianMapper.selectCount(param);
		return count;
	}

}
