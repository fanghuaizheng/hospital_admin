package top.lsyweb.hosadm.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
@Configuration
public class ShiroConfig
{
	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
	{
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 添加Shiro内置过滤器，设置拦截规则
		Map<String, String> filterMap = new LinkedHashMap<>();
		// 公开页面
		filterMap.put("/login", "anon");
		filterMap.put("/error", "anon");
		filterMap.put("/views/toLogin.html", "anon");

		// 权限拦截规则
		filterMap.put("/assignBed", "perms[admin:assign]");
		filterMap.put("/removeBed", "perms[admin:assign]");
		filterMap.put("/getPatients", "perms[admin:assign]");
		filterMap.put("/getWards", "perms[admin:assign]");
		filterMap.put("/getData2_2", "perms[admin:assign]");  // wnz统计界面权限拦截
		filterMap.put("/getPatientsByInp", "perms[admin:assign]"); // zyj统计界面权限拦截
		filterMap.put("/views/assign/*", "perms[admin:assign]");

		filterMap.put("/addPatient", "perms[admin:record]");
		filterMap.put("/patientUpload", "perms[admin:record]");
		filterMap.put("/getData_2", "perms[admin:record]"); // wnz统计界面权限拦截
		filterMap.put("/views/record/*", "perms[admin:record]");


		// 认证拦截规则
		filterMap.put("/views/**", "authc");
		filterMap.put("/adminChange", "authc");
		filterMap.put("/adminInfo", "authc");
		filterMap.put("/adminUpload", "authc");
		filterMap.put("/adminChangePassword", "authc");
		filterMap.put("/department", "authc");
		filterMap.put("/getLog", "authc");

		filterMap.put("/logout", "logout");


		// 指定登录页面
		shiroFilterFactoryBean.setLoginUrl("/views/toLogin.html");
		// 指定出错页面
//		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		// 绑定过滤规则
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


		return shiroFilterFactoryBean;
	}

	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(AdminRealm adminRealm)
	{
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 关联realm
		defaultWebSecurityManager.setRealm(adminRealm);
		return defaultWebSecurityManager;
	}

	/**
	 * 创建Realm对象
	 */
	@Bean
	public AdminRealm getAdminRealm()
	{
		return new AdminRealm();
	}
}
