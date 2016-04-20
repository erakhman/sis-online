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

import com.beesinergi.mapper.TahunAjaranMapper;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.TahunAjaran;
import com.beesinergi.model.TahunAjaran;
import com.beesinergi.util.Paging;

@Service("tahunAjaranService")
public class TahunAjaranService implements CommonService<TahunAjaran> {

	@Autowired
	private TahunAjaranMapper tahunAjaranMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public TahunAjaran findById(int id) {
		TahunAjaran param = new TahunAjaran();
		param.setPkTahunAjaran(id);
		List<TahunAjaran> list = tahunAjaranMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, TahunAjaran param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.TahunAjaranMapper.selectAll";
		List<TahunAjaran> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(TahunAjaran object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkTahunAjaran() == null){
				object.setCreatedDate(new Date());
				tahunAjaranMapper.insert(object);
			} else{
				object.setChangedDate(new Date());
				tahunAjaranMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<TahunAjaran> findAll(TahunAjaran param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(TahunAjaran param) {
		int count = tahunAjaranMapper.selectCount(param);
		return count;
	}

}
