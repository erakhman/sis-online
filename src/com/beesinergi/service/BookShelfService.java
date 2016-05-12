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

import com.beesinergi.mapper.BookShelfMapper;
import com.beesinergi.model.BookShelf;
import com.beesinergi.util.Paging;

@Service("bookShelfService")
public class BookShelfService implements CommonService<BookShelf> {

	@Autowired
	private BookShelfMapper bookShelfMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public BookShelf findById(int id) {
		BookShelf param = new BookShelf();
		param.setId(id);
		List<BookShelf> list = bookShelfMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, BookShelf param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.BookShelfMapper.selectAll";
		List<BookShelf> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(BookShelf object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getId() == null){
				bookShelfMapper.insert(object);
			} else{
				bookShelfMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<BookShelf> findAll(BookShelf param) {
		List<BookShelf> list = bookShelfMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(BookShelf param) {
		int count = bookShelfMapper.selectCount(param);
		return count;
	}

}
