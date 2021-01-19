import java.util.List;

import dao.TodoDao;
import dto.TodoDto;

public class test1 {
    // addToDo Test Code
//	public static void main(String[] args) {
//		long id = 3;
//		String title = "할일";
//		String name = "유림";
//		int sequence = 1;
//		String type = "TODO";
//		
//		TodoDto todo = new TodoDto(id, title, name, sequence, type, null);
//		
//		TodoDao dao = new TodoDao();
//		int insertCount = dao.addTodo(todo);
//		
//		System.out.println(insertCount);
//	}
	
	// getTodo Test Code
	public static void main(String[] args) {
		TodoDao dao = new TodoDao();
		List<TodoDto> list = dao.getTodo();
		for (TodoDto todo : list) {
			System.out.println(todo);
		}
	}
}
