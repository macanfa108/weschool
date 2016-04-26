package net.jking.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jking.pageModel.BookInfo;
import net.jking.service.BookService;

public class BookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BookServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctrlno = request.getParameter("ctrlno");
		BookService serive = new BookService();
		BookInfo bookInfo= serive.getBookInfo(ctrlno);
		request.setAttribute("bookInfo", bookInfo);
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
