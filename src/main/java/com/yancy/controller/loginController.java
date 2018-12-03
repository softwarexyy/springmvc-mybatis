package com.yancy.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yancy.entity.User;
import com.yancy.mapper.UserMapper;
import com.yancy.util.SqlSessionFactoryUtil;

/**
 * ��������
 * 
 * @author yancy
 *
 */
@Controller
@RequestMapping(value = "/user")
public class loginController {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView hello(User model) {
		System.out.println(" ============ SpringMVC-hello�ѳɹ����� =========== ");

		System.out.println(" ============ ��ʼ��ʼ��mybatis�� =========== ");
		SqlSession sqlSession = null;
		sqlSession = SqlSessionFactoryUtil.openSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		String username = model.getName();
		String password = model.getPasswd();
		System.out.println("----- ����Ĳ��� ----" + username + password);
		User user = userMapper.getUserByName(username);
		System.out.println("----- ��ѯ������ ----" + user.getName() + user.getPasswd());
		sqlSession.close();// �ر�sqlSession
		// ��֤�����Ƿ���ȷ
		if (password.equals(user.getPasswd())) {
			ModelAndView mv = new ModelAndView("homePage");// ָ����ͼ
			System.out.println("======== homepage ========");
			return mv;
		} else {
			return new ModelAndView("redirect:/index.jsp");
		}
	}

	@RequestMapping("/hello2")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {
		ModelAndView mv = new ModelAndView("hello");// ָ����ͼ
		// ����ͼ�������Ҫչʾ��ʹ�õ����ݣ�����ҳ����ʹ��
		mv.addObject("name", name);
		System.out.println(" ============ SpringMVC-hello2�ѳɹ����� =========== ");
		return mv;
	}

}
