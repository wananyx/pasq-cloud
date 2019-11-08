package com.pasq.provider.config;

import com.pasq.provider.model.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.HashMap;

/**
 * 认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${access_token.signingKey}")
    private String signingKey;

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();//允许表单形式的验证
    }

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，
     * 也可以把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用in-memory存储
                .withClient("clientId")
                .secret(bCryptPasswordEncoder.encode("secret"))
                .authorizedGrantTypes("authorization_code","password","refresh_token")
                .scopes("app"); // 允许的授权范围

    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore());
        endpoints.accessTokenConverter(accessTokenConverter());
        // 授权码模式下,code存储(原来使用的是jdbc数据库存储)
// 		endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource));
//        endpoints.authorizationCodeServices(redisAuthorizationCodeServices);
    }

    /**
     * jwt形式令牌存储
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 在JWT编码的令牌值和OAuth身份验证之间转换的帮助程序
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter(){
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                OAuth2AccessToken oAuth2AccessToken = super.enhance(accessToken, authentication);
                //将当前用户信息追加到登陆后返回数据里,如果不需要可以注释
                addLoginUserInfo(oAuth2AccessToken, authentication);
                return super.enhance(accessToken, authentication);
            }
        };
        DefaultAccessTokenConverter defaultAccessTokenConverter
                = (DefaultAccessTokenConverter) jwtAccessTokenConverter.getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailsService);
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        //这里务必设置一个,否则多台认证中心的话,一旦使用jwt方式,access_token将解析错误
        jwtAccessTokenConverter.setSigningKey(signingKey);
        return jwtAccessTokenConverter;
    }

    /**
     * 将当前用户信息追加到登陆后返回数据里
     * @param oAuth2AccessToken
     * @param authentication
     */
    private void addLoginUserInfo(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication authentication) {
        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) oAuth2AccessToken;
            Authentication userAuthentication = authentication.getUserAuthentication();
            Object principal = userAuthentication.getPrincipal();
            if (principal instanceof LoginUserDTO) {
                LoginUserDTO user = (LoginUserDTO) principal;
                HashMap<String, Object> map = new HashMap<>(defaultOAuth2AccessToken.getAdditionalInformation());
                map.put("userInfo", user);//追加当前用户
                defaultOAuth2AccessToken.setAdditionalInformation(map);
            }
        }
    }


}
