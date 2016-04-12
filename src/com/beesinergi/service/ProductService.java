package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.ProductMapper;
import com.beesinergi.model.Product;
import com.beesinergi.model.Stock;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("productService")
public class ProductService implements CommonService<Product> {
	
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private StockService stockService;

	@Override
	public Product findById(int id) {
		Product param = new Product();
		param.setPkProduct(id);
		List<Product> list = productMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Product param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.ProductMapper.selectAll";
		if (param != null){
			if (param.getProductName() != null){
				param.setProductName("%"+param.getProductName().toLowerCase()+"%");
			}
		}
		List<Product> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}
	
	public void adjustmentQty(int pkProduct, int qty, Date trxDate, String trxNo) throws Exception {
		Product product = findById(pkProduct);
		int finalQty = product.getQty() + qty; 
		
		Stock stock = new Stock();
		stock.setFkProduct(pkProduct);
		stock.setTrxDate(trxDate);
		stock.setTrxNo(trxNo);
		stock.setTrxQty(qty);
		stock.setStartStock(product.getQty());
		stock.setEndStock(finalQty);
		stockService.save(stock);
		
		product.setQty(finalQty);
		save(product);
	}

	@Override
	public void save(Product object) {
		if (object.getPkProduct() == null){
			object.setCreatedDate(new Date());
			productMapper.insert(object);
		} else{
			if (object.getIsActive() == null){
				object.setIsActive(SystemConstant.NO);
			}
			object.setChangedDate(new Date());
			productMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public List<Product> findAll(Product param) {
		if (param != null){
			if (param.getProductName() != null){
				param.setProductName("%"+param.getProductName().toLowerCase()+"%");
			}
		}
		List<Product> list = productMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Product param) {
		if (param.getIsActive() == null){
			param.setIsActive(SystemConstant.YES);
		}
		int count = productMapper.selectCount(param);
		return count;
	}
	

}
