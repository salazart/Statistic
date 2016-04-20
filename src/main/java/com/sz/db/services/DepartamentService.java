package com.sz.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sz.db.models.Crime;
import com.sz.db.models.Departament;

public class DepartamentService extends AbstractSQLDao<Departament>{

	@Override
	public String queryGet() {
		return "SELECT id, name, name_dov, rank, boss FROM department WHERE id=?;";
	}

	@Override
	protected Departament resultGet(ResultSet rs) throws Exception {
		Departament object = new Departament();
		if(rs.next()){
			setDepartament(rs, object);
		}
		return object;
	}

	private void setDepartament(ResultSet rs, Departament object) throws SQLException {
		object.setId(rs.getInt("id"));
		object.setName(rs.getString("name"));
		object.setNameForReference(rs.getString("name_dov"));
		object.setRank(rs.getString("rank"));
		object.setBoss(rs.getString("boss"));
	}

	@Override
	public String querySave() {
		return "INSERT INTO department (name, name_dov, rank, boss) VALUES (?, ?, ?, ?);";
	}

	@Override
	protected void prepareSaveUpdate(PreparedStatement ps, Departament object) throws Exception {
		ps.setString(1, object.getName());
		ps.setString(2, object.getNameForReference());
		ps.setString(3, object.getRank());
		ps.setString(4, object.getBoss());
	}

	@Override
	public String queryDelete() {
		return "DELETE FROM department WHERE id = ?;";
	}

	@Override
	public String queryUpdate(int id) {
		return "UPDATE department SET name = ?, name_dov = ?, rank = ?, boss = ? WHERE id = " + id + ";";
	}

	@Override
	protected String queryGetAll() {
		return "SELECT id, name, name_dov, rank, boss FROM department;";
	}

	@Override
	protected List<Departament> resultGetAll(ResultSet rs) throws Exception {
		List<Departament> departaments = new ArrayList<Departament>();
		while(rs.next()){
			Departament departament = new Departament();
			setDepartament(rs, departament);
			departaments.add(departament);
		}
		return departaments;
	}
}
