package ua.serviceImplementation.tag;

import static java.lang.String.valueOf;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.data.domain.Page;

public class PageableTag extends SimpleTagSupport{

	private final static int first = 1;
	private final static int visible = 5;
	private final static String amper = "&";
	private final static String quest = "?";
	private final static String equal = "=";
	
	private final StringWriter sw = new StringWriter();
	
	private int last;
	
	private int current;
	
	private int size;
	
	private String stContainer = "<tr>";
	private String endContainer = "</tr>";
	private String stCell = "<td>";
	private String endCell = "</td>";
	private String activeClass = "active";
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		sw.append(stContainer);
		buildFirstPage();
		buildLeftArrow();
		if(last<=visible){
			for (int i = 1; i <= last; i++) {
				buildOneCell(i);
			}
		}else{
			int start = (current-visible/2) >= first ? (current-visible/2) : first;
			int finish = (current+visible/2) <= last ? (current+visible/2) : last;
			start = (finish - start < visible) ? (finish - visible + 1) : start;
			start = start <= 0 ? first : start;
			finish = (finish - start) < visible ? start + visible - 1 : finish;
			finish = finish > last ? last : finish;
			for (; start <= finish; start++) {
				buildOneCell(start);
			}
		}
		buildRightArrow();
		buildLastPage();
		sw.append(endContainer);
		out.println(sw.toString());
	}
	
	private void buildLastPage(){
		sw.append(stCell);
		sw.append("<a href='");
		sw.append(quest);
		sw.append("page=");
		sw.append(valueOf(last));
		sw.append(amper);
		sw.append("size=");
		sw.append(valueOf(size));
		addAllParameters();
		sw.append("'>");
		sw.append(">>");
		sw.append("</a>");
		sw.append(endCell);
	}
	
	private void buildFirstPage(){
		sw.append(stCell);
		sw.append("<a href='");
		sw.append(quest);
		sw.append("page=");
		sw.append(valueOf(first));
		sw.append(amper);
		sw.append("size=");
		sw.append(valueOf(size));
		addAllParameters();
		sw.append("'>");
		sw.append("<<");
		sw.append("</a>");
		sw.append(endCell);
	}
	
	private void buildRightArrow(){
		sw.append(stCell);
		sw.append("<a href='");
		sw.append(quest);
		sw.append("page=");
		if(current == last) sw.append(valueOf(current));
		else sw.append(valueOf(current+1));
		sw.append(amper);
		sw.append("size=");
		sw.append(valueOf(size));
		addAllParameters();
		sw.append("'>");
		sw.append(">");
		sw.append("</a>");
		sw.append(endCell);
	}
	
	private void buildLeftArrow(){
		sw.append(stCell);
		sw.append("<a href='");
		sw.append(quest);
		sw.append("page=");
		if(current == first) sw.append(valueOf(current));
		else sw.append(valueOf(current-1));
		sw.append(amper);
		sw.append("size=");
		sw.append(valueOf(size));
		addAllParameters();
		sw.append("'>");
		sw.append("<");
		sw.append("</a>");
		sw.append(endCell);
	}
	
	private void buildOneCell(int number){
		if(number == current){
			sw.append(stCell.substring(0, stCell.length()-1));
			sw.append(" class='");
			sw.append(activeClass);
			sw.append("'>");
		}else{
			sw.append(stCell);
		}
		sw.append("<a href='");
		sw.append(quest);
		sw.append("page=");
		sw.append(valueOf(number));
		sw.append(amper);
		sw.append("size=");
		sw.append(valueOf(size));
		addAllParameters();
		sw.append("'>");
		sw.append(valueOf(number));
		sw.append("</a>");
		sw.append(endCell);
	}
	
	private void addAllParameters(){
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Map<String, String[]> map = request.getParameterMap();
		for(Entry<String, String[]> entry : map.entrySet()){
			for(String value : entry.getValue()){
				if(!(entry.getKey().equals("page")||entry.getKey().equals("size"))){
					sw.append(amper);
					sw.append(entry.getKey());
					sw.append(equal);
					sw.append(value);
				}
			}
		}
	}
	
	public void setPage(Page<?> page) {
		last = page.getTotalPages();
		current = page.getNumber()+1;
		size = page.getSize();
	}
	/**
	 * <ul class=pagination></ul>*/
	public void setContainer(String container){
		stContainer = container.substring(0, container.indexOf("<", 1));
		endContainer = container.substring(container.indexOf("<", 1));
	}
	/**
	 * <li></li>*/
	public void setCell(String cell){
		stCell = cell.substring(0, cell.indexOf("<", 1));
		endCell = cell.substring(cell.indexOf("<", 1));
	}
}