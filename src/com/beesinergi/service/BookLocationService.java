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

import com.beesinergi.mapper.BookLocationMapper;
import com.beesinergi.model.BookLocation;
import com.beesinergi.util.Paging;

@Service("bookLocationService")
public class BookLocationService implements CommonService<BookLocation> {

	@Autowired
	private BookLocationMapper bookLocationMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public BookLocation findById(int id) {
		BookLocation param = new BookLocation();
		param.setId(id);
		List<BookLocation> list = bookLocationMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, BookLocation param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.BookLocationMapper.selectAll";
		List<BookLocation> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(BookLocation object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getId() == null){
				bookLocationMapper.insert(object);
			} else{
				bookLocationMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<BookLocation> findAll(BookLocation param) {
		List<BookLocation> list = bookLocationMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(BookLocation param) {
		int count = bookLocationMapper.selectCount(param);
		return count;
	}

}
