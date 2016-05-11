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

import com.beesinergi.mapper.BookMapper;

import com.beesinergi.model.Book;
import com.beesinergi.util.Paging;

@Service("bookService")
public class BookService implements CommonService<Book> {

	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public Book findById(int id) {
		Book param = new Book();
		param.setPkBook(id);
		List<Book> list = bookMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Book param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.BookMapper.selectAll";
		List<Book> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Book object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkBook() == null){
				object.setCreatedDate(new Date());
				bookMapper.insert(object);
			} else{
				object.setChangedDate(new Date());
				bookMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<Book> findAll(Book param) {
		List<Book> list = bookMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Book param) {
		int count = bookMapper.selectCount(param);
		return count;
	}

}
