package br.com.zup.proposta.compartilhado.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_proposta:read")
                        .antMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_proposta:write")
                        .antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_biometria:write")
                        .antMatchers(HttpMethod.POST, "/bloqueio/**").hasAuthority("SCOPE_bloqueio:write")
                        .antMatchers(HttpMethod.POST, "/avisoviagem/**").hasAuthority("SCOPE_avisoviagem:write")
                        .antMatchers(HttpMethod.POST, "/carteira/**").hasAuthority("SCOPE_carteira:write")
                        .antMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    }
}
