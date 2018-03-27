package controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.FastDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sist.msk.Action;
import com.sun.xml.internal.bind.v2.model.core.ID;


import member.memDAO;
import member.memVO;
import my.db.BoardDAO;
import my.db.BoardDBMybatis;
import member.MemDBMybatis;
import my.db.BoardVO;

public class Boardcontroller extends Action{
	
	//����ȭ��//
	public String index(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		return  "/view/index.jsp"; 
	}
	//���� ����ȭ��//
	public String admin(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		 return  "/view/admin.jsp";
	} 
	//ȸ����ġ//
	public String contact(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		 return  "/view/menu/contact.jsp";
	} 
	//�α���//
	public String login(HttpServletRequest request, HttpServletResponse response)  throws Throwable {
		HttpSession session = request.getSession();
		//jsp���� �Ѱ��� ���� �޴� �Ķ����
		String id=request.getParameter("login_id");
		String pass=request.getParameter("login_pwd");
		MemDBMybatis dao = MemDBMybatis.getInstance();
		//�α���üũ
		int pwcheck = dao.login(id, pass);
		String name = dao.getname(id);
		session.setAttribute("name", name);
		request.setAttribute("pwcheck", pwcheck);
		session.setAttribute("id", id);
		if(id.equals("admin") && pwcheck==1) { return "/view/admin.jsp"; }
		else
		return  "/view/menu/login.jsp"; 
	}
	
	//�α׾ƿ�//
	public String logout(HttpServletRequest request, HttpServletResponse response)  throws Throwable {
		HttpSession session = request.getSession();
		//���Ǿ��̵� �ΰ��� �ƴҶ��� �ҷ��´�.
		if(session.getAttribute("id") != null){
			session.invalidate();	//���� ����
		}
		return "/view/index.jsp"; 
	} 
	//ȸ������//
	public String join(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		memVO member = new memVO();
		member.setM_id(request.getParameter("join_id"));
		member.setM_pwd(request.getParameter("join_pwd"));
		member.setM_name(request.getParameter("join_name"));
		member.setM_birth(request.getParameter("join_birth"));
		member.setM_money(request.getParameter("join_money"));
		member.setM_level(request.getParameter("join_level"));
		member.setM_email(request.getParameter("join_email"));
		MemDBMybatis dao = MemDBMybatis.getInstance();
		int cnt = dao.insert(member);
		request.setAttribute("cnt", cnt);
		return  "/view/join/joinOk.jsp"; 
	}

	
	
	//����Ȩ//
	public String userView(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();   //���ǿ� ����� ���̵� ������ ȸ�� ������ �����´�
		MemDBMybatis dao = MemDBMybatis.getInstance();
		memVO vo = dao.myhomeView2(id);
		request.setAttribute("vo", vo);
		return "/view/user/userView.jsp";
	} 
	//����Ȩ_������������//
	public String userUpdate(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		try{
			HttpSession session = request.getSession();
			String id = session.getAttribute("id").toString();
			String num = request.getParameter("num");
			MemDBMybatis dao = MemDBMybatis.getInstance();
			memVO vo = dao.myhomeView(num);
			request.setAttribute("vo", vo);
		}catch(Exception e){ e.printStackTrace();}
		return  "/view/user/userUpdate.jsp"; 
	}
	//����Ȩ_���������Ϸ�//
	public String userUpdateP(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		MemDBMybatis dao = MemDBMybatis.getInstance();
		memVO member = new memVO();
		String id = (String)session.getAttribute("id");
		member.setM_num(Integer.parseInt(request.getParameter("m_num")));
		member.setM_pwd(request.getParameter("m_pwd"));
		member.setM_id(id);
		member.setM_name(request.getParameter("m_name"));
		member.setM_birth(request.getParameter("m_birth"));
		member.setM_email(request.getParameter("m_email"));
		member.setM_money(request.getParameter("m_money"));
		member.setM_level(request.getParameter("m_level"));
		
		int pwdcheck = dao.updateMember(member);
		request.setAttribute("pwdcheck", pwdcheck);
		return  "/view/user/userUpdateP.jsp"; 
	}
	//����Ȩ_ȸ��Ż����//
	public String userDelete(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String num = request.getParameter("num");
		try{
			MemDBMybatis dao = MemDBMybatis.getInstance();
			
			memVO vo = dao.myhomeView(num);
			request.setAttribute("vo", vo);
		}catch(Exception e){e.printStackTrace();}
		return  "/view/user/userDelete.jsp"; 
	} 
		
		
	//����Ȩ_ȸ��Ż��Ϸ�//
	public String userDeleteP(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String num = request.getParameter("m_num"); //deleteForm ���� �Ѿ�� ������
		try{
			String passwd = request.getParameter("m_pwd");
			MemDBMybatis dao = MemDBMybatis.getInstance();
			
			int passcheck = dao.deleteMember(num, passwd, id);
			request.setAttribute("passcheck", passcheck);
		}catch(Exception e){e.printStackTrace();}
		return  "/view/user/userDeleteP.jsp"; 
	} 
	
	
	//����_ȸ������
	public String adList(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		//�˻�ó��
		String keyField = request.getParameter("keyField");
		String keyWord = request.getParameter("keyWord");

		//������ó��
		int pageSize=10;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum == ""){
			pageNum = "1";}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage* pageSize;

