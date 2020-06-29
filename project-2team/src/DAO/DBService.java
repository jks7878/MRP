package DAO;

import java.sql.SQLException;

import DTO.Member;

public interface DBService {
	public void insert(Member m) throws SQLException;
	public boolean select(String id, String pw);
	public boolean selectMId(String id);
}
