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
		System.out.println(" ============ SpringMVC-hello已成功运行 =========== ");

		System.out.println(" ============ 开始初始化mybatis： =========== ");
		SqlSession sqlSession = null;
		sqlSession = SqlSessionFactoryUtil.openSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		String username = model.getName();
		String password = model.getPasswd();
		System.out.println("----- 传入的参数 ----" + username + password);
		User user = userMapper.getUserByName(username);
		System.out.println("----- 查询的密码 ----" + user.getName() + user.getPasswd());
		sqlSession.close();// 关闭sqlSession
		// 验证密码是否正确
		if (password.equals(user.getPasswd())) {
			ModelAndView mv = new ModelAndView("homePage");// 指定视图
			System.out.println("======== homepage ========");
			return mv;
		} else {
			return new ModelAndView("redirect:/index.jsp");
		}
	}

	@RequestMapping("/hello2")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {
		ModelAndView mv = new ModelAndView("hello");// 指定视图
		// 向视图中添加所要展示或使用的内容，将在页面中使用
		mv.addObject("name", name);
		System.out.println(" ============ SpringMVC-hello2已成功运行 =========== ");
		return mv;
	}

}
