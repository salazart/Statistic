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
import com.sz.db.models.Employe;

public class EmployeService extends AbstractSQLDao<Employe>{

	@Override
	public String queryGet() {
		return "SELECT id, name FROM employe WHERE id=?;";
	}

	@Override
	protected Employe resultGet(ResultSet rs) throws Exception {
		Employe object = new Employe();
		if(rs.next()){
			setEmploye(rs, object);
		}
		return object;
	}

	private void setEmploye(ResultSet rs, Employe object) throws SQLException {
		object.setId(rs.getInt("id"));
		object.setName(rs.getString("name"));
	}

	@Override
	public String querySave() {
		return "INSERT INTO employe (name) VALUES (?);";
	}

	@Override
	protected void prepareSaveUpdate(PreparedStatement ps, Employe object) throws Exception {
		ps.setString(1, object.getName());
	}

	@Override
	public String queryDelete() {
		return "DELETE FROM employe WHERE id = ?;";
	}

	@Override
	public String queryUpdate(int id) {
		return "UPDATE employe SET name = ? WHERE id = " + id + ";";
	}

	@Override
	protected String queryGetAll() {
		return "SELECT id, name FROM employe;";
	}

	@Override
	protected List<Employe> resultGetAll(ResultSet rs) throws Exception {
		List<Employe> employes = new ArrayList<Employe>();
		while(rs.next()){
			Employe employe = new Employe();
			setEmploye(rs, employe);
			employes.add(employe);
		}
		return employes;
	}
}
