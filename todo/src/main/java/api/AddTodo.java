package api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.TodoDto;

@WebServlet("/add")
public class AddTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddTodo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/addTodo.jsp");
    	requestDispatcher.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		TodoDto todo = new TodoDto();
		todo.setName("name");
		todo.setTitle("title");

		TodoDao dao = new TodoDao();
		dao.addTodo(todo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/todos.jsp");
		rd.forward(request, response);
	}

}
