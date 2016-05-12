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

import com.beesinergi.mapper.ReturnAndBorrowBookMapper;
import com.beesinergi.model.ReturnAndBorrowBook;
import com.beesinergi.util.Paging;

@Service("returnAndBorrowBookService")
public class ReturnAndBorrowBookService implements CommonService<ReturnAndBorrowBook> {

	@Autowired
	private ReturnAndBorrowBookMapper returnAndBorrowBookMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public ReturnAndBorrowBook findById(int id) {
		ReturnAndBorrowBook param = new ReturnAndBorrowBook();
		param.setId(id);
		List<ReturnAndBorrowBook> list = returnAndBorrowBookMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, ReturnAndBorrowBook param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.ReturnAndBorrowBookMapper.selectAll";
		List<ReturnAndBorrowBook> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(ReturnAndBorrowBook object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getId() == null){
				returnAndBorrowBookMapper.insert(object);
			} else{
				returnAndBorrowBookMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<ReturnAndBorrowBook> findAll(ReturnAndBorrowBook param) {
		List<ReturnAndBorrowBook> list = returnAndBorrowBookMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(ReturnAndBorrowBook param) {
		int count = returnAndBorrowBookMapper.selectCount(param);
		return count;
	}

}
