package net.jking.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;

import net.jking.pageModel.DataGrid;
import net.jking.pageModel.SearchModel;
import net.jking.service.BookSearchService;

public class SearchBookServlet extends HttpServlet {

	public SearchBookServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SearchModel model = new SearchModel().getModel(request);
		BookSearchService service = new BookSearchService();
		DataGrid data = service.getDataList(model);
		String json = JSON.toJSONStringWithDateFormat(data, "yyyy-MM-dd HH:mm:ss");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
