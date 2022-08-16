package work.board;

import java.io.Serializable;

public class BoardBean implements Serializable{
	private String boardNo;     	//게시글번호
	private String userCode;    	//유저코드
	private String boardTitle;      //게시판본문제목
	private String boardContents;   //게시글내용
	private String boardDate;       //게시글작성일자
	private String boardHits;       //게시글조회수

	public BoardBean() {
		super();
	}

	public BoardBean(String boardNo, String userCode, String boardTitle,
			String boardContents, String boardDate, String boardHits) {
		super();
		this.boardNo = boardNo;
		this.userCode = userCode;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.boardDate = boardDate;
		this.boardHits = boardHits;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardHits() {
		return boardHits;
	}

	public void setBoardHits(String boardHits) {
		this.boardHits = boardHits;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardBean [boardNo=");
		builder.append(boardNo);
		builder.append(", userCode=");
		builder.append(userCode);
		builder.append(", boardTitle=");
		builder.append(boardTitle);
		builder.append(", boardContents=");
		builder.append(boardContents);
		builder.append(", boardDate=");
		builder.append(boardDate);
		builder.append(", boardHits=");
		builder.append(boardHits);
		builder.append("]");
		return builder.toString();
	}

}
