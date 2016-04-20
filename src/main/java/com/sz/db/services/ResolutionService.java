package com.sz.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.sz.db.models.Resolution;

public class ResolutionService extends AbstractSQLDao<Resolution>{

	@Override
	public String queryGet() {
		return "SELECT * FROM award WHERE id=?;";
	}

	@Override
	protected Resolution resultGet(ResultSet rs) throws Exception {
		Resolution object = new Resolution();
		if(rs.next()){
			object.setId(rs.getInt("id"));
			object.setDateAdd(rs.getDate("date_add"));
			object.setIdDepartament(rs.getInt("id_department"));
			object.setIdCrime(rs.getInt("id_crime"));
			
			object.setDateResolution(rs.getDate("date_award"));
			object.setNumberResolution(rs.getString("number_award"));
			
			object.setIdEmploye(rs.getInt("id_emplloye"));
			object.setDateValid(rs.getDate("date_validity_award"));
			object.setDateSubmission(rs.getDate("date_submission"));
			object.setNote(rs.getString("note"));
		}
		return object;
	}

	@Override
	public String querySave() {
		return "INSERT INTO award (date_add, id_department, id_crime, "
				+ "date_award, number_award, id_emplloye, date_validity_award, date_submission, note) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareSaveUpdate(PreparedStatement ps, Resolution object) throws Exception {
		ps.setDate(1, object.getDateAdd());
		ps.setInt(2, object.getIdDepartament());
		ps.setInt(3, object.getIdCrime());
		
		ps.setDate(4, object.getDateResolution());
		ps.setString(5, object.getNumberResolution());
		
		ps.setInt(6, object.getIdEmploye());
		ps.setDate(7,object.getDateValid());
		ps.setDate(8, object.getDateSubmission());
		ps.setString(9, object.getNote());
	}

	@Override
	public String queryDelete() {
		return "DELETE FROM award WHERE id = ?;";
	}

	@Override
	public String queryUpdate(int id) {
		return "UPDATE award SET date_add = ?, id_department = ?, id_crime = ?, date_award = ?, number_award = ?, "
				+ "id_emplloye = ?, date_validity_award = ?, date_submission = ?, note = ? "
				+ "WHERE id = " + id + ";";
	}

	@Override
	protected String queryGetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Resolution> resultGetAll(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
