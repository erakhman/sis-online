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

import com.beesinergi.exception.SystemException;
import com.beesinergi.mapper.PoMapper;
import com.beesinergi.mapper.PoOutDetailMapper;
import com.beesinergi.mapper.PoOutMapper;
import com.beesinergi.model.Po;
import com.beesinergi.model.PoOut;
import com.beesinergi.model.PoOutDetail;
import com.beesinergi.util.DateTimeFunction;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;
import com.beesinergi.util.SystemParameter;

@Service("poOutService")
public class PoOutService implements CommonService<PoOut> {
	
	@Autowired
	private PoOutMapper poOutMapper;
	@Autowired
	private PoOutDetailMapper poOutDetailMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private AppParameterService appParameterService;
	@Autowired
	private PoMapper poMapper;

	@Override
	public PoOut findById(int id) {
		PoOut po = poOutMapper.selectByPrimaryKey(id);
		return po;
	}

	@Override
	public Paging findAllByPaging(Paging paging, PoOut param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.PoOutMapper.selectAll";
		List<PoOut> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(PoOut object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkPoOut() == null){
				object.setPoOutDate(new Date());
				object.setCreatedDate(new Date());
				object.setStatus(SystemConstant.PoOutStatus.NEW);
				object.setPoOutNo(generateTrxNo(object.getPoOutDate()));
				poOutMapper.insert(object);
				
//				updatePoStatus(object.getFkPo());
				appParameterService.incrementTrxNo(SystemParameter.RUNNING_NUMBER_PO_OUT_NO, "RUNNING_NUMBER_PO_OUT_NO");
			} else{
				object.setChangedDate(new Date());
				poOutMapper.updateByPrimaryKey(object);
				
				deletePoOutDetail(object);
			}
			savePoOutDetail(object);
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);
	}
	
	public void updatePoStatus(Integer pkPo) {
		Po po = poMapper.selectByPrimaryKey(pkPo);
		po.setStatus(SystemConstant.PoStatus.PO_OUT);
		poMapper.updateByPrimaryKey(po);
	}
	
	public String generateTrxNo(Date tanggal) {
		Integer nextNumber = SystemParameter.RUNNING_NUMBER_PO_OUT_NO + 1;			
		String nextNumberStr = StringUtils.leftPad(nextNumber.toString(), 4, "0");
		return "PT-"+DateTimeFunction.date2String(tanggal, "ddMMyy")+"-"+nextNumberStr;
	}
	
	public void savePoOutDetail(PoOut object) throws SystemException {
		for(PoOutDetail detail:object.getPoOutDetailList()){
			detail.setFkPoOut(object.getPkPoOut());
			poOutDetailMapper.insert(detail);
		}
	}
	
	public void deletePoOutDetail(PoOut object) {
		poOutDetailMapper.deleteByFkPoOut(object.getPkPoOut());
	}

	@Override
	public List<PoOut> findAll(PoOut param) {
		List<PoOut> list = poOutMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(PoOut param) {
		int count = poOutMapper.selectCount(param);
		return count;
	}
	

}
