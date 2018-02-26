package com.system.po;

import java.util.List;

public class ShowPage<E> {

	private Integer pageSize = 5;
	private Integer page;
	private Long total;
	private Long totalPage;
	private List<E> list;
	private String action;
	
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getTotalPage() {
		return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	
	
}
