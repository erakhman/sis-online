package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.beesinergi.mapper.DeliveryDetailMapper;
import com.beesinergi.mapper.DeliveryMapper;
import com.beesinergi.mapper.PoDetailMapper;
import com.beesinergi.mapper.PoMapper;
import com.beesinergi.mapper.StockMapper;
import com.beesinergi.model.Delivery;
import com.beesinergi.model.DeliveryDetail;
import com.beesinergi.model.Po;
import com.beesinergi.model.PoDetail;
import com.beesinergi.model.Product;
import com.beesinergi.util.DateTimeFunction;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;
import com.beesinergi.util.SystemParameter;

@Service("deliveryService")
public class DeliveryService implements CommonService<Delivery> {
	
	@Autowired
	private DeliveryMapper deliveryMapper;
	@Autowired
	private DeliveryDetailMapper deliveryDetailMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private ProductService productService;
	@Autowired
	private AppParameterService appParameterService;
	@Autowired
	private PoDetailMapper poDetailMapper;
	@Autowired
	private PoMapper poMapper;
	@Autowired
	private StockMapper stockMapper;

	@Override
	public Delivery findById(int id) {
		Delivery po = deliveryMapper.selectByPrimaryKey(id);
		for(DeliveryDetail detail:po.getDeliveryDetailList()){
			Product product = productService.findById(detail.getFkProduct());
			int stock = product.getQty() + detail.getQty();
			detail.setProductStock(stock);
			detail.setProductUnit(product.getUnitName());
		}
		return po;
	}

	@Override
	public Paging findAllByPaging(Paging paging, Delivery param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.DeliveryMapper.selectAll";
		if (param != null){
			if (param.getDeliveryNo() != null){
				param.setDeliveryNo("%"+param.getDeliveryNo().toLowerCase()+"%");
			}
			if (param.getPoNo() != null){
				param.setPoNo("%"+param.getPoNo().toLowerCase()+"%");
			}
		}
		List<Delivery> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Delivery object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkDelivery() == null){
				deliveryMapper.makeNonEditable(object.getFkPo());
				
				object.setCreatedDate(new Date());
				object.setDeliveryNo(generateTrxNo(object.getDeliveryDate()));
				deliveryMapper.insert(object);
				
				appParameterService.incrementTrxNo(SystemParameter.RUNNING_NUMBER_DELIVERY_NO, "RUNNING_NUMBER_DELIVERY_NO");
			} else{
				object.setChangedDate(new Date());
				deliveryMapper.updateByPrimaryKey(object);
				
				deliveryDetailMapper.deleteByFkDelivery(object.getPkDelivery());
				updateProductQty(object);
				stockMapper.deleteByTrxNo(object.getDeliveryNo());
			}
			saveDeliveryDetail(object);
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);
	}
	
	public void updateProductQty(Delivery object) {
		for (DeliveryDetail detail:object.getDeliveryDetailList()){
			Product product = productService.findById(detail.getFkProduct());
			product.setQty(detail.getProductStock());
			productService.save(product);
		}
	}
	
	public String generateTrxNo(Date tanggal) {
		Integer nextNumber = SystemParameter.RUNNING_NUMBER_DELIVERY_NO + 1;			
		String nextNumberStr = StringUtils.leftPad(nextNumber.toString(), 4, "0");
		return "PD-"+DateTimeFunction.date2String(tanggal, "ddMMyy")+"-"+nextNumberStr;
	}
	
	public void saveDeliveryDetail(Delivery object) throws Exception {
		int outstanding = 0;
		for(DeliveryDetail detail:object.getDeliveryDetailList()){
			detail.setFkDelivery(object.getPkDelivery());
			deliveryDetailMapper.insert(detail);
			
			int qty = detail.getQty()*-1;
			productService.adjustmentQty(detail.getFkProduct(), qty, object.getDeliveryDate(), object.getDeliveryNo());
			
			PoDetail poDetail = new PoDetail();
			poDetail.setFkPo(object.getFkPo());
			poDetail.setFkProduct(detail.getFkProduct());
			List<PoDetail> poDetails = poDetailMapper.selectAll(poDetail);
			if (!poDetails.isEmpty()){
				poDetail = poDetails.get(0);
				outstanding = detail.getOutstanding() - detail.getQty();
				poDetail.setOutstanding(outstanding);
				poDetailMapper.updateByPrimaryKey(poDetail);
			}
		}
		updatePoStatus(outstanding,object.getFkPo());
	}
	
	public void updatePoStatus(int outstanding, int fkPo) {
		Po po = poMapper.selectByPrimaryKey(fkPo);
		if (outstanding > 0){
			po.setStatus(SystemConstant.PoStatus.OUTSTANDING);
		} else{
			po.setStatus(SystemConstant.PoStatus.DELIVERED);
		}
		poMapper.updateByPrimaryKey(po);
	}

	@Override
	public List<Delivery> findAll(Delivery param) {
		List<Delivery> list = deliveryMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Delivery param) {
		if (param != null){
			if (param.getDeliveryNo() != null){
				param.setDeliveryNo("%"+param.getDeliveryNo().toLowerCase()+"%");
			}
			if (param.getPoNo() != null){
				param.setPoNo("%"+param.getPoNo().toLowerCase()+"%");
			}
		}
		int count = deliveryMapper.selectCount(param);
		return count;
	}
	

}
