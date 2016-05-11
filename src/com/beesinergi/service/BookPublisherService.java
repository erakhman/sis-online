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

import com.beesinergi.mapper.BookPublisherMapper;

import com.beesinergi.model.BookPublisher;
import com.beesinergi.util.Paging;

@Service("bookPublisherService")
public class BookPublisherService implements CommonService<BookPublisher> {

	@Autowired
	private BookPublisherMapper bookPublisherMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public BookPublisher findById(int id) {
		BookPublisher param = new BookPublisher();
		param.setPkBookPublisher(id);
		List<BookPublisher> list = bookPublisherMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, BookPublisher param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.BookPublisherMapper.selectAll";
		List<BookPublisher> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(BookPublisher object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkBookPublisher() == null){
				bookPublisherMapper.insert(object);
			} else{
				bookPublisherMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<BookPublisher> findAll(BookPublisher param) {
		List<BookPublisher> list = bookPublisherMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(BookPublisher param) {
		int count = bookPublisherMapper.selectCount(param);
		return count;
	}

}
