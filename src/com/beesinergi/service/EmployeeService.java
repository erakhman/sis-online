package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.EmployeeMapper;
import com.beesinergi.model.Employee;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("employeeService")
public class EmployeeService implements CommonService<Employee> {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Employee findById(int id) {
		Employee param = new Employee();
		param.setPkEmployee(id);
		List<Employee> list = employeeMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Employee param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.EmployeeMapper.selectAll";
		if (param != null){
			if (param.getEmployeeName() != null){
				param.setEmployeeName("%"+param.getEmployeeName().toLowerCase()+"%");
			}
		}
		List<Employee> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Employee object) throws Exception {
		if (object.getPkEmployee() == null){
			object.setCreatedDate(new Date());
			employeeMapper.insert(object);
		} else{
			if (object.getIsActive() == null){
				object.setIsActive(SystemConstant.NO);
			}
			object.setChangedDate(new Date());
			employeeMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public List<Employee> findAll(Employee param) {
		List<Employee> list = employeeMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Employee param) {
		if (param != null){
			if (param.getEmployeeName() != null){
				param.setEmployeeName("%"+param.getEmployeeName().toLowerCase()+"%");
			}
		}
		int count = employeeMapper.selectCount(param);
		return count;
	}
	

}
