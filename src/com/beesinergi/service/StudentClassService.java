package com.beesinergi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.beesinergi.mapper.SiswaMapper;
import com.beesinergi.mapper.StudentClassMapper;
import com.beesinergi.model.ClassHistory;
import com.beesinergi.model.Siswa;
import com.beesinergi.model.StudentClass;
import com.beesinergi.util.Paging;

@Service("studentClassService")
public class StudentClassService implements CommonService<StudentClass> {

	@Autowired
	private StudentClassMapper studentClassMapper;
	@Autowired
	private SiswaMapper siswaMapper;
	@Autowired
	private ClassHistoryService classHistoryService;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public StudentClass findById(int id) {
		return null;
	}

	@Override
	public Paging findAllByPaging(Paging paging, StudentClass param) {
		return null;
	}

	@Override
	public void save(StudentClass object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkStudentList() != null){
				for (Integer pkStudent:object.getPkStudentList()){
					object.setFkStudent(pkStudent);
					studentClassMapper.insert(object);
					saveStudent(object);
				}
			} else{
				if (object.getPkStudentClass() == null){
					studentClassMapper.insert(object);
				} else{
					studentClassMapper.updateByPrimaryKey(object);
				}
				saveStudent(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}
	
	public void saveStudent(StudentClass object) {
		ClassHistory classHistory = classHistoryService.findById(object.getFkClassHistory());
		Siswa siswa = new Siswa();
		siswa.setPkSiswa(object.getFkStudent());
		siswa.setFkKelas(classHistory.getFkClass());
		siswaMapper.updateByPrimaryKey(siswa);
	}

	@Override
	public List<StudentClass> findAll(StudentClass param) {
		List<StudentClass> list = studentClassMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(StudentClass param) {
		return null;
	}

}
