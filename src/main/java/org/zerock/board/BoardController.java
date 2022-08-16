package work.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import work.user.UserService;

@Controller
public class BoardController {
	@Resource(name = "boardService")
	private BoardService boardService;

	@Resource(name = "userService")
	private UserService userService;


	@RequestMapping(value="/work/board/createBoard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView createBoard(@ModelAttribute BoardBean board, HttpServletRequest request){
		HttpSession session = request.getSession();

		String userCode = (String)session.getAttribute("userCode");

		ModelAndView mv = new ModelAndView();

		String flag = board.getBoardTitle(); //BoardBean 존재여부

		if(flag == null){
			mv.setViewName("/board/boardRegisterC");
		}else if(flag != null){
			//게시글 생성
			board.setUserCode(userCode);
			boardService.createBoard(board);

			String maxBoardNo = boardService.retrieveMaxBoardNo();

			mv.setViewName("redirect:/work/board/retrieveBoard.do?maxBoardNo=" + maxBoardNo + "&fromCreate=true");
		}

		return mv;
	}
	@RequestMapping(value="/work/board/goMain.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String goMain(){
//		ModelAndView mv = new ModelAndView();
//
//		mv.setViewName("/intro/index");

		return "redirect:/intro/index.jsp";
	}

	@RequestMapping(value="/work/board/retrieveBoard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView retrieveBoard(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();

		String boardNo = request.getParameter("maxBoardNo");
		String fromRating = request.getParameter("fromRating");
		String fromCreate = request.getParameter("fromCreate");
		String fromReply = request.getParameter("fromReply");

		if(boardNo == null) boardNo = request.getParameter("boardNo");

		Map<String, String> boardParam = new HashMap<String, String>();
		Map<String, String> replyParam = new HashMap<String, String>();

		boardParam.put("boardNo", boardNo);
		replyParam.put("boardNo", boardNo);

		//조회수 증가
		if(!"true".equals(fromRating) && !"true".equals(fromCreate) && !"true".equals(fromReply) ){
			boardService.updateBoardHits(boardParam);
		}

		Map<String, String> dsBoard = boardService.retrieveBoard(boardParam);


		mv.addObject("dsBoard", dsBoard);

		mv.setViewName("/board/boardR");

		return mv;
	}

	@RequestMapping(value="/work/board/retrieveBoardList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView retrieveBoardList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();

		Map<String, String> boardParam = new HashMap<String, String>();

		List<Map<String, String>> dsBoardList = boardService.retrieveBoardList(boardParam);

		mv.addObject("dsBoardList", dsBoardList);
		mv.setViewName("/board/boardListR");

		return mv;
	}

	@RequestMapping(value="/work/board/deleteBoard.do", method=RequestMethod.GET)
	public ModelAndView deleteBoard(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();

		Map<String, String> boardParam = new HashMap<String, String>();
		Map<String, String> replyParam = new HashMap<String, String>();
		Map<String, String> markParam = new HashMap<String, String>();

		HttpSession session = request.getSession();

		String userCode = (String)session.getAttribute("userCode");
		String boardNo = request.getParameter("boardNo");

		boardParam.put("userCode", userCode);
		boardParam.put("boardNo", boardNo);

		replyParam.put("boardNo", boardNo);

		markParam.put("boardNo", boardNo);

		//글 삭제
		boardService.deleteBoard(boardParam);

		mv.setViewName("redirect:/work/board/retrieveBoardList.do");

		return mv;
	}

	@RequestMapping(value="/work/board/updateBoardRating.do", method=RequestMethod.GET)
	public ModelAndView updateBoardRating(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();

		HttpSession session = request.getSession();

		Map<String, String> boardParam = new HashMap<String, String>();
		Map<String, String> markParam = new HashMap<String, String>();

		String userCode = (String)session.getAttribute("userCode");
		String boardNo = request.getParameter("boardNo");

		boardParam.put("boardNo", boardNo);

		markParam.put("userCode", userCode);
		markParam.put("boardNo", boardNo);

		mv.setViewName("redirect:/work/board/retrieveBoard.do?boardNo=" + boardNo + "&fromRating=true");

		return mv;
	}

	@RequestMapping(value="/work/board/updateBoard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateBoard(HttpServletRequest request, @ModelAttribute BoardBean bean){
		Map<String, String> boardParam = new HashMap<String, String>();
		ModelAndView mv = new ModelAndView();
        String boardNo = request.getParameter("boardNo"); //없으면 GET(create안함), 있으면 POST(create)

        String flag = bean.getBoardTitle();
        boardParam.put("boardNo", boardNo);

        Map<String, String> dsBoard = boardService.retrieveBoard(boardParam);

		if(flag == null){
			mv.addObject("dsBoard", dsBoard);
			mv.setViewName("/board/boardRegisterU");
		}else{
			boardService.updateBoard(bean);
			mv.setViewName("/work/board/retrieveBoard.do?boardNo=" + boardNo + "&fromUpdate=true");
		}
		return mv;
	}

}
