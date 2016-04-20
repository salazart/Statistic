package com.sz.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.sz.db.models.Scouting;

public class ScoutingService extends AbstractSQLDao<Scouting>{

	@Override
	public String queryGet() {
		return "SELECT * FROM scouting WHERE id=?;";
	}

	@Override
	protected Scouting resultGet(ResultSet rs) throws Exception {
		Scouting object = new Scouting();
		if(rs.next()){
			object.setId(rs.getInt("id"));
			object.setIdDepartament(rs.getInt("id_department"));
			object.setIdCrime(rs.getInt("id_crime"));
			object.setDateAdd(rs.getDate("date_add"));
			object.setNumberRaport(rs.getString("number_raport"));
			object.setDateRaport(rs.getDate("date_raport"));
			object.setNumberReference(rs.getString("number_dovidka"));
			object.setDateReference(rs.getDate("date_dovidka"));
			object.setIdEmploye(rs.getInt("id_employe"));
		}
		return object;
	}
	
	@Override
	public String querySave() {
		return "INSERT INTO scouting (id_crime, id_department, "
				+ "number_raport, date_raport, number_dovidka, date_dovidka, id_employe, note) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareSaveUpdate(PreparedStatement ps, Scouting object) throws Exception {
		ps.setInt(1, object.getIdCrime());
		ps.setInt(2, object.getIdDepartament());
		ps.setString(3, object.getNumberRaport());
		ps.setDate(4, object.getDateRaport());
		ps.setString(5, object.getNumberReference());
		ps.setDate(6, object.getDateReference());
		ps.setInt(7, object.getIdEmploye());
		ps.setString(8, object.getNote());
	}

	@Override
	public String queryDelete() {
		return "DELETE FROM scouting WHERE id = ?;";
	}

	@Override
	public String queryUpdate(int id) {
		return "UPDATE scouting SET id_crime = ?, id_department = ?, number_raport = ?, date_raport = ?, "
				+ "number_dovidka = ?, date_dovidka = ?, id_employe = ?, note = ? "
				+ "WHERE id = " + id +";";
	}

	@Override
	protected String queryGetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Scouting> resultGetAll(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
