package com.beesinergi.service;

import java.util.ArrayList;
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

import com.beesinergi.exception.ErrorHolder;
import com.beesinergi.exception.SystemException;
import com.beesinergi.mapper.AppParameterMapper;
import com.beesinergi.mapper.PoDetailMapper;
import com.beesinergi.mapper.PoMapper;
import com.beesinergi.model.AppParameter;
import com.beesinergi.model.Po;
import com.beesinergi.model.PoDetail;
import com.beesinergi.util.DateTimeFunction;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;
import com.beesinergi.util.SystemParameter;

@Service("poService")
public class PoService implements CommonService<Po> {
	
	@Autowired
	private PoMapper poMapper;
	@Autowired
	private PoDetailMapper poDetailMapper;
	@Autowired
	private AppParameterMapper appParameterMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private AppParameterService appParameterService;
	

	@Override
	public Po findById(int id) {
		Po po = poMapper.selectByPrimaryKey(id);
		return po;
	}

	@Override
	public Paging findAllByPaging(Paging paging, Po param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.PoMapper.selectAll";
		if (param != null){
			if (param.getPoNo() != null){
				param.setPoNo("%"+param.getPoNo().toLowerCase()+"%");
			}
		}
		List<Po> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Po object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkPo() == null){
				object.setPoDate(new Date());
				object.setCreatedDate(new Date());
				object.setStatus(SystemConstant.PoStatus.NEED_APPROVAL);
				object.setPoNo(generateTrxNo(object.getPoDate()));
				poMapper.insert(object);
				
				appParameterService.incrementTrxNo(SystemParameter.RUNNING_NUMBER_PO_NO, "RUNNING_NUMBER_PO_NO");
			} else{
				object.setChangedDate(new Date());
				poMapper.updateByPrimaryKey(object);
				
				deletePoDetail(object);
			}
			savePoDetail(object);
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);
	}
	
	public String generateTrxNo(Date tanggal) {
		Integer nextNumber = SystemParameter.RUNNING_NUMBER_PO_NO + 1;			
		String nextNumberStr = StringUtils.leftPad(nextNumber.toString(), 4, "0");
		return "PR-"+DateTimeFunction.date2String(tanggal, "ddMMyy")+"-"+nextNumberStr;
	}
	
	public void savePoDetail(Po object) throws SystemException {
		for(PoDetail detail:object.getPoDetailList()){
			detail.setFkPo(object.getPkPo());
			poDetailMapper.insert(detail);
		}
	}
	
	public void deletePoDetail(Po object) {
		poDetailMapper.deleteByFkPo(object.getPkPo());
	}
	
	

	@Override
	public List<Po> findAll(Po param) {
		List<Po> list = poMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Po param) {
		if (param != null){
			if (param.getPoNo() != null){
				param.setPoNo("%"+param.getPoNo().toLowerCase()+"%");
			}
		}
		int count = poMapper.selectCount(param);
		return count;
	}
	

}
