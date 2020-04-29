package com.nature.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 分页的数据结构
 * 
 * @author Java
 * @param <K>
 * @since JDK 5.0
 */
@SuppressWarnings("serial")
public final class DataPage<K> implements Serializable {
	/** 数据 */
	private List<K> record;
	/** 整个数据集总记录数 */
	private int totalRecord;
	/** 分页每页大小 */
	private int pageSize;
	/** 当前分页的页码 */
	private int pageNo;
	/** 总分页数 */
	private int pageTotal;
	

	/**
	 * 默认构造函数
	 */
	public DataPage() {
		this.record = new ArrayList<K>();
		this.totalRecord = 0;
		this.pageSize = 1;
		this.pageNo = 1;
	}

	/**
	 * 构造函数
	 * 
	 * @param record
	 * @param totalRecord
	 * @param pageSize
	 * @param pageNo
	 */
	public DataPage(List<K> record, int totalRecord, int pageSize, int pageNo) {
		this.record = record;
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		//this.pageTotal = pageTotal;
	}
	/**
	 * 构造函数
	 * 
	 * @param record
	 * @param totalRecord
	 * @param pageSize
	 * @param pageNo
	 * @param pageTotal
	 */
	public DataPage(List<K> record, int totalRecord, int pageSize, int pageNo,int pageTotal) {
		this.record = record;
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.pageTotal = pageTotal;
	}
	
	public List<K> getRecord() {
		return record;
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageTotal() {
		if(this.totalRecord==0){
			this.pageTotal = 1;
		}else{
			this.pageTotal = (this.totalRecord +  this.pageSize  - 1) / this.pageSize;
		}
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	
	
	
	/**
	 * 获取分页开始的记录下标
	 * @param totalRecord
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public static int countLimitNo(int totalRecord,int pageSize, int pageNo){
		if(totalRecord<=0||pageNo<=1){
			return 0;
		}
		int totalPage = (totalRecord + pageSize  - 1) / pageSize;
		if(pageNo > totalPage){
			return (totalPage-1)*pageSize;
		}
		
		return (pageNo-1)*pageSize;
	}

	/**
	 * 取得List的第N页的subList
	 * 
	 * @param list
	 *            要分页的list
	 * @param pageSize
	 * @param pageNo
	 * @return List
	 */
	public static <T> List<T> subList(List<T> list, int pageSize, int pageNo) {
		pageSize = (pageSize <= 0 ? ConstantsUtil2.NUMBER_10 : pageSize);
		pageNo = (pageNo <= 0 ? 1 : pageNo);
		int begin = (pageSize * (pageNo - 1) > list.size() ? list.size() : pageSize * (pageNo - 1));
		int end = (pageSize * pageNo > list.size() ? list.size() : pageSize * pageNo);
		return list.subList(begin, end);
	}

	/**
	 * 取得List的第N页的DataPage对象
	 * 
	 * @param list
	 *            要分页的list
	 * @param pageSize
	 * @param pageNo
	 * @return List
	 */
	public static <T> DataPage<T> getByList(List<T> list, int pageSize, int pageNo) {
		if (list == null || list.isEmpty()) {
			return new DataPage<T>();
		}
		int totalPage = list.size() % pageSize == 0 ? (list.size() / pageSize) : (list.size() / pageSize + 1);
		if (totalPage < pageNo) {
			pageNo = totalPage;
		}
		if(pageNo < 1){
			pageNo = 1;
		}
		List<T> l = subList(list, pageSize, pageNo);
		return new DataPage<T>(l, list.size(), pageSize, pageNo);
	}

	/**
	 * 分段/块保存数据中获取记录所在块<br/>
	 * blockSize是pageSize的倍数
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param blockSize
	 */
	public static int getBlockNo(int pageSize, int pageNo, int blockSize) {
		int page = pageSize * pageNo / blockSize;
		if (pageSize * pageNo % blockSize > 0) {
			page++;
		}
		return page;
	}

	/**
	 * 从分块保存的list中获取DataPage
	 * 
	 * @param blockList
	 *            具体块集合
	 * @param pageSize
	 *            需要得到分页大小
	 * @param pageNo
	 *            需要得到分页页码
	 * @param blockNo
	 *            块编号,从1开始
	 * @param blockSize
	 *            块大小,一般blockSize=pageSize * N
	 * @param totalRecord
	 *            记录总数
	 * @return
	 */
	public static <T> DataPage<T> getByList(List<T> blockList, int pageSize, int pageNo, int blockNo,
			int blockSize, int totalRecord) {
		if (blockList == null || blockList.isEmpty()) {
			return new DataPage<T>();
		}
		int fromIndex = pageSize * (pageNo - 1) - (blockNo - 1) * blockSize;
		int toIndex = pageSize * pageNo - (blockNo - 1) * blockSize;
		if (blockList.size() < toIndex) {
			toIndex = blockList.size();
		}
		if (fromIndex > toIndex) {
			return new DataPage<T>();
		}
		blockList = blockList.subList(fromIndex, toIndex);
		return new DataPage<T>(blockList, totalRecord, pageSize, pageNo);
	}
	/**
	 * 取得List的第N页的DataPage对象
	 * 
	 * @param list
	 *            要分页的list
	 * @param pageSize
	 * @param pageNo
	 * @param totalRecord
	 * @return List
	 */
	public static <T> DataPage<T> getByList(List<T> List, int pageSize, int pageNo, int totalRecord) {
		if (List == null || List.isEmpty()) {
			return new DataPage<T>();
		}
		int pageTotal = (int)(totalRecord/pageSize)+1;
		return new DataPage<T>(List, totalRecord, pageSize, pageNo,pageTotal);
	}

}