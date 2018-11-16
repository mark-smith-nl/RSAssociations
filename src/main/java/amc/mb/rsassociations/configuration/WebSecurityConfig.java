package amc.mb.rsassociations.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String SUPERADMIN = "SUPERADMIN";

	public static final String ADMIN = "ADMIN";

	public WebSecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		/*InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuthentication = authenticationManagerBuilder.inMemoryAuthentication();

		//@formatter:off
				UserDetails superadmin = User.withDefaultPasswordEncoder()
						.username("superadmin")
						.password("superadmin")
						.roles("SUPERADMIN").build();
				UserDetails admin = User.withDefaultPasswordEncoder()
						.username("admin")
						.password("admin")
						.roles("ADMIN").build();
				//@formatter:on

		inMemoryAuthentication.withUser(superadmin).withUser(admin);*/

		authenticationManagerBuilder.authenticationProvider(new AuthenticationProvider() {

			@Override
			public boolean supports(Class<?> authentication) {
				return true;
			}

			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				Object credentials = authentication.getCredentials();

				if (!"Bokassa".equals(credentials)) {
					throw new BadCredentialsException("isse nie goe");
				}
				Set<GrantedAuthority> authorities = new HashSet<>();
				authorities.add(new SimpleGrantedAuthority("Osama"));
				authorities.add(new SimpleGrantedAuthority("Bokassa"));
				UserDetails user = new User(authentication.getName(), "", authorities);

				UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
				Map<String, Object> details = new HashMap<>();
				details.put("email", "m.smith@amc.nl");
				// details.putAll((Map<String, Object>) authentication.getDetails());
				result.setDetails(new MyUser("Mark", "Smith"));

				System.out.println(result.isAuthenticated());
				return result;
			}
		});

	}

	/*@Bean
	@Override
	public UserDetailsService userDetailsService() {
		//@formatter:off
		UserDetails superadmin = User.withDefaultPasswordEncoder()
				.username("superadmin")
				.password("superadmin")
				.roles("SUPERADMIN").build();
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("admin")
				.roles("ADMIN").build();
		//@formatter:on

		return new InMemoryUserDetailsManager(superadmin,admin);

	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//@formatter:off
		http
        .authorizeRequests()
        	.antMatchers("/login/switch_user", "/superadmin/**").hasRole(SUPERADMIN)
        	.antMatchers("/admin/**").hasRole(ADMIN)
        	.anyRequest().permitAll()
            .and()
        .formLogin()
        	.loginPage("/login")
        	.defaultSuccessUrl("/rsEmployees")
        	.permitAll()
        	.and()
        .logout()
        	.logoutUrl("/logout")
        	.logoutSuccessUrl("/login")
        	.deleteCookies("JSESSIONID")
        	.clearAuthentication(true)
        	.invalidateHttpSession(true)
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
        	.permitAll();
		//@formatter:on
	}

	public static class MyUser {
		private final String firstName;

		private final String lastName;

		public MyUser(String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		@Override
		public String toString() {
			return "MyUser [firstName=" + firstName + ", lastName=" + lastName + "]";
		}

	}
}
