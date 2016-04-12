package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.ReservationMapper;
import com.beesinergi.model.Reservation;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("reservationService")
public class ReservationService implements CommonService<Reservation> {
	
	@Autowired
	private ReservationMapper reservationMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Reservation findById(int id) {
		Reservation param = new Reservation();
		param.setPkReservation(id);
		List<Reservation> list = reservationMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Reservation param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.ReservationMapper.selectAll";
		List<Reservation> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Reservation object) throws Exception {
		if (object.getPkReservation() == null){
			object.setCreatedDate(new Date());
			reservationMapper.insert(object);
		} else{
			object.setChangedDate(new Date());
			reservationMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public List<Reservation> findAll(Reservation param) {
		List<Reservation> list = reservationMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Reservation param) {
		int count = reservationMapper.selectCount(param);
		return count;
	}
	

}
