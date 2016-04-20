package com.sz.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.sz.db.models.Place;

public class PlaceService extends AbstractSQLDao<Place>{

	@Override
	public String queryGet() {
		return "SELECT * FROM radio_intelligence WHERE id=?;";
	}

	@Override
	protected Place resultGet(ResultSet rs) throws Exception {
		Place object = new Place();
		if(rs.next()){
			object.setId(rs.getInt("id"));
			object.setIdScoution(rs.getInt("id_scouting"));
			
			object.setOperatorKyivstar(rs.getBoolean("kyivstar"));
			object.setOperatorLifecell(rs.getBoolean("lifecell"));
			object.setOperatorVodafone(rs.getBoolean("vodafone"));
			
			object.setLac(rs.getString("lac"));
			object.setCid(rs.getString("cid"));
			object.setPlace(rs.getString("place"));
		}
		return object;
	}

	@Override
	public String querySave() {
		return "INSERT INTO radio_intelligence (id_scouting, lac, cid, place, kyivstar, lifecell, vodafone)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareSaveUpdate(PreparedStatement ps, Place object) throws Exception {
		ps.setInt(1, object.getIdScoution());
		ps.setString(2, object.getLac());
		ps.setString(3, object.getCid());
		ps.setString(4, object.getPlace());
		ps.setBoolean(5, object.isOperatorKyivstar());
		ps.setBoolean(6, object.isOperatorLifecell());
		ps.setBoolean(7, object.isOperatorVodafone());
	}

	@Override
	public String queryDelete() {
		return "DELETE FROM radio_intelligence WHERE id = ?;";
	}

	@Override
	public String queryUpdate(int id) {
		return "UPDATE radio_intelligence SET id_scouting = ?, lac = ?, cid = ?, place = ?, kyivstar = ?, lifecell = ?, vodafone = ?"
				+ " WHERE id = " + id + ";";
	}

	@Override
	protected String queryGetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Place> resultGetAll(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
