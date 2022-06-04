package capstone4.Cosi.security;

import capstone4.Cosi.servieces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationFailureHandler customFailureHandler;

    @Autowired
    public WebSecurityConfiguration(MemberService memberService,
                                    BCryptPasswordEncoder bCryptPasswordEncoder,
                                    AuthenticationFailureHandler customFailureHandler) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customFailureHandler = customFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/login", "/registration/**").permitAll()
                    .antMatchers("/market/**").hasRole("User")
                    .antMatchers("members/**").hasRole("User")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/market")
                .failureHandler(customFailureHandler);
//                .and()
//                    .logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(memberService);
        return provider;
    }
}
