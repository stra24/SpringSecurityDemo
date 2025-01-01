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
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers("/register", "/login", "/delete", "/favicon.ico")
                .permitAll() // /register, login, deleteへのリクエストは認証なしで許可する
                .anyRequest().authenticated() // それ以外の全てのリクエストは認証が必要とする
        )
        .formLogin(formLogin ->  // フォームベースのログインを設定する
            formLogin
                .loginPage(
                    "/login" // ログインページのパス。GET /loginをログインページ表示とする。
                )
                .loginProcessingUrl(
                    "/login"
                    // ログイン処理のパス。POST /loginをログイン処理とする。これが指定されていなければデフォルトで/loginが指定されたことになる。
                )
                .usernameParameter(
                    "name"
                    // SpringSecurityデフォルトのユーザー名の項目名usernameをuserに変更。ログイン画面のinputタグのname属性値と紐づく。
                )
                .passwordParameter(
                    "pass"
                    // SpringSecurityデフォルトのパスワード名の項目名passwordをpassに変更。ログイン画面のinputタグのname属性値と紐づく。
                )
                .failureUrl(
                    "/login?failure=true" // ログイン失敗時に遷移するパス
                )
                .defaultSuccessUrl(
                    "/mypage", // ログイン成功時に遷移するパス。
                    true // trueを指定すると、第一引数に遷移してくれる。falseを指定すると、ログイン前のURL?continueにリダイレクトする。
                )
        )
        .logout(logout ->  // ログアウトを設定する
            logout
                .logoutSuccessUrl(
                    "/login?logout=true" // ログアウト成功時に遷移するパス
                )
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウトのリクエストパスを設定する
        )
        .exceptionHandling(configurer ->
            // 想定外のURLにアクセスされたら/loginにリダイレクトさせる。なお、認証が必要なリソースにアクセスしようとしたら、デフォルトで/loginにリダイレクトされる。
            configurer.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        );

    return http.build();
  }
}