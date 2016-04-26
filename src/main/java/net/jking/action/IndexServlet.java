package net.jking.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;

import net.jking.pageModel.DataList;
import net.jking.pageModel.Token;
import net.jking.pageModel.XmlModel;
import net.jking.service.BookSearchService;
import net.jking.util.GenericUtil;
import net.jking.util.XmlUtil;

public class IndexServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Token token = new Token().getToken(request);
		if (token == null) {
			return;
		}
		List<String> params = new ArrayList<String>();
		params.add(token.getToken());
		params.add(token.getTimestamp());
		params.add(token.getNonce());
		Collections.sort(params, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String temp = GenericUtil.encode(params.get(0) + params.get(1)
				+ params.get(2));
		if (temp.equals(token.getSignature())) {
			response.getWriter().println(token.getEchostr());

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		XmlModel model = XmlUtil.getModel(request);
		if (!"text".equals(model.getMsgType())) {
			return;
		}
		BookSearchService service = new BookSearchService();
		DataList data = service.getDataList(model);
		Document doc = XmlUtil.dataToXML(data);
		response.getWriter().print(doc.asXML());
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
