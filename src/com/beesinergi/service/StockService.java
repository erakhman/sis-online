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

import com.beesinergi.mapper.StockMapper;
import com.beesinergi.model.Product;
import com.beesinergi.model.Stock;
import com.beesinergi.util.Paging;

@Service("stockService")
public class StockService implements CommonService<Stock>{
	
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private ProductService productService;

	@Override
	public Stock findById(int id) {
		return null;
	}

	@Override
	public Paging findAllByPaging(Paging paging, Stock param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.StockMapper.selectAll";
		List<Stock> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Stock object) throws Exception {
		object.setCreatedDate(new Date());
		stockMapper.insert(object);
	}

	@Override
	public Integer getCount(Stock param) {
		int count = stockMapper.selectCount(param);
		return count;
	}

	@Override
	public List<Stock> findAll(Stock param) {
		List<Stock> list = stockMapper.selectAll(param);
		return list;
	}

}
