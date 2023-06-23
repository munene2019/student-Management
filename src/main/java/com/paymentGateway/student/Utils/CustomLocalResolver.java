package com.paymentGateway.student.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
@Slf4j
public class CustomLocalResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    List<Locale> LOCALES = Arrays.asList(
            new Locale("en"),
            new Locale("fr"));

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String headerLang = request.getHeader("Accept-Language");

            log.info("locale :"+headerLang);
            return headerLang == null || headerLang.isEmpty()
                    ? Locale.getDefault()
                    : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
        }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("lang/messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setCacheSeconds(0);
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

}
