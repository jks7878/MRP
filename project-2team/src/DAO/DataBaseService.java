package DAO;

import java.sql.SQLException;

import Main.Member;

public interface DataBaseService {
	public void insert(Member member) throws SQLException;
	public boolean select(String id, String pw);
}
