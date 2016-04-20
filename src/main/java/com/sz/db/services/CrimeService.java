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

public class CrimeService extends AbstractSQLDao<Crime>{
	
	@Override
	public String queryGet() {
		return "SELECT * FROM crime WHERE id=?;";
	}

	@Override
	protected Crime resultGet(ResultSet rs) throws Exception {
		Crime object = new Crime();
		if(rs.next()){
			setCrime(rs, object);
		}
		return object;
	}

	private void setCrime(ResultSet rs, Crime object) throws SQLException {
		object.setId(rs.getInt("id"));
		object.setType(rs.getString("kind_crime"));
		object.setNumber(rs.getString("number"));
		object.setStory(rs.getString("story"));
		object.setRegistrationDate(rs.getDate("date_registration"));
		object.setArticle(rs.getString("article"));
		object.setCrimeDate(rs.getDate("date_crime"));
	}

	@Override
	public String querySave() {
		return "INSERT INTO crime (kind_crime, number, "
				+ "story, date_registration, article, date_crime) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareSaveUpdate(PreparedStatement ps, Crime object) throws Exception {
		ps.setString(1, object.getType());
		ps.setString(2, object.getNumber());
		ps.setString(3, object.getStory());
		ps.setDate(4, object.getRegistrationDate());
		ps.setString(5, object.getArticle());
		ps.setDate(6, object.getCrimeDate());
	}

	@Override
	public String queryDelete() {
		return "DELETE FROM crime WHERE id = ?;";
	}

	@Override
	public String queryUpdate(int id) {
		return "UPDATE crime SET kind_crime = ?, number = ?, story = ?, date_registration = ?, "
				+ "article = ?, date_crime = ? "
				+ "WHERE id = " + id + ";";
	}

	@Override
	protected String queryGetAll() {
		return "SELECT id, kind_crime, number, story, date_registration, article, date_crime FROM crime;";
	}

	@Override
	protected List<Crime> resultGetAll(ResultSet rs) throws Exception {
		List<Crime> crimes = new ArrayList<Crime>();
		while(rs.next()){
			Crime crime = new Crime();
			setCrime(rs, crime);
			crimes.add(crime);
		}
		return crimes;
	}
}
