package finance.dev.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@TypeInfo(name = "SecurityConfig", description = "스프링 시큐리티 설정 클래스")
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @MethodInfo(name = "filterChain", description = "스프링 시큐리티 필터 체인을 설정합니다.")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        (sessionManagement) ->
                                sessionManagement.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        request ->
                                request.dispatcherTypeMatchers(DispatcherType.FORWARD)
                                        .permitAll()
                                        .anyRequest()
                                        .permitAll()
                        // .requestMatchers().authenticated()
                        )
                .formLogin(
                        login ->
                                login.loginPage("/user/login")
                                        .usernameParameter("id")
                                        .passwordParameter("password")
                                        .defaultSuccessUrl("/", true)
                                        .permitAll())
                .logout(withDefaults());

        return httpSecurity.build();
    }

    @MethodInfo(name = "passwordEncoder", description = "비밀번호 암호화를 위한 PasswordEncoder 빈을 생성합니다.")
    @Bean
    public PasswordEncoder passwordEncoder() {
        int passwordEncoderStrength = 12;

        return new BCryptPasswordEncoder(passwordEncoderStrength);
    }
}
