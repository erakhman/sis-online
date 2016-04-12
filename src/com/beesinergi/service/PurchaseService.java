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

import com.beesinergi.mapper.PoOutMapper;
import com.beesinergi.mapper.PurchaseDetailMapper;
import com.beesinergi.mapper.PurchaseMapper;
import com.beesinergi.model.PoOut;
import com.beesinergi.model.Purchase;
import com.beesinergi.model.PurchaseDetail;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("purchaseService")
public class PurchaseService implements CommonService<Purchase> {
	
	@Autowired
	private PurchaseMapper purchaseMapper;
	@Autowired
	private PurchaseDetailMapper purchaseDetailMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private AppParameterService appParameterService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PoOutMapper poOutMapper;

	@Override
	public Purchase findById(int id) {
		Purchase po = purchaseMapper.selectByPrimaryKey(id);
		return po;
	}

	@Override
	public Paging findAllByPaging(Paging paging, Purchase param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.PurchaseMapper.selectAll";
		if (param != null){
			if (param.getPurchaseNo() != null){
				param.setPurchaseNo("%"+param.getPurchaseNo().toLowerCase()+"%");
			}
			if (param.getPoOutNo() != null){
				param.setPoOutNo("%"+param.getPoOutNo().toLowerCase()+"%");
			}
		}
		List<Purchase> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Purchase object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkPurchase() == null){
				object.setCreatedDate(new Date());
				purchaseMapper.insert(object);
				
				PoOut poOut = poOutMapper.selectByPrimaryKey(object.getFkPoOut());
				poOut.setStatus(SystemConstant.PoOutStatus.PURCHASED);
				poOutMapper.updateByPrimaryKey(poOut);
			} else{
				object.setChangedDate(new Date());
				purchaseMapper.updateByPrimaryKey(object);
				
				deletePurchaseDetail(object);
			}
			savePurchaseDetail(object);
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);
	}
	
	public void savePurchaseDetail(Purchase object) throws Exception {
		for(PurchaseDetail detail:object.getPurchaseDetailList()){
			detail.setFkPurchase(object.getPkPurchase());
			Double total = detail.getPrice() * detail.getQty();
			detail.setTotal(total);
			purchaseDetailMapper.insert(detail);
			
			productService.adjustmentQty(detail.getFkProduct(), detail.getQty(), object.getPurchaseDate(), object.getPurchaseNo());
		}
	}
	
	public void deletePurchaseDetail(Purchase object) {
		purchaseDetailMapper.deleteByFkPurchase(object.getPkPurchase());
	}

	@Override
	public List<Purchase> findAll(Purchase param) {
		List<Purchase> list = purchaseMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Purchase param) {
		if (param != null){
			if (param.getPurchaseNo() != null){
				param.setPurchaseNo("%"+param.getPurchaseNo().toLowerCase()+"%");
			}
			if (param.getPoOutNo() != null){
				param.setPoOutNo("%"+param.getPoOutNo().toLowerCase()+"%");
			}
		}
		int count = purchaseMapper.selectCount(param);
		return count;
	}
	

}
