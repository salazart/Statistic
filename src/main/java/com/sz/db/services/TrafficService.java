package com.sz.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.sz.db.models.Traffic;

public class TrafficService extends AbstractSQLDao<Traffic>{

	@Override
	protected String queryGet() {
		return "SELECT * FROM traffic WHERE id=?;";
	}

	@Override
	protected Traffic resultGet(ResultSet rs) throws Exception {
		Traffic object = new Traffic();
		if(rs.next()){
			object.setId(rs.getInt("id"));
			object.setDateAdd(rs.getDate("date_add"));
			object.setIdDepartament(rs.getInt("id_department"));
			object.setIdCrime(rs.getInt("id_crime"));
		}
		return object;
	}

	@Override
	protected String querySave() {
		return "INSERT INTO traffic (date_add, id_department, id_crime) "
				+ "VALUES (?, ?, ?);";
	}

	@Override
	protected void prepareSaveUpdate(PreparedStatement ps, Traffic object) throws Exception {
		ps.setDate(1, object.getDateAdd());
		ps.setInt(2, object.getIdDepartament());
		ps.setInt(3, object.getIdCrime());
	}

	@Override
	protected String queryDelete() {
		return "DELETE FROM traffic WHERE id = ?;";
	}

	@Override
	protected String queryUpdate(int id) {
		return "UPDATE traffic SET id_department = ?, id_crime = ? WHERE id = " + id + ";";
	}

	@Override
	protected String queryGetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Traffic> resultGetAll(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
