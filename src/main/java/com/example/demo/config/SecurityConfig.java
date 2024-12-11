package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(configurer ->
                        // 想定外のURLにアクセスされたら/loginにリダイレクトさせる。なお、認証が必要なリソースにアクセスしようとしたら、デフォルトで/loginにリダイレクトされる。
                        configurer.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                .authorizeHttpRequests(authorize ->
                        authorize
                             .requestMatchers("/register").permitAll() // /registerへのリクエストは認証なしで許可する
                            .anyRequest().authenticated() // それ以外の全てのリクエストは認証が必要とする
                )
                .formLogin(formLogin ->  // フォームベースのログインを設定する
                        formLogin
                            .loginPage("/login") // ログインページのURL。未認証ユーザーからのリクエストはGet /loginに飛ばす。POST /loginをログイン処理とする。
                            .usernameParameter("name") // SpringSecurityデフォルトのユーザー名usernameをuserに変更。
                            .passwordParameter("pass") // SpringSecurityデフォルトのパスワード名passwordをpassに変更。
                            .permitAll() // ログインページは認証なしで許可する
                            .failureUrl("/login?failure") // ログイン失敗時に遷移するURL
                            .defaultSuccessUrl("/mypage", true) // ログイン成功時に遷移するURL, 第二引数をtrueにすると、ログイン前のURL?continueにリダイレクトせずに第一引数に遷移してくれる。
                )
                .logout(logout ->  // ログアウトを設定する
                        logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウトのリクエストURLを設定する
                );

        return http.build();
    }
}