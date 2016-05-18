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

import com.beesinergi.mapper.StaffMapper;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.Staff;
import com.beesinergi.model.Staff;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("staffService")
public class StaffService implements CommonService<Staff> {

	@Autowired
	private StaffMapper staffMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public Staff findById(int id) {
		Staff param = new Staff();
		param.setPkStaff(id);
		List<Staff> list = staffMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Staff param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.StaffMapper.selectAll";
		List<Staff> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Staff object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkStaff() == null){
//				object.setCreatedDate(new Date());
				staffMapper.insert(object);
			} else{
//				object.setChangedDate(new Date());
				staffMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<Staff> findAll(Staff param) {
		List<Staff> list = staffMapper.selectAll(param);
		return list;
	}
	
	public List<Staff> findAllTeacher() {
		Staff param = new Staff();
		param.setType(SystemConstant.StaffType.TEACHER);
		List<Staff> list = staffMapper.selectAll(param);
		return list;
	}
	
	public List<Staff> findAllAdmin() {
		Staff param = new Staff();
		param.setType(SystemConstant.StaffType.ADMIN);
		List<Staff> list = staffMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Staff param) {
		int count = staffMapper.selectCount(param);
		return count;
	}

}
