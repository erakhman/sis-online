package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.AppParameterMapper;
import com.beesinergi.model.AppParameter;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;
import com.beesinergi.util.SystemParameter;

@Service("appParameterService")
public class AppParameterService implements CommonService<AppParameter>{
	
	@Autowired
	private AppParameterMapper appParameterMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public AppParameter findById(int id) {
		AppParameter param = new AppParameter();
		param.setPkAppParameter(id);
		List<AppParameter> list = appParameterMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, AppParameter param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.AppParameterMapper.selectAll";
		if (param == null){
			param = new AppParameter();
			param.setIsViewable(SystemConstant.YES);
		}
		List<AppParameter> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(AppParameter object) {
		object.setChangedDate(new Date());
		appParameterMapper.updateByPrimaryKey(object);
		SystemParameter.updateSystemEnvironment(object.getName(),object.getValue().toString());
	}
	
	public void incrementTrxNo(int currentNumber, String trxType) { 
		AppParameter appParameter = new AppParameter();
		appParameter.setName(trxType);
		appParameter.setValue(String.valueOf(currentNumber+1));
		appParameterMapper.updateByName(appParameter);
		SystemParameter.updateSystemEnvironment(trxType, String.valueOf(currentNumber+1));
	}

	@Override
	public Integer getCount(AppParameter param) {
		if (param == null){
			param = new AppParameter();
			param.setIsViewable(SystemConstant.YES);
		}
		int count = appParameterMapper.selectCount(param);
		return count;
	}

	@Override
	public List<AppParameter> findAll(AppParameter param) {
		List<AppParameter> list = appParameterMapper.selectAll(param);
		return list;
	}
	


}
