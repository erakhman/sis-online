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

import com.beesinergi.mapper.JadwalUjianMapper;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.JadwalUjian;
import com.beesinergi.model.JadwalUjian;
import com.beesinergi.util.Paging;

@Service("jadwalUjianService")
public class JadwalUjianService implements CommonService<JadwalUjian> {

	@Autowired
	private JadwalUjianMapper jadwalUjianMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public JadwalUjian findById(int id) {
		JadwalUjian param = new JadwalUjian();
		param.setPkJadwalUjian(id);
		List<JadwalUjian> list = jadwalUjianMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, JadwalUjian param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.JadwalUjianMapper.selectAll";
		List<JadwalUjian> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(JadwalUjian object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkJadwalUjian() == null){
				object.setCreatedDate(new Date());
				jadwalUjianMapper.insert(object);
			} else{
				object.setChangedDate(new Date());
				jadwalUjianMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<JadwalUjian> findAll(JadwalUjian param) {
		return null;
	}

	@Override
	public Integer getCount(JadwalUjian param) {
		int count = jadwalUjianMapper.selectCount(param);
		return count;
	}

}