		MemDBMybatis dao = MemDBMybatis.getInstance();
		List memList = null;
		int count = 0;
		int number = 0;
		count = dao.SelectCountMem(startRow, endRow, keyField, keyWord);
		if(count>0){
			memList = dao.memList(startRow, endRow, keyField, keyWord);
		int bottomLine=5;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		int startPage = 1+(currentPage-1)/bottomLine*bottomLine;
		int endPage = startPage+bottomLine-1;
		if(endPage>pageCount) endPage=pageCount;
		
		
		
		request.setAttribute("memList", memList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		} 
		request.setAttribute("count", count);
		return  "/view/admin/adList.jsp"; 
	}
		
	//����_ȸ�����//
	public String adJoin(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		return  "/view/admin/adJoin.jsp";  
	} 
	//����_ȸ����ϿϷ�//
	public String adJoinPro(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		memVO member = new memVO();
		member.setM_id(request.getParameter("ad_m_id"));
		member.setM_pwd(request.getParameter("ad_m_pwd"));
		member.setM_name(request.getParameter("ad_m_name"));
		member.setM_birth(request.getParameter("ad_m_birth"));
		member.setM_money(request.getParameter("ad_m_money"));
		member.setM_level(request.getParameter("ad_m_level"));
		member.setM_email(request.getParameter("ad_m_email"));
		MemDBMybatis dao = MemDBMybatis.getInstance();
		
		dao.insert(member);
		return  "/view/admin/adJoinPro.jsp"; 
	} 
	//����_ȸ������//
	public String adView(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		String num = request.getParameter("num");
		MemDBMybatis dao = MemDBMybatis.getInstance();
		
		memVO vo = dao.myhomeView(num);
		request.setAttribute("vo", vo);
		return  "/view/admin/adView.jsp";  
	} 
	//����_����������//
	public String adUpdate(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String num = request.getParameter("num");
		try{
			MemDBMybatis dao = MemDBMybatis.getInstance();
			
			memVO vo = dao.myhomeView(num);
			request.setAttribute("vo", vo);
		}catch(Exception e){e.printStackTrace();}
		return  "/view/admin/adUpdate.jsp";  
	} 
	//����_���������Ϸ�//
	public String adUpdateP(HttpServletRequest request, HttpServletResponse response)  throws Throwable {
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();   //���ǿ� ����� ���̵� ������ ȸ�� ������ �����´�
		MemDBMybatis dao = MemDBMybatis.getInstance();
		memVO member = new memVO();
		member.setM_num(Integer.parseInt(request.getParameter("m_num")));
		member.setM_id(id);
		member.setM_name(request.getParameter("m_name"));
		member.setM_birth(request.getParameter("m_birth"));
		member.setM_email(request.getParameter("m_email"));
		member.setM_level(request.getParameter("m_level"));
		member.setM_money(request.getParameter("m_money"));
		member.setAd_pwd(request.getParameter("ad_pwd"));
		
		int pwdcheck = dao.updateMember(member);
		request.setAttribute("pwdcheck", pwdcheck);
		return  "/view/user/userUpdateP.jsp"; 
	}
	//����_ȸ��Ż����//
	public String adDelete(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		String num = request.getParameter("num");
		try{
			MemDBMybatis dao = MemDBMybatis.getInstance();
			
			memVO vo = dao.myhomeView(num);
			request.setAttribute("vo", vo);
		}catch(Exception e){e.printStackTrace();}
		return  "/view/admin/adDelete.jsp"; 
	} 
	//����_ȸ��Ż��Ϸ�
	public String adDeleteP(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String num = request.getParameter("num"); //deleteForm ���� �Ѿ�� ������
		try{
			String passwd = request.getParameter("ad_pwd");
			MemDBMybatis dao = MemDBMybatis.getInstance();
			String id = (String)session.getAttribute("id"); 
			
			int passcheck = dao.deleteMember(num, passwd, id);
			request.setAttribute("passcheck", passcheck);
		}catch(Exception e){e.printStackTrace();}
		return  "/view/admin/adDeleteP.jsp"; 
	} 
		
		
		
	
	//����� ����
	public String writeForm(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		return  "/view/haru/writeForm.jsp"; 
	}
	//����� ����Ϸ�
	public String writePro(HttpServletRequest request, HttpServletResponse response)  throws Throwable {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id"); //����id�� ������ db�� �����Ŵ >> ������ �ۼ��� �۸� ���̰�
		int count = 0;
		BoardVO mainboard = new BoardVO();
		mainboard.setMain_writeday(request.getParameter("main_writeday"));
		mainboard.setMain_option(request.getParameter("main_option"));
		mainboard.setMain_account(request.getParameter("main_account"));
		mainboard.setMain_content(request.getParameter("main_content"));
		mainboard.setMain_price(request.getParameter("main_price"));
		
		BoardDBMybatis dao = BoardDBMybatis.getInstance();
		//����ξ���
		dao.haruinsert(mainboard, id);
		//�ѵ� �ݾ� üũ
		if("1".equals(mainboard.getMain_option())){
		count = dao.moneyCheck(id); 
		}
		request.setAttribute("count", count);
		request.setAttribute("id", id);
		return  "/view/haru/writePro.jsp";
	}
	
