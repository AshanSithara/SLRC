package lk.rupavahini.PPUManagement.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private CustomLoginSuccessHandler sucessHandler;


	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
<<<<<<< HEAD

				//Employee restriction
				.antMatchers("/employee/**").not().hasAnyAuthority( "Editor","Casset library admin","Technical Officer","Camera man","Sponsor")
//				.antMatchers("/employee/**").not().hasAuthority( )
//				.antMatchers("/employee/**").not().hasAuthority( )
//				.antMatchers("/employee/**").not().hasAuthority( )
//				.antMatchers("/employee/**").not().hasAuthority( )

				//Sponsor restriction
				.antMatchers("/sponsor/**").not().hasAnyAuthority( "Engineer","Editor","Casset library admin","Technical Officer","Camera man","Sponsor")
				/*.antMatchers("/sponsor/**").not().hasAuthority( )
				.antMatchers("/sponsor/**").not().hasAuthority( )
				.antMatchers("/sponsor/**").not().hasAuthority( )
				.antMatchers("/sponsor/**").not().hasAuthority( )
				.antMatchers("/sponsor/**").not().hasAuthority( )*/

				//Programme restriction
				.antMatchers("/programme/**").not().hasAnyAuthority( "Technical Officer","Camera man","Sponsor")
				/*.antMatchers("/programme/**").not().hasAuthority( )
				.antMatchers("/programme/**").not().hasAuthority( )*/

				//Studio restriction
				.antMatchers("/studio/**").not().hasAnyAuthority( "Casset library admin","Camera man","Sponsor","Editor")
				/*.antMatchers("/studio/**").not().hasAuthority( )
				.antMatchers("/studio/**").not().hasAuthority( )
				.antMatchers("/studio/**").not().hasAuthority( )*/

				//PPU restriction
				.antMatchers("/ppu/**").not().hasAnyAuthority( "Casset library admin", "Camera man","Sponsor","Editor")
				/*.antMatchers("/ppu/**").not().hasAuthority()
				.antMatchers("/ppu/**").not().hasAuthority( )
				.antMatchers("/ppu/**").not().hasAuthority( )*/

				//Clibrary Restriction
				.antMatchers("/clibrary/**").not().hasAnyAuthority(  "Camera man","Sponsor")


				//Broadcast
				.antMatchers("/broadcast/**").not().hasAnyAuthority( "Casset library admin", "Camera man","Sponsor","Editor","Booking Officer")

				//Team
				.antMatchers("/team/**").not().hasAnyAuthority( "Booking Officer","Editor","Casset library admin","Technical Officer","Camera man","Sponsor")

				//Approve
				.antMatchers("/approve/**").not().hasAnyAuthority( "Engineer","Editor","Casset library admin","Technical Officer","Camera man","Sponsor","Producer")


=======
				// URLs matching for access rights

				.antMatchers("/report/**").permitAll()
>>>>>>> 4609734 (Initial commit)
				.antMatchers("/user/**").permitAll()
//				.antMatchers("/employee/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/home/**").hasAnyAuthority( "ADMIN_USER","SITE_USER")
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN_USER")
				.anyRequest().authenticated()
				.and()
				// form login
				.csrf().disable().formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.successHandler(sucessHandler)
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
				// logout
//				.logout().deleteCookies("JSESSIONID", "JWT").logoutSuccessUrl("/login") .clearAuthentication(true).invalidateHttpSession(true);
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/sign-up"))
				.logoutSuccessUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").and()
				.rememberMe()
				.alwaysRemember(true)
				.tokenValiditySeconds(10030*5).rememberMeCookieName("mouni")
				.key("somesecret")
<<<<<<< HEAD
				.and().csrf().disable()
				.exceptionHandling().accessDeniedPage("/error");
=======
				.and()
				.csrf().disable();
>>>>>>> 4609734 (Initial commit)
//				.deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);

//				.exceptionHandling()
//				.accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**","/dist/**","/css/**", "/js/**", "/images/**");
	}

}
