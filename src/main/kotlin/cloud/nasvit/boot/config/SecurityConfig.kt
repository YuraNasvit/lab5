package cloud.nasvit.boot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.Customizer

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf().disable()
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers("/editor/**").hasRole("EDITOR")
                    .requestMatchers("/viewer/**").hasRole("VIEWER")
                    .anyRequest().authenticated()
            }
            .httpBasic(Customizer.withDefaults())
            .build()

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetailsService = InMemoryUserDetailsManager()
        val editor = User.withUsername("admin")
            .password("admin")
            .roles("EDITOR")
            .build()
        val viewer = User.withUsername("guest")
            .password("guest")
            .roles("VIEWER")
            .build()
        userDetailsService.createUser(editor)
        userDetailsService.createUser(viewer)
        return userDetailsService
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return NoOpPasswordEncoder.getInstance()
    }
}