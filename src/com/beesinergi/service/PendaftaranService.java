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
import com.beesinergi.mapper.PendaftaranDetailMapper;
import com.beesinergi.mapper.PendaftaranMapper;
import com.beesinergi.mapper.SiswaMapper;
import com.beesinergi.model.Pendaftaran;
import com.beesinergi.model.PendaftaranDetail;
import com.beesinergi.model.Po;
import com.beesinergi.util.Paging;

@Service("pendaftaranService")
public class PendaftaranService implements CommonService<Pendaftaran> {
	
	@Autowired
	private PendaftaranMapper pendaftaranMapper;
	@Autowired
	private PendaftaranDetailMapper pendaftaranDetailMapper;
	@Autowired
	private SiswaMapper siswaMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;

	@Override
	public Pendaftaran findById(int id) {
		Pendaftaran param = new Pendaftaran();
		param.setPkPendaftaran(id);
		List<Pendaftaran> list = pendaftaranMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Pendaftaran param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.PendaftaranMapper.selectAll";
//		if (param != null){
//			if (param.getPendaftaranName() != null){
//				param.setPendaftaranName("%"+param.getPendaftaranName().toLowerCase()+"%");
//			}
//		}
		List<Pendaftaran> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Pendaftaran object) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkPendaftaran() == null){
				object.getSiswa().setCreatedDate(new Date());
				siswaMapper.insert(object.getSiswa());
				
				object.setCreatedDate(new Date());
				object.setFkSiswa(object.getSiswa().getPkSiswa());
				pendaftaranMapper.insert(object);
			} else{
				object.getSiswa().setChangedDate(new Date());
				siswaMapper.updateByPrimaryKey(object.getSiswa());
				
				object.setChangedDate(new Date());
				pendaftaranMapper.updateByPrimaryKey(object);
				
				deletePendaftaranDetail(object);
			}
			savePendaftaranDetail(object);
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);		
	}
	
	public void deletePendaftaranDetail(Pendaftaran object) {
		pendaftaranDetailMapper.deleteByFkPendaftaran(object.getPkPendaftaran());
	}
	
	public void savePendaftaranDetail(Pendaftaran object) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<ErrorHolder>();
		for(PendaftaranDetail detail:object.getPendaftaranDetailList()){
			detail.setFkPendaftaran(object.getPkPendaftaran());
			pendaftaranDetailMapper.insert(detail);
		}
		if (!errors.isEmpty()){
			throw new SystemException(errors);
		}
	}

	@Override
	public List<Pendaftaran> findAll(Pendaftaran param) {
//		if (param != null){
//			if (param.getPendaftaranName() != null){
//				param.setPendaftaranName("%"+param.getPendaftaranName().toLowerCase()+"%");
//			}
//		}
		List<Pendaftaran> list = pendaftaranMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Pendaftaran param) {
		int count = pendaftaranMapper.selectCount(param);
		return count;
	}
	

}
