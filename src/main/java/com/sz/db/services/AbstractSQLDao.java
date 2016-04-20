package com.sz.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Entity;

public abstract class AbstractSQLDao  <T extends Entity> extends ConnService implements GenericDao<T>{
    
    protected abstract String queryGet();
    protected abstract T resultGet(ResultSet rs) throws Exception;
    
	@Override
	public T get(int id) throws PersistException {
		T object = null;
		try (PreparedStatement ps = getConnection().prepareStatement(queryGet())) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			object = resultGet(rs);
		 } catch (Exception e) {
			 log.error(e);
	     }
		 return object;
	}

	protected abstract String querySave();
	protected abstract void prepareSaveUpdate(PreparedStatement ps, T object) throws Exception;
	
	@Override
	public T save(T object) throws PersistException {
		if(object.getId() == 0){
			try (PreparedStatement ps = getConnection().prepareStatement(querySave(), Statement.RETURN_GENERATED_KEYS)) {
				prepareSaveUpdate(ps, object);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()){
					object.setId(rs.getInt(1));
				}
	        } catch (Exception e) {
	            log.error(e);
	        }
		} else {
			update(object);
		}
		return object;
	}

	protected abstract String queryDelete();
	
	@Override
	public void delete(T object) {
		try (PreparedStatement ps = getConnection().prepareStatement(queryDelete())) {
			ps.setInt(1, object.getId());
			ps.executeUpdate();
        } catch (Exception e) {
            log.error(e);
        }
	}

	protected abstract String queryUpdate(int id);
	
	@Override
	public T update(T object) {
		log.debug("Try run request: " + queryUpdate(object.getId()));
		try (PreparedStatement ps = getConnection().prepareStatement(queryUpdate(object.getId()), Statement.RETURN_GENERATED_KEYS)) {
			prepareSaveUpdate(ps, object);
			ps.executeUpdate();
        } catch (Exception e) {
            log.error(e);
        }
		return object;
	}
	
	protected abstract String queryGetAll();
    protected abstract List<T> resultGetAll(ResultSet rs) throws Exception;
    
	public List<T> getAll() throws PersistException {
		List<T> objects = null;
		try (ResultSet rs = getConnection().createStatement().executeQuery(queryGetAll())) {
			objects = resultGetAll(rs);
		} catch (Exception e) {
			log.error(e);
	    }
		return objects;
	}
	
}
