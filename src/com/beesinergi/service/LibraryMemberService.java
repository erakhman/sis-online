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

import com.beesinergi.mapper.LibraryMemberMapper;

import com.beesinergi.model.LibraryMember;
import com.beesinergi.util.Paging;

@Service("libraryMemberService")
public class LibraryMemberService implements CommonService<LibraryMember> {

	@Autowired
	private LibraryMemberMapper libraryMemberMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public LibraryMember findById(int id) {
		LibraryMember param = new LibraryMember();
		param.setPkLibraryMember(id);
		List<LibraryMember> list = libraryMemberMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, LibraryMember param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.LibraryMemberMapper.selectAll";
		List<LibraryMember> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(LibraryMember object) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if (object.getPkLibraryMember() == null){
				object.setCreatedDate(new Date());
				libraryMemberMapper.insert(object);
			} else{
				object.setChangedDate(new Date());
				libraryMemberMapper.updateByPrimaryKey(object);
			}
		} catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}
		txManager.commit(status);	
	}

	@Override
	public List<LibraryMember> findAll(LibraryMember param) {
		List<LibraryMember> list = libraryMemberMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(LibraryMember param) {
		int count = libraryMemberMapper.selectCount(param);
		return count;
	}

}
