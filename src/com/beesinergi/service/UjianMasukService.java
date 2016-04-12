package com.beesinergi.service;

import java.util.ArrayList;
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

import com.beesinergi.exception.ErrorHolder;
import com.beesinergi.exception.SystemException;
import com.beesinergi.mapper.UjianMasukDetailMapper;
import com.beesinergi.mapper.UjianMasukMapper;
import com.beesinergi.mapper.SiswaMapper;
import com.beesinergi.model.UjianMasuk;
import com.beesinergi.model.UjianMasukDetail;
import com.beesinergi.model.Po;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemParameter;

@Service("ujianMasukService")
public class UjianMasukService implements CommonService<UjianMasuk> {
	
	@Autowired
	private UjianMasukMapper ujianMasukMapper;
	@Autowired
	private UjianMasukDetailMapper ujianMasukDetailMapper;
	@Autowired
	private SiswaMapper siswaMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;

	@Override
	public UjianMasuk findById(int id) {
		UjianMasuk param = new UjianMasuk();
		param.setPkUjianMasuk(id);
		List<UjianMasuk> list = ujianMasukMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, UjianMasuk param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.UjianMasukMapper.selectAll";
//		if (param != null){
//			if (param.getUjianMasukName() != null){
//				param.setUjianMasukName("%"+param.getUjianMasukName().toLowerCase()+"%");
//			}
//		}
		List<UjianMasuk> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(UjianMasuk object) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			object.setCreatedDate(new Date());
			ujianMasukMapper.insert(object);
			saveUjianMasukDetail(object);
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);		
	}
	
	public void saveUjianMasukDetail(UjianMasuk object) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<ErrorHolder>();
		for(UjianMasukDetail detail:object.getUjianMasukDetailList()){
			detail.setFkUjianMasuk(object.getPkUjianMasuk());
			ujianMasukDetailMapper.insert(detail);
		}
		if (!errors.isEmpty()){
			throw new SystemException(errors);
		}
	}

	@Override
	public List<UjianMasuk> findAll(UjianMasuk param) {
//		if (param != null){
//			if (param.getUjianMasukName() != null){
//				param.setUjianMasukName("%"+param.getUjianMasukName().toLowerCase()+"%");
//			}
//		}
		List<UjianMasuk> list = ujianMasukMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(UjianMasuk param) {
		int count = ujianMasukMapper.selectCount(param);
		return count;
	}
	

}
