package net.jking.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.jking.pageModel.BookInfo;
import net.jking.pageModel.Location;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookService {

	public static String Url = "http://210.38.138.1:81/bookinfo.aspx?ctrlno=";
	
	public BookInfo getBookInfo(String num) throws IOException{
		
		BookInfo bookInfo = new BookInfo();
		List<Location> locations = new ArrayList<Location>();
		String connectUrl = Url +num;
		Document doc = Jsoup.connect(connectUrl).header("Accept-Language", "zh-CN,zh;q=0.8").get();
		String contents[] = doc.getElementById("carddiv").text().split(" 　　");
		StringBuffer descrs = new StringBuffer();
		for(int i=0;i<contents.length;i++){
			descrs.append("<div>").append(contents[i]).append("</div>");
		}
		Element tbody = doc.getElementsByTag("tbody").get(0);
		Elements lists = tbody.getElementsByTag("tr");
		for(Element list:lists){
			Location location = new Location();
			Elements cols = list.getElementsByTag("td");
			location.setLocal(cols.get(0).select("a").text());
			location.setNum(cols.get(1).text());
			location.setStatus((cols.get(5).text()));
			locations.add(location);
		}
		bookInfo.setDescr(descrs.toString());
		bookInfo.setLocations(locations);
		return bookInfo;
		
		
		
	}
	
}
