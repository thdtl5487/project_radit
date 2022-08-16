package org.zerock.board;

import java.util.List;
import java.util.Map;


public interface BoardService {
	public List<Map<String, String>> retrieveBoardList(Map<String, String> boardParam);

	public Map<String, String> retrieveBoard(Map<String, String> boardParam);

	public String retrieveMaxBoardNo();

	public void createBoard(BoardBean board);

	public void updateBoard(BoardBean board);

	public void updateBoardHits(Map<String, String> boardParam);

	public void deleteBoard(Map<String, String> boardParam);
	public void deleteBoard2(Map<String, String> boardParam);
}
