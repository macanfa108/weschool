package net.jking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.jking.pageModel.Book;
import net.jking.pageModel.DataList;
import net.jking.pageModel.XmlModel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	public static XmlModel getModel(HttpServletRequest request) throws IOException{
		XmlModel model = new XmlModel();
		InputStream is = request.getInputStream();
		SAXReader saxReader = new SAXReader();
		try {
			Document doc = saxReader.read(is);
			Element root = doc.getRootElement();
			Element toUserName  = root.elements("ToUserName").get(0);
			Element fromUserName = root.elements("FromUserName").get(0);
			Element createTime = root.elements("CreateTime").get(0);
			Element msgType = root.elements("MsgType").get(0);
			Element content = root.elements("Content").get(0);
			Element msgId = root.elements("MsgId").get(0);
			model.setContent(content.getText());
			model.setCreateTime(createTime.getText());
			model.setFromUserName(fromUserName.getText());
			model.setMsgId(msgId.getText());
			model.setMsgType(msgType.getText());
			model.setToUserName(toUserName.getText());
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		return model;
	}
	
	
	
	public static Document dataToXML(DataList data){
		Document document = DocumentHelper.createDocument();
		Element xml = document.addElement("xml");
		Element toUsername = xml.addElement("ToUserName");
		Element fromUserName = xml.addElement("FromUserName");
		Element createTime = xml.addElement("CreateTime");
		Element msgType = xml.addElement("MsgType");
		Element articleCount = xml.addElement("ArticleCount");
		Element articles = xml.addElement("Articles");
		toUsername.addCDATA(data.getToUserName());
		fromUserName.addCDATA(data.getFromUserName());
		createTime.setText(data.getCreateTime());
		msgType.addCDATA(data.getMsgType());
		List<Book> books = data.getBooks();
		articleCount.setText(books.size()+1+"");
		Element picItem = articles.addElement("item");
		picItem.addElement("Title").addCDATA("找到"+data.getCount()+"本书,点击查看结果");
		picItem.addElement("PicUrl").addCDATA("http://weschool.jking.net/images/"+((int)(Math.random()*7)+1)+".jpg");
		picItem.addElement("Url").addCDATA("http://weschool.jking.net");
		for(Book book:books){
		Element item = articles.addElement("item");
		item.addElement("Title").addCDATA(book.getName());
		item.addElement("Description").addCDATA(book.getDescripe());
		item.addElement("Url").addCDATA(book.getUrl());
		}
		return document;
	}
}
