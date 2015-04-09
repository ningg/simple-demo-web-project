# simple-demo-web-project
基本的产品原型设计、基本的工程开发代码



##最简单的spring web项目

（官网有没有介绍？）



##Maven管理搭建基本的spring web项目


思考：

* pom.xml中配置的依赖，Spring部分，来源是什么？官网有说明吗？
* HttpServlet.class类找不到，需要添加哪些依赖？
* Spring web与java web之间的关系？
* Spring web与java ee之间的关系？
* servlet、jsp的api，什么关系？是具体实现，还是规范？
* tomcat与servlet、jsp之间的关系？
* jetty、tomcat的基本原理以及区别；
	* tomcat的不同版本，支持不同的Java EE版本，对应不同的servlet 版本；
	* 开发java web时，注意使用的是哪个版本的servlet；
	* 两者的应用场景，debug模式下：如果java代码、jsp修改了，是否需要重启服务器？
* maven project使用archtype-webapp，如何当作web应用来运行和调试？
	* 参考：[使用Eclipse构建Maven的SpringMVC项目][使用Eclipse构建Maven的SpringMVC项目]，实际上，直接配置server，部署工程即可；

	
说明：

* 暂时不提git的配置和使用；
* Eclipse/MyEclipse下Git的使用；



思考：一个标准的servlet如何编写？去写一个demo，并且梳理一下整个过程；





###参考来源

* [使用spring4.0+maven构建超简单的web项目][使用spring4.0+maven构建超简单的web项目]
* [Spring framework reference][Spring framework reference]





##常见问题及思考


###学习工程开发的基本思路

先解决问题，整体上有个认识，然后，补充基础知识，融汇贯通。


###Maven 管理 web project

Maven 管理的 web project，在其他地方，作为Maven project导入时，会生成为web project吗？即，本质上，web project与普通的java project之间的差异在哪儿？配置文件中是否有体现，Maven的pom.xml文件中有体现吗？

新建一个普通的maven project，然后使用webapp的archetype。


###Maven管理 Spring webmvc项目，应该添加哪些依赖？

具体依赖如下：

	<properties>
	  <spring.version>4.1.6.RELEASE</spring.version>
	</properties>
	...
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>


思考：上述依赖的指定地点在哪？官网有没有参考来源。正常情况下spring-context、spring-web、spring-webmvc之间已经有了一个相互依赖关系，例如，spring-webmvc依赖于spring-web，spring-web依赖于spring-context，即，通过maven在依赖中添加spring-webmvc即可将上述spring-context、spring-web、spring-webmvc添加到编译路径中，这个需要对Spring有深入理解、梳理，才能有这样的认识。


###servlet api

如果报错：javax.servlet.http.HttpServlet 找不到。

解决办法：

	<dependency>
	  <groupId>javax.servlet</groupId>
	  <artifactId>javax.servlet-api</artifactId>
	  <version>3.1.0</version>
	  <!-- <scope>provided</scope> -->
	</dependency>

疑问：是否一定要使用`<scope>provided</scope>`这一属性？

* 使用这一属性时，无法利用Eclipse自带的快捷键，来实现class自动补充为`HttpServlet`；
* 不使用这一属性，有没有什么影响？因为其他地方发现都是用这个属性的；

详情参考：

* [The superclass “javax.servlet.http.HttpServlet” was not found on the Java Build Path 1]
* [The superclass “javax.servlet.http.HttpServlet” was not found on the Java Build Path 2]









[使用spring4.0+maven构建超简单的web项目]:		http://www.it165.net/pro/html/201403/10049.html
[Spring framework reference]:					http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/
[Spring MVC HelloWorld Using Maven in Eclipse]:	http://www.programcreek.com/2014/02/spring-mvc-helloworld-using-maven-in-eclipse/
[The superclass “javax.servlet.http.HttpServlet” was not found on the Java Build Path 1]:		http://stackoverflow.com/questions/22756153/the-superclass-javax-servlet-http-httpservlet-was-not-found-on-the-java-build
[The superclass “javax.servlet.http.HttpServlet” was not found on the Java Build Path 2]:	http://stackoverflow.com/questions/26936848/the-superclass-javax-servlet-http-httpservlet-was-not-found-on-the-java-build
[使用Eclipse构建Maven的SpringMVC项目]:			http://limingnihao.iteye.com/blog/830409


