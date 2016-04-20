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

import com.beesinergi.mapper.SiswaMapper;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.Siswa;
import com.beesinergi.model.Siswa;
import com.beesinergi.util.Paging;

@Service("siswaService")
public class SiswaService implements CommonService<Siswa> {

	@Autowired
	private SiswaMapper siswaMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public Siswa findById(int id) {
		Siswa param = new Siswa();
		param.setPkSiswa(id);
		List<Siswa> list = siswaMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Siswa param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.SiswaMapper.selectAll";
		List<Siswa> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Siswa object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkSiswa() == null){
				object.setCreatedDate(new Date());
				siswaMapper.insert(object);
			} else{
				object.setChangedDate(new Date());
				siswaMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<Siswa> findAll(Siswa param) {
		return null;
	}

	@Override
	public Integer getCount(Siswa param) {
		int count = siswaMapper.selectCount(param);
		return count;
	}

}