	//����� ���� ��
	public String updateForm(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDBMybatis dao = BoardDBMybatis.getInstance();
		
		BoardVO vo = dao.Contentview(num);
		request.setAttribute("vo", vo);
		return  "/view/haru/updateForm.jsp"; 		 
	} 
	
	//����� ���� �Ϸ�
	public String haruUpdatePro(HttpServletRequest request, HttpServletResponse response)  throws Throwable {
		BoardDBMybatis dao = BoardDBMybatis.getInstance();
		BoardVO vo = new BoardVO();
		
		vo.setMain_num(Integer.parseInt(request.getParameter("main_num")));
		vo.setMain_writeday(request.getParameter("main_writeday"));
		vo.setMain_content(request.getParameter("main_content"));
		vo.setMain_price(request.getParameter("main_price"));
		
		int check = dao.updateBoard(vo);
		request.setAttribute("check", check);
		return  "/view/haru/updatePro.jsp"; 
	}
	
	
	//����� ���
	public String writeList(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		
		BoardVO mainboard = new BoardVO();
		BoardVO mainboard2 = new BoardVO();
		
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		
		//�˻�ó��(Ű����)
		String key1 = request.getParameter("key1");
		String key2 = request.getParameter("key2");
		
		
		//��¥ �˻�
		String startDt = request.getParameter("main_writeday");
		String endDt = request.getParameter("main_writeday2");
		
		
		//������ó��
		int pageSize=10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum == ""){
			pageNum = "1";}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage* pageSize;
		
