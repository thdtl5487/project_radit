package org.zerock.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.board.BoardService;
import org.zerock.code.CodeBean;
import org.zerock.code.CodeService;



@Controller
public class UserController {
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "boardService")
	private BoardService boardService;

	@Resource(name = "codeService")
	private CodeService codeService;

	@RequestMapping(value="/work/user/idCheck.do", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> idCheck(HttpServletRequest request){
		String loginId = request.getParameter("loginId");
		UserBean bean = userService.retrieveUser(loginId);
		String checkMsg = "<font color='green' size='3px;'><i class='fa fa-check'>사용할 수 있는 아이디입니다.</i></font>@true";

		if(bean != null){
			checkMsg = "<font color='red' size='3px;'><i class='fa fa-times'>이미 존재하는 아이디입니다.</i></font>@false";
		}

		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type","text/html;charset=UTF-8");
		ResponseEntity resultMsg = new ResponseEntity<String>(checkMsg, resHeader, HttpStatus.OK);
		return resultMsg;
	}

	@RequestMapping(value="/work/user/ajaxLoginCheck.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> ajaxLogincheck(HttpServletRequest request
											, @RequestParam(value="loginId", required=false) String loginId
											, @RequestParam(value="pw", required=false) String pw){
		Map<String, String> loginYnMap = new HashMap<String, String>();

		boolean check = false;
		check = userService.logincheck(loginId, pw);

		if(check){
			loginYnMap.put("loginYn", "success");
		}else{
			loginYnMap.put("loginYn", "fail");
		}

		return loginYnMap;
	}

	@RequestMapping(value="/work/user/login.do",method=RequestMethod.POST)
	public String login(@ModelAttribute UserBean bean, HttpServletRequest request){
		HttpSession session = request.getSession();

		UserBean userBean = userService.retrieveSessionInfo(bean.getU_Email());

		String u_profile_path = userBean.getU_profile_path();
		String u_Email = userBean.getU_Name();
//		String grade = userBean.getGrade();

//		session.setAttribute("userCode", userCode);
//		session.setAttribute("grade", grade);
		session.setAttribute("u_Email", u_Email);

		Map<String, String> boardParam = new HashMap<String, String>();

//		boardParam.put("userCode", userCode);

		userService.updateUserConnectedTime(boardParam);

		session.setMaxInactiveInterval(-1); //세션 무한대
		return "redirect:/work/board/goMain.do";
	}



	@RequestMapping(value="/work/user/logout.do")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("userCode");
		session.invalidate();
		return "redirect:/work/board/goMain.do";

	}

	@RequestMapping(value="/work/user/createUser.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView createUser(@ModelAttribute UserBean bean){
		ModelAndView mv = new ModelAndView();
        String id = bean.getU_Email(); //없으면 GET(create안함), 있으면 POST(create)
		Map<String, String> codeParam = new HashMap<String, String>();

		if(id == null){
			codeParam.put("commTyCd", "CODE0100");
			List<CodeBean> dsCode1 = codeService.retrieveCodeList(codeParam);
			codeParam.put("commTyCd", "CODE0101");
			List<CodeBean> dsCode2 = codeService.retrieveCodeList(codeParam);
			mv.addObject("dsCode1", dsCode1);
			mv.addObject("dsCode2", dsCode2);
			mv.setViewName("/intro/userC");
		}else{
			userService.createUser(bean);
			mv.setViewName("redirect:/work/board/goMain.do");
		}
		return mv;
	}

	@RequestMapping(value="/work/user/updateUser.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateUser(HttpServletRequest request, @ModelAttribute UserBean bean){
		HttpSession session = request.getSession();

		ModelAndView mv = new ModelAndView();
        String id = (String) session.getAttribute("id"); //없으면 GET(create안함), 있으면 POST(create)
        String flag = bean.getU_Name();
		Map<String, String> codeParam = new HashMap<String, String>();

		UserBean dsUser = userService.retrieveUser(id);

		if(flag == null){
			codeParam.put("commTyCd", "CODE0100");
			List<CodeBean> dsCode1 = codeService.retrieveCodeList(codeParam);
			codeParam.put("commTyCd", "CODE0101");
			List<CodeBean> dsCode2 = codeService.retrieveCodeList(codeParam);
			mv.addObject("dsCode1", dsCode1);
			mv.addObject("dsCode2", dsCode2);

			mv.addObject("dsUser", dsUser);
			mv.setViewName("/intro/userU");
		}else{
			String userCode = dsUser.getU_profile_path();
			bean.getU_profile_path();
			userService.updateUser(bean);
			mv.setViewName("redirect:/work/board/goMain.do");
		}
		return mv;
	}

	@RequestMapping(value="/work/user/deleteUser.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deleteUser(HttpServletRequest request){
		HttpSession session = request.getSession();

		ModelAndView mv = new ModelAndView();
        String id = (String) session.getAttribute("id"); //없으면 GET(create안함), 있으면 POST(create)

        Map<String, String> boardParam = new HashMap<String, String>();
        boardParam.put("loginId", id);

        boardService.deleteBoard2(boardParam);

        userService.deleteUser(id);

		mv.setViewName("redirect:/work/user/logout.do");

		return mv;
	}


}
