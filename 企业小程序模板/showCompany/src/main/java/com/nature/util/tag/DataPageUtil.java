﻿package com.nature.util.tag;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.nature.util.DataPage;



/**
 * 分页的数据结构
 * @author Java
 * @param <K>
 * @since JDK 5.0
 */
@SuppressWarnings("serial")
public final class DataPageUtil<K> implements Serializable
{
	/** 数据 */
	private List<K> record;
	/** 整个数据集总记录数 */
	private int totalRecord;
	/** 分页每页大小 */
	private int pageSize;
	/** 当前分页的页码 */
	private int pageNo;
	
	/**
	 * 默认构造函数
	 */
	public DataPageUtil()
	{
		this.record = new ArrayList<K>();
		this.totalRecord = 0;
		this.pageSize = 1;
		this.pageNo = 1;
	}	
	
	/**
	 * 构造函数
	 * @param record
	 * @param totalRecord
	 * @param pageSize
	 * @param pageNo
	 */
	public DataPageUtil(List<K> record,int totalRecord,int pageSize,int pageNo)
	{
		this.record = record;
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
	
	/**
	 * 构造函数
	 * @param record
	 * @param totalRecord
	 * @param pageSize
	 * @param pageNo
	 */
	public DataPageUtil(DataPage<K> dataPage)
	{
		this.record = dataPage.getRecord();
		this.totalRecord = dataPage.getTotalRecord();
		this.pageSize = dataPage.getPageSize();
		this.pageNo = dataPage.getPageNo();
	}	
	
	/**
	 * 取得分页数据
	 * @return List<K>
	 */
	public List<K> getRecord()
	{
		return this.record;
	}
	
	/**
	 * 取得每页大小
	 * @return int
	 */
	public int getPageSize()
	{
		return this.pageSize;
	}
	
	/**
	 * 取当前面页码
	 * @return int
	 */
	public int getPageNo()
	{
		return this.pageNo;
	}
	
	/**
	 * 取得整个数据集总页数
	 * @return int
	 */
	public int getPageCount()
	{
		return (int) Math.ceil(this.totalRecord * 1.000 / this.pageSize);	
	}
	
	/**
	 * 取得当前页总记录数
	 * @return int
	 */
	public int getRecordCount()
	{
		return this.record.size();	
	}
	
	/**
	 * 取得整个记录集总记录数
	 * @return int
	 */
	public int getTotalRecordCount()
	{
		return this.totalRecord;	
	}	
	
	/**
	 * 当前页是否有下一页
	 * @return boolean
	 */
	public boolean hasNextPage()
	{
		return this.pageNo < getPageCount();
	}
	/**
	 * 当前页是否有上一页
	 * @return boolean
	 */	
	public boolean hasPrevPage()
	{
		return this.pageNo > 1;		
	}
	
	/**
	 * 取得List的第N页的subList
	 * @param list 要分页的list
	 * @param pageSize
	 * @param pageNo
	 * @return List
	 */
	public static<T> List<T> subList(List<T> list, int pageSize, int pageNo)
	{
		pageSize = (pageSize <= 0 ? 10 : pageSize);
		pageNo = (pageNo <= 0 ? 1 : pageNo);	
		int begin = (pageSize * (pageNo - 1) > list.size() ? list.size(): pageSize * (pageNo - 1));
		int end = (pageSize * pageNo > list.size() ? list.size() : pageSize * pageNo);		
		return list.subList(begin,end);
	}
	
	/**
	 * 取得List的第N页的DataPage对象
	 * @param list 要分页的list
	 * @param pageSize
	 * @param pageNo
	 * @return List
	 */
	public static<T> DataPage<T> getByList(List<T> list, int pageSize, int pageNo)
	{
		List<T> l = subList(list, pageSize, pageNo);
		return new DataPage<T>(l, list.size(), pageSize, pageNo);
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{ record: [");
		for(int i = 0; i < record.size(); ++i)
		{
			sb.append(record.get(i));
			if(i != record.size())
				sb.append(", ");
		}
		sb.append("], pageNo: ")
			.append(pageNo)
			.append(", pageSize: ")
			.append(pageSize)
			.append(", totalRecord: ")
			.append(totalRecord)
			.append(" }");
		return sb.toString();
	}

	public void setRecord(List<K> record) {
		this.record = record;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}