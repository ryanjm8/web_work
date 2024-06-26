package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 *  특정 경로 요청에 대해서 클라이언트가 로그인을 한 클라이언트인지 확인 작업을 하는 필터 정의하기
 *  
 *  1. Filter 인터페이스 구현
 *  2. 추상메소드 오버라이트
 *  3. @WebFilter 어노테이션을 이용해서 필터 맵핑 
 */


@WebFilter({"/user/private/*"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//원래 type 으로 casting 하기 
		HttpServletRequest req=(HttpServletRequest)request;
		//자식 type 을 이용해서 HttpSession 객체의 참조값을 얻어낸다.
		HttpSession session=req.getSession();
		
		//session 영역에 id 라는 키값으로 저장된 값이 있는지 확인한다(로그인된 사용자인지 확인)
		String id=(String)session.getAttribute("id");
		//만일 없다면(로그인된 사용자가 아니라면)
		if(id == null){		
			//로그인 페이지로 리다일렉트 시키기
			String cPath=req.getContextPath();
			// ServletResponse type 을 HttpServletResponse type 으로 casting
			HttpServletResponse res=(HttpServletResponse)response;
			res.sendRedirect(cPath+"/user/loginform.jsp");
		}else {//로그인을 한 사용자라면 
			//관여하지 않고 요청의 흐름을 이어간다
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}