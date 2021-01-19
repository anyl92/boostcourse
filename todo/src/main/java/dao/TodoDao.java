package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TodoDto;

public class TodoDao {
	private static String dbUrl = "jdbc:mysql://10.113.116.52:13306/user05?serverTimezone=UTC";
	private static String dbUser = "user05";
	private static String dbPw = "user05";
	
	public int addTodo(TodoDto todo) {
		int insertCount = 0;
		
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver" );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "insert into todo (title, name, sequence, type, regDate) values (?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			ps.setString(4, todo.getType());
			ps.setString(5, todo.getRegDate());
			
			insertCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public List<TodoDto> getTodo() {
		List<TodoDto> list = new ArrayList<>();
		
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver" );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "select * from todo order by regdate desc";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser,dbPw);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Long nextId = rs.getLong("id");
					String title = rs.getString(1);
					String name = rs.getString(2);
					Integer sequence = rs.getInt("sequence");
					String type = rs.getString(4);
					String regDate = rs.getString(5);
					TodoDto todo = new TodoDto(nextId, title, name, sequence, type, regDate);
					list.add(todo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
