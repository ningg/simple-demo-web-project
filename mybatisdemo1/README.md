MyBatis使用梳理 

##mybatisdemo1

###创建数据库

在MySQL中，创建表格：

	CREATE TABLE `user` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `age` int(11) DEFAULT NULL,
	  `username` varchar(255) DEFAULT NULL,
	  `password` varchar(255) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


###pom.xml

添加如下依赖：

	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis</artifactId>
		<version>3.2.8</version>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>5.1.34</version>
	</dependency>

###配置文件

TODO：两个配置文件的详细含义？

MyBatis的配置文件mybatis-config.xml：

	<configuration>
		<typeAliases>
			<typeAlias type="top.ningg.mybatis.User" alias="User" />
		</typeAliases>
		<environments default="development">
			<environment id="development">
				<transactionManager type="JDBC" />
				<dataSource type="POOLED">
					<property name="driver" value="com.mysql.jdbc.Driver" />
					<property name="url" value="jdbc:mysql://168.7.2.167:3306/studentmgr?characterEncoding=utf-8" />
					<property name="username" value="root" />
					<property name="password" value="root" />
				</dataSource>
			</environment>
		</environments>
		<mappers>
			<mapper resource="config/mybatis/app/UserVO.sqlmap.xml"/>
		</mappers>
	</configuration>
	
UserVO.sqlmap.xml文件的配置：

	<mapper namespace="userTest">
		<select id="selectUser" parameterType="int" resultType="top.ningg.mybatis.User">
			select * from User where id = #{id}
		</select>
	</mapper>


关于Mapper XML文件，详解参考：[Mapper XML文件详解][Mapper XML文件详解]。




###User类

User.java类如下：

	package top.ningg.mybatis;

	public class User {
		private Integer id;
		private String username;
		private String password;
		private Integer age;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

	}


###测试入口类

MyBatisTest.java如下：

	package top.ningg.mybatis;

	import java.io.Reader;

	import org.apache.ibatis.io.Resources;
	import org.apache.ibatis.session.SqlSession;
	import org.apache.ibatis.session.SqlSessionFactory;
	import org.apache.ibatis.session.SqlSessionFactoryBuilder;

	public class MyBatisTest {

		public static void main(String[] args) throws Exception {
			String resource = "config/mybatis/conf/mybatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			User user = sqlSession.selectOne("userTest.selectUser", 2);
			System.out.println(user.getUsername());
			sqlSession.close();
		}
	}



参考：[MyBatis入门级Demo][MyBatis入门级Demo]












##参考来源

* [MyBatis 入门实例][MyBatis 入门实例]
* [一步步学MyBatis][一步步学MyBatis] *（是一个系列）*
* [MyBatis入门级Demo][MyBatis入门级Demo]




[MyBatis 入门实例]:			http://mybatis.github.io/mybatis-3/zh/getting-started.html
[MyBatis入门级Demo]:		http://www.cnblogs.com/kakag/p/3140278.html
[一步步学MyBatis]:			http://www.blogjava.net/davidjefiny/archive/2013/12.html
[Mapper XML文件详解]:		http://mybatis.github.io/mybatis-3/zh/sqlmap-xml.html

