package com.ssafy.sunbis.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel
public class PostDto {
	int postno;
	int boardtype;
	String writer;
	String writerName;
	String title;
	String content;
	String cdate;
	int likeCount;
	int dislikeCount;
	int replyCount;
	List<ReplyDto> replyList;
	public PostDto() {
		super();
	}
	public int getPostno() {
		return postno;
	}
	public void setPostno(int postno) {
		this.postno = postno;
	}
	public int getBoardtype() {
		return boardtype;
	}
	public void setBoardtype(int boardtype) {
		this.boardtype = boardtype;
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
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getDislikeCount() {
		return dislikeCount;
	}
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public List<ReplyDto> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<ReplyDto> replyList) {
		this.replyList = replyList;
	}
	@Override
	public String toString() {
		return "PostDto [postno=" + postno + ", boardtype=" + boardtype + ", writer=" + writer + ", writerName="
				+ writerName + ", title=" + title + ", content=" + content + ", cdate=" + cdate + ", likeCount="
				+ likeCount + ", dislikeCount=" + dislikeCount + ", replyCount=" + replyCount + ", replyList="
				+ replyList + "]";
	}
}
