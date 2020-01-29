# security-demo
A simple demo project for Spring Security
Spring security is a framework that provides authentication and authorization to java applications.
Authentication is used to confirm the identity of the user and authorization is about the privileges and rights of the user and what the user has access to.
Here we will write a simple Rest Controller a Greeting Service which wishes you good morning when you key in localhost:7000/morning. We will see how it functions with and without security dependencies.
We will start by creating a spring boot project with the following dependencies.
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
Now we will create a simple rest controller that says Good Morning
@RestController
public class GreetingsController {

@GetMapping("/morning")
public String getGreetings() {
return "Good Morning";
}
}
Let us update the application.properties file 
server.port=7000
Now if you run the application you and key in localhost:7000/morning, you will be presented with a login screen.

When you run the application there will be a security password generated in the console. You can copy it and paste it. The default user name is "user" and the password you can paste in the copied security password(from the console).
This will take you to page that says Good Morning.
Now if you comment out the security dependency and run the application you will be taken directly to the page that wishes you "Good Morning". 
So this gives you a feeler of how spring security can help you secure your application.
Now Let us override the default security User Name and Password.
One way of doing this is by creating the application.properties file as below.
server.port=7000
spring.security.user.name=hello
spring.security.user.password=world
Now you can run the application. Make sure you uncomment the security dependencies in the POM file.
You will be taken to the same login screen as above and you can key in username as "hello" and password as "world". You will be now greeted with "Good Morning".
Now another way of doing this is by using Java code. Ley us comment the application.properties file as below.
server.port=7000
#spring.security.user.name=hello
#spring.security.user.password=world
Now let us write the code. This will require you to Autowire a password encoder. I am using the Bcrypt password encoder. When we try to run the application by @Autowire password encoder. It will give you an error. We should define a bean for the password encoder. You can also use the NoOpPasswordEncoder which is deprecated. Have a look at the code below.
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
@Autowired
private PasswordEncoder passwordEncoder;

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication()
.withUser("user").
//                password("user").
//                roles("USER");
password(passwordEncoder.encode( "user")).roles("USER");
}
@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}

When using the NoOpPasswordEncoder you don't require an encoder for the password.
Now by running this application you will be taken to the same login screen where you can key in username as "user" and password as "user". You will be greeted with "Good Morning".
You can find the full source code at https://github.com/gudpick/security-demo
You can find the video tutorial at https://youtu.be/e-9rPnlHWL8
