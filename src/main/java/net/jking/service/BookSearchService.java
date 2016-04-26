package net.jking.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.jking.pageModel.Book;
import net.jking.pageModel.DataGrid;
import net.jking.pageModel.DataList;
import net.jking.pageModel.SearchModel;
import net.jking.pageModel.XmlModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookSearchService {

	private static String url = "http://210.38.138.1:81/searchresult.aspx?";

	public Integer getBooks(String anywords, int page, int rows,
			List<Book> books) throws IOException {
		String connectUrl = url + "anywords="
				+ URLEncoder.encode(anywords, "gb2312") + "&page=" + page
				+ "&dp=" + rows;
		Document doc = Jsoup.connect(connectUrl)
				.header("Accept-Language", "zh-CN,zh;q=0.8").get();
		Elements table = doc.getElementsByTag("tbody");
		Element countEle = doc
				.getElementById("ctl00_ContentPlaceHolder1_countlbl");
		int count = 0;
		if (countEle != null) {

			count = Integer.parseInt(doc.getElementById(
					"ctl00_ContentPlaceHolder1_countlbl").text());
		}

		if (count == 0) {
			return count;
		}
		Element tbody = table.get(0);
		Elements lists = tbody.getElementsByTag("tr");
		for (Element list : lists) {
			Book book = new Book();
			Elements cols = list.getElementsByTag("td");
			String num = cols.get(1).select("span a").attr("href").split("=")[1];
			String url = "http://weschool.jking.net/book?ctrlno=" + num;
			book.setUrl(url);
			book.setName(cols.get(1).select("span a").text());
			book.setDescripe(cols.get(2).text());
			book.setPublisher(cols.get(3).text());
			book.setCount(Integer.parseInt(cols.get(6).text()));
			book.setSurplus(Integer.parseInt(cols.get(7).text()));
			books.add(book);
		}
		return count;
	}

	public DataList getDataList(XmlModel model) throws IOException {
		DataList data = new DataList();
		List<Book> books = new ArrayList<Book>();
		int count = getBooks(model.getContent(), 1, 8, books);
		data.setBooks(books);
		data.setFromUserName(model.getToUserName());
		data.setToUserName(model.getFromUserName());
		data.setCreateTime(new Date().getTime() + "");
		data.setMsgType("news");
		data.setCount(count);
		return data;
	}
	
	public DataGrid getDataList(SearchModel model) throws IOException{
		DataGrid data = new DataGrid();
		List<Book> books = new ArrayList<Book>();
		int total = getBooks(model.getAnywords(), model.getPage(), model.getRows(), books);
		data.setRows(books);
		data.setTotal(total);
		return data;
	}

}
