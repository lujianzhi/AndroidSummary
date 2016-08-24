package com.example.lawson.androidsummery.detectmemory.entity;

public class ObjImplComparable implements Comparable<ObjImplComparable> {

	private String content;
	private String title;
	private int id;

	public ObjImplComparable(int id, String content, String title) {
		super();
		this.content = content;
		this.title = title;
		this.id = id;
	}

	public ObjImplComparable(String content, String title) {
		super();
		this.content = content;
		this.title = title;
	}

	public ObjImplComparable(String content) {
		super();
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(ObjImplComparable o) {
		return id>o.id?1:(id==o.id?0:-1);
	}

	@Override
	public String toString() {
		return "ObjImplComparable [id=" + id + ", title=" + title + ", content=" + content + "]\n";
	}


}
