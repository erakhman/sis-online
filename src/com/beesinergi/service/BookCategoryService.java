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

import com.beesinergi.mapper.BookCategoryMapper;
import com.beesinergi.model.BookCategory;
import com.beesinergi.util.Paging;

@Service("bookCategoryService")
public class BookCategoryService implements CommonService<BookCategory> {

	@Autowired
	private BookCategoryMapper bookCategoryMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public BookCategory findById(int id) {
		BookCategory param = new BookCategory();
		param.setPkBookCategory(id);
		List<BookCategory> list = bookCategoryMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, BookCategory param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.BookCategoryMapper.selectAll";
		List<BookCategory> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(BookCategory object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkBookCategory() == null){
				bookCategoryMapper.insert(object);
			} else{				
				bookCategoryMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<BookCategory> findAll(BookCategory param) {
		List<BookCategory> list = bookCategoryMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(BookCategory param) {
		int count = bookCategoryMapper.selectCount(param);
		return count;
	}

}
