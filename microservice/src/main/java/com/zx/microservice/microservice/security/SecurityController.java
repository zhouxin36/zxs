package com.zx.microservice.microservice.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhouxin
 * @date 2019/1/9
 */

@ConditionalOnProperty(name = "spring.securityController.enable",havingValue = "true")
@RestController
@RequestMapping("/security")
public class SecurityController {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private final static Logger logger = LoggerFactory.getLogger(SecurityController.class);

//    private final ReactiveClientRegistrationRepository reactiveClientRegistrationRepository;

//    private final OAuth2AuthorizedClientService authorizedClientService;

//    @Autowired
//    public SecurityController(ReactiveClientRegistrationRepository reactiveClientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService) {
//        this.reactiveClientRegistrationRepository = reactiveClientRegistrationRepository;
//        this.authorizedClientService = authorizedClientService;
//    }

    @PostMapping(value = "/say/{message}")
    public String say(@PathVariable String message){
        if(!StringUtils.isBlank(threadLocal.get())){
            logger.info("---------->{}",threadLocal.get());
        }else {
            threadLocal.set("thread:" + Thread.currentThread().getName());
        }
        logger.info("------;;---->thread:{}",Thread.currentThread().getName());
        return message;
    }


//    @RequestMapping("/googleRegistration")
//    public String index() {
//        Mono<ClientRegistration> google = this.reactiveClientRegistrationRepository.findByRegistrationId("google");
//        return "index";
//    }



//    @RequestMapping("/userinfo")
//    public String userinfo(OAuth2AuthenticationToken authentication) {
        // authentication.getAuthorizedClientRegistrationId() returns the
        // registrationId of the Client that was authorized during the oauth2Login() flow
//        OAuth2AuthorizedClient authorizedClient =
//                this.authorizedClientService.loadAuthorizedClient(
//                        authentication.getAuthorizedClientRegistrationId(),
//                        authentication.getName());
//
//        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
//        return "userinfo";
//    }
}
