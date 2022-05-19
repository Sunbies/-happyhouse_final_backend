package snippet;

public class Snippet {
	#server.servlet.context-path=/
	server.port=80
	
	# #JSP Setting
	# spring.mvc.view.prefix=/WEB-INF/views/
	# spring.mvc.view.suffix=.jsp
	
	#DataBase Setting
	spring.config.import=classpath:db.properties
	
	#MyBatis Setting
	mybatis.type-aliases-
}

