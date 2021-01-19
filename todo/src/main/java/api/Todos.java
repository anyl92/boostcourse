package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.TodoDao;
import dto.TodoDto;

@WebServlet("/todos")
public class Todos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Todos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		TodoDao dao = new TodoDao();
		List<TodoDto> list = dao.getTodo();
		System.out.println(list);
//		request.setAttribute("getList", list);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(list);
		
		request.setAttribute("getJson", json);
		
		RequestDispatcher rd = request.getRequestDispatcher("/todos.jsp");
		rd.forward(request, response);
		
		PrintWriter out = response.getWriter();
		out.println(list);
		out.close();
	}
}
