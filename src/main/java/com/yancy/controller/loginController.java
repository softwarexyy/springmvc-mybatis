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
 * 控制器类
 * 
 * @author yancy
 *
 */
@Controller
@RequestMapping(value = "/user")
public class loginController {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView hello(User model) {
		System.out.println(" ============ SpringMVC-hello开始运行 =========== ");

		System.out.println(" ============ mybatis初始化sqlSession =========== ");
		SqlSession sqlSession = null;
		sqlSession = SqlSessionFactoryUtil.openSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		String username = model.getName();
		String password = model.getPasswd();
		System.out.println("----- 前端页面传入的数据： ----" + username + password);
		User user = userMapper.getUserByName(username);
		System.out.println("----- 从数据库读取的数据： ----" + user.getName() + user.getPasswd());
		sqlSession.close();// 关闭sqlSession
		// 验证登录密码是否正确
		if (password.equals(user.getPasswd())) {
			ModelAndView mv = new ModelAndView("homePage");	//返回到homePage页面
			System.out.println("======== homepage ========");
			return mv;
		} else {
			return new ModelAndView("redirect:/index.jsp");
		}
	}

	@RequestMapping("/hello2")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {
		ModelAndView mv = new ModelAndView("hello");
		// 为页面加上name属性值
		mv.addObject("name", name);
		System.out.println(" ============ SpringMVC-hello2运行成功 =========== ");
		return mv;
	}

}