		BoardDBMybatis dao = BoardDBMybatis.getInstance();
		int count = 0;
		List mainList = null;
		
		count=dao.mainCount(id, key1, key2, startRow, endRow, startDt, endDt);
		if(count>0){
			
			mainList = dao.mainList(id, key1, key2, startRow, endRow, startDt, endDt);
			
			mainboard = dao.totPrice(id, key1, key2, startDt, endDt);
			
		int bottomLine=5;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		int startPage = 1+(currentPage-1)/bottomLine*bottomLine;
		int endPage = startPage+bottomLine-1;
		if(endPage>pageCount)endPage=pageCount;
		request.setAttribute("mainboard", mainboard);
		request.setAttribute("mainList", mainList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		}
		request.setAttribute("count", count);
		
		return  "/view/haru/writeList.jsp"; 
	}
	
	//����� �󼼺���
	public String content(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		BoardDBMybatis dao = BoardDBMybatis.getInstance();
		BoardVO vo = dao.Contentview(num);
		System.out.println(vo.getMain_account());
		request.setAttribute("vo", vo);
			
		return  "/view/haru/content.jsp"; 
	}
		
		
	//����� ����	
	public String deletePro(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		
		int num = Integer.parseInt(request.getParameter("num"));
		
			BoardDBMybatis dao = BoardDBMybatis.getInstance();
			int check = dao.deleteBoard(num);
			
			request.setAttribute("check", check);
		
		return  "/view/haru/deletePro.jsp";
	} 
		
		
		
	
	
	
	
	
	
	//��Ʈ
   public String chart(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
      return  "/view/haruChart/chart.jsp"; 
      } 
   
   public String chartView (HttpServletRequest request, HttpServletResponse response) throws Throwable { 
      
	   	String main_option = request.getParameter("main_option");
	   	String date1 = request.getParameter("date1");
	   	String date2 = request.getParameter("date2");
	   	HttpSession session = request.getSession();
		  
	   	BoardDBMybatis dao = BoardDBMybatis.getInstance();
		 String id = (String)session.getAttribute("id"); 
		  
		 if(main_option.equals("1")) {
		 int foodcnt = dao.foodCnt(id, main_option, date1, date2);
		 int carcnt = dao.carCnt(id, main_option, date1, date2);
		 int phoneCnt = dao.phoneCnt(id, main_option, date1, date2);
		 int culturalCnt = dao.culturalCnt(id, main_option, date1, date2);
		 int healthcnt = dao.healthCnt(id, main_option, date1, date2);
		 int articlecnt = dao.articleCnt(id, main_option, date1, date2);
		 int beautycnt = dao.beautyCnt(id, main_option, date1, date2);
		 request.setAttribute("foodcnt", foodcnt);
		 request.setAttribute("carcnt", carcnt);
		 request.setAttribute("phoneCnt", phoneCnt);
		 request.setAttribute("culturalCnt", culturalCnt);
		 request.setAttribute("healthcnt", healthcnt);
		 request.setAttribute("articlecnt", articlecnt);
		 request.setAttribute("beautycnt", beautycnt);
		 return  "/view/haruChart/chart.jsp"; }
		
		else{
		     int payCnt = dao.payCnt(id, main_option, date1, date2);
		     int interestCnt = dao.interestCnt(id, main_option, date1, date2);
		     int otherCnt = dao.otherCnt(id, main_option, date1, date2);
		     request.setAttribute("payCnt", payCnt);
		 request.setAttribute("interestCnt", interestCnt);
		 request.setAttribute("otherCnt", otherCnt);
		 return  "/view/haruChart/chart2.jsp"; 
		 }
}
	   
	   //�޷�
	   public String calendar (HttpServletRequest request, HttpServletResponse response) throws Throwable { 
		      return  "/view/haruCal/calendar.jsp"; 
		      }
}


