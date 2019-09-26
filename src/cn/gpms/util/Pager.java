package cn.gpms.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author бнЪО
 *
 * @param <T>
 */
public class Pager<T> {
	
	private int page;
	private int totalPage;
	private int total;
	private int size;
	private List<T> dataSets=new ArrayList<T>();
	private boolean hasPre;
	private boolean hasNext;
	private boolean hasLast;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {

		return this.totalPage;
	}
	public void initTotalPage() {
		int totalpage=this.total/this.size;
		this.totalPage= this.total%this.size==0?totalpage:(totalpage+1);
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getDataSets() {
		return dataSets;
	}
	public void setDataSets(List<T> dataSets) {
		this.dataSets = dataSets;
	}

	public boolean isHasPre() {
		this.hasPre = this.page>1;
		return this.hasPre;
	}

	public boolean isHasNext() {
		this.hasNext= this.page<this.totalPage;
		return this.hasNext;
	}
	public boolean isHasLast() {
		this.hasLast =this.page==this.totalPage;
		return hasLast;
	}
    public void init(){
    	initTotalPage();
    	isHasPre();
    	isHasNext();
    	isHasLast();
	   
   }

}
