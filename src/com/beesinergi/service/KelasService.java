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

import com.beesinergi.mapper.KelasMapper;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.Kelas;
import com.beesinergi.model.Kelas;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.util.Paging;

@Service("kelasService")
public class KelasService implements CommonService<Kelas> {

	@Autowired
	private KelasMapper kelasMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public Kelas findById(int id) {
		Kelas param = new Kelas();
		param.setPkKelas(id);
		List<Kelas> list = kelasMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Kelas param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.KelasMapper.selectAll";
		List<Kelas> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Kelas object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkKelas() == null){
				object.setCreatedDate(new Date());
				kelasMapper.insert(object);
			} else{
				object.setChangedDate(new Date());
				kelasMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<Kelas> findAll(Kelas param) {
		List<Kelas> list = kelasMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Kelas param) {
		int count = kelasMapper.selectCount(param);
		return count;
	}

}
