package com.sz.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.sz.db.models.Target;

public class TargetService extends AbstractSQLDao<Target>{

	@Override
	public String queryGet() {
		return "SELECT * FROM object WHERE id=?;";
	}

	@Override
	protected Target resultGet(ResultSet rs) throws Exception {
		Target target = new Target();
		if(rs.next()){
			target.setId(rs.getInt("id"));
			target.setIdResolution(rs.getInt("id_award"));
			target.setTarget(rs.getString("object"));
			target.setOperatorVodafone(rs.getBoolean("vodafone"));
			target.setOperatorKyivstar(rs.getBoolean("kyivstar"));
			target.setOperatorLifecell(rs.getBoolean("lifecell"));
			target.setSendTarget(rs.getBoolean("send"));
			target.setIdTraffic(rs.getInt("id_traffic"));
		}
		return target;
	}

	@Override
	public String querySave() {
		return "INSERT INTO object (id_award, object, send, kyivstar, lifecell, vodafone, id_traffic) VALUES (?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareSaveUpdate(PreparedStatement ps, Target object) throws Exception {
		ps.setInt(1, object.getIdResolution());
		ps.setString(2, object.getTarget());
		ps.setBoolean(3, object.isSendTarget());
		ps.setBoolean(4, object.isOperatorKyivstar());
		ps.setBoolean(5, object.isOperatorLifecell());
		ps.setBoolean(6, object.isOperatorVodafone());
		ps.setInt(7, object.getIdTraffic());
	}

	@Override
	public String queryDelete() {
		return "DELETE FROM object WHERE id = ?;";
	}

	@Override
	public String queryUpdate(int id) {
		return "UPDATE object SET id_award = ?, object = ?, send = ?, kyivstar = ?, lifecell = ?, vodafone = ?, id_traffic = ? WHERE id = " + id + ";";
	}

	@Override
	protected String queryGetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Target> resultGetAll(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
