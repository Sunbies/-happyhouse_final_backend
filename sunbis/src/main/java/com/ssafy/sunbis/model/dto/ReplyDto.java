package com.ssafy.sunbis.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public class ReplyDto {
	int replyno;
	int postno;
	String writer;
	String writerName;
	String content;
	String cdate;
	public ReplyDto() {
		super();
	}
	public int getReplyno() {
		return replyno;
	}
	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}
	public int getPostno() {
		return postno;
	}
	public void setPostno(int postno) {
		this.postno = postno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	@Override
	public String toString() {
		return "ReplyDto [replyno=" + replyno + ", postno=" + postno + ", writer=" + writer + ", writerName="
				+ writerName + ", content=" + content + ", cdate=" + cdate + "]";
	}
}
