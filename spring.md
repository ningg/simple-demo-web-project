
#Java Web with Spring、Hibernate、Myibatis


目标：快速搭建基本架构

基本思路：

* 使用Spring作为核心框架，来连接其他框架；
* 如何使用Spring搭建基本的web框架？参考资料从哪里获得？
	* Spring 官网介绍；无奈，[官网例子][Serving Web Content with Spring MVC]中使用了Spring Boot，暂时我只想使用最原始的Spring；
	* [官网参考文档][Spring Framework Reference Documentation]，其中有详尽的解释，但没有完整的例子；

##maven project：spring web

> 原文地址：[Spring MVC HelloWorld Using Maven in Eclipse][Spring MVC HelloWorld Using Maven in Eclipse]，其中对每一步骤有详尽的说明；

具体步骤：

* 创建Maven Project，选定webapp的archtype；
* 添加Spring相应组件的依赖；
* 添加Spring需要的beans：在上下文中登记注册bean、设置web页面的前端渲染等；





###git ignore的设置以及详细含义

几个疑问：

* 如何设置git ignore，让其对子目录生效；
* ignore中具体的设置，含义是什么？

下面.ignore中配置的含义？

	# Eclipse
	.classpath
	.project
	.settings/

	# Maven
	/target

上述几项的具体含义？即，对于Eclipse下的Maven project来说`.classpath`、`.project`、`.settings/`中的内容是什么？有什么用？为什么不提交到git仓库中？

###javax.servlet.http.HttpServlet找不到


报错：javax.servlet.http.HttpServlet 找不到。

解决办法，添加servlet api的依赖：

	<dependency>
	  <groupId>javax.servlet</groupId>
	  <artifactId>javax.servlet-api</artifactId>
	  <version>3.1.0</version>
	  <!-- <scope>provided</scope> -->
	</dependency>

###版本之间匹配关系

下面几个版本之间的匹配关系，是否兼容？

* JDK 1.6.x
* Spring 4.x
* Servlet 3.x
* JSP 2.x
* Tomcat 7.x


###Spring webmvc的依赖

使用Spring MVC来编写web项目，需要添加依赖，具体依赖如下：

	<properties>
	  <spring.version>4.1.6.RELEASE</spring.version>
	</properties>


    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>
	
简要说几点：

* Spring Framework，为Spring的一个工程，其中包含很多jar包，具体参考[官网][Spring Reference - Dependency Management]，官网对这些jar包的用途有个简要说明；
* spring-webmvc依赖与spring-web、spring-context、beans、core等，因此，只在pom.xml中指定spring-webmvc即可；


###web.xml文件的编写

示例样本：

	<?xml version="1.0" encoding="UTF-8" ?>

	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns="http://java.sun.com/xml/ns/javaee"
		xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
		 http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
		version="3.0">


		<display-name>Archetype Created Web Application</display-name>


		<context-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
		</context-param>

		<servlet>
			<servlet-name>dispatcher</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<load-on-startup>1</load-on-startup>
		</servlet>

		<servlet-mapping>
			<servlet-name>dispatcher</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>

		<listener>
			<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		</listener>
	</web-app>


说几点：

* 第一行，为标识行，指出此文件为XML文件，并且编码方式为`UTF-8`；
* 后面`web-app`元素为servlet规范约束，其中配置的`xmlns`为建议内容；
	* 上述`xmlns:xsi`通过后面URL唯一标识；
	* `xsi:shcemaLocation`指定不同xmlns对应的校验文件；
	* servlet规范对应的xmlns校验文件，官方建议是哪个？
	* 上述`version="3.0"`是哪个地方约束的？
	* 上述配置，除了`version`属性，其余配置都是固定的，需要记忆一下；
	* 上述配置，针对Servlet 3.x规范有效，则Tomcat 6.x，由于只支持到Servlet 2.5，则上述开发的web应用，无法在Tomcat 6.x上使用，必须使用Tomcat 7.x + 的版本。

###dispatcher-servlet.xml文件

文件样本如下：

	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="
			http://www.springframework.org/schema/beans
			http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
			http://www.springframework.org/schema/context
			http://www.springframework.org/schema/context/spring-context-3.0.xsd">

		<context:component-scan base-package="com.programcreek.helloworld.controller" />

		<bean
			class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix">
				<value>/WEB-INF/views/</value>
			</property>
			<property name="suffix">
				<value>.jsp</value>
			</property>
		</bean>
		
		
	</beans>
	

几点：

* 上述`schemaLocation`中指定的校验文件位置，哪里给出的参考？
* [Spring 官网参考文档][Spring Framework Reference Documentation]在`XML Schema-based configuration`中详细介绍了`beans schema`和`context schema`；
* 上述`context:component-scan`和设置前端渲染的配置，具体含义和作用是什么？`component-scan`能否进行模糊匹配？怎么设置？







##参考来源

* [Spring MVC HelloWorld Using Maven in Eclipse][Spring MVC HelloWorld Using Maven in Eclipse]






[Serving Web Content with Spring MVC]:			http://spring.io/guides/gs/serving-web-content/
[Spring Framework Reference Documentation]:		http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/
[Spring MVC HelloWorld Using Maven in Eclipse]:	http://www.programcreek.com/2014/02/spring-mvc-helloworld-using-maven-in-eclipse/
[Spring Reference - Dependency Management]:		http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#dependency-management
[Spring Framework Reference Documentation]:		http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/



