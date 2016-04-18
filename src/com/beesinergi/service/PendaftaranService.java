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
import com.beesinergi.mapper.UserMapper;
import com.beesinergi.mapper.UserRoleMapper;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.AppUserRole;
import com.beesinergi.model.Pendaftaran;
import com.beesinergi.model.PendaftaranDetail;
import com.beesinergi.util.DateTimeFunction;
import com.beesinergi.util.Paging;
import com.beesinergi.util.PasswordUtil;
import com.beesinergi.util.SystemConstant;

@Service("pendaftaranService")
public class PendaftaranService implements CommonService<Pendaftaran> {
	
	@Autowired
	private PendaftaranMapper pendaftaranMapper;
	@Autowired
	private PendaftaranDetailMapper pendaftaranDetailMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserService userService;
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
				object.setCreatedDate(new Date());
				pendaftaranMapper.insert(object);
				generateUserAndPassword(object);
			} else{
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
	
	public void generateUserAndPassword(Pendaftaran pendaftaran) {
		AppUser user = new AppUser();
		user.setFullName(pendaftaran.getNamaSiswa());
		String[] namaSiswa = pendaftaran.getNamaSiswa().split(" ");
		String userName = "";
		for (int i=0; i<namaSiswa.length; i++){
			if (i == namaSiswa.length-1){
				userName += namaSiswa[i];
			} else{
				userName += namaSiswa[i].charAt(0);
			}
		}
		user.setUserName(userName.toLowerCase());
		String tglLahir = DateTimeFunction.date2String(pendaftaran.getTglLahir(), "ddMMyyyy");
		String encryptPassword = PasswordUtil.getEncryptPassword(tglLahir);
		user.setPassword(encryptPassword);
		user.setWrongPassword(0);
		user.setIsLocked("N");
		user.setChangePassword("N");
		user.setCreatedDate(new Date());
		user.setCreatedBy("System");
		userMapper.insert(user);
		
		AppUserRole userRole = new AppUserRole();
		userRole.setFkUser(user.getPkUser());
		userRole.setFkRole(SystemConstant.PK_USER_ROLE_CALON_SISWA);
		userRoleMapper.insert(userRole);
	}
	
	public void deletePendaftaranDetail(Pendaftaran object) {
		pendaftaranDetailMapper.deleteByFkPendaftaran(object.getPkPendaftaran());
	}
	
	public void savePendaftaranDetail(Pendaftaran object) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<ErrorHolder>();
		if (object.getPendaftaranDetailList() != null){
			for(PendaftaranDetail detail:object.getPendaftaranDetailList()){
				detail.setFkPendaftaran(object.getPkPendaftaran());
				pendaftaranDetailMapper.insert(detail);
			}
			if (!errors.isEmpty()){
				throw new SystemException(errors);
			}
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
