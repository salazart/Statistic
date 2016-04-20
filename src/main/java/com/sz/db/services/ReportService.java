package com.sz.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ReportService extends ConnService{
	
	public void createReport(String query, List<String> params){
		try(PreparedStatement ps = getConnection().prepareStatement(query)){
			for (int i = 0; i < params.size(); i++) {
				ps.setString(i + 1, params.get(i));
			}
			ps.executeUpdate();
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public List<List<String>> getReport(String query, List<String> params, List<List<String>> report){
		
		try(PreparedStatement ps = getConnection().prepareStatement(query)){
			prepareRequest(query, params, ps);
			
			ResultSet rs = ps.executeQuery();
			
			parseResponse(report, rs);
		} catch (Exception e) {
			log.error(e);
		}
		return report;
	}

	private void parseResponse(List<List<String>> report, ResultSet rs) throws SQLException {
		while(rs.next()){
			List<String> row = new ArrayList<String>();
			for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
				row.add(rs.getString(i).trim());
			}
			report.add(row);
		}
	}

	private void prepareRequest(String query, List<String> params, PreparedStatement ps) throws SQLException {
		int count = StringUtils.countMatches(query, "?");
		if(count != 0){
			for (int i = 0; i < params.size(); i++) {
				ps.setString(i + 1, params.get(i));
			}
		}
	}
	
	public List<List<String>> getReport(String query){
		List<List<String>> rows = new ArrayList<>();
		try(ResultSet rs = getConnection().createStatement().executeQuery(query)){
			parseResponse(rows, rs);
		} catch (Exception e) {
			log.error(e);
		}
		return rows;
	}
}
