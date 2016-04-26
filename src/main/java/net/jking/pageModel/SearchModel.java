package net.jking.pageModel;

import javax.servlet.http.HttpServletRequest;

public class SearchModel {
	private int rows;
	private int page;
	private String anywords;
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getAnywords() {
		return anywords;
	}
	public void setAnywords(String anywords) {
		this.anywords = anywords;
	}
	
	public SearchModel getModel(HttpServletRequest request){
		page = Integer.parseInt(request.getParameter("page"));
		rows = Integer.parseInt(request.getParameter("rows"));
		anywords = request.getParameter("anywords");
		return this;
		
	}
}
