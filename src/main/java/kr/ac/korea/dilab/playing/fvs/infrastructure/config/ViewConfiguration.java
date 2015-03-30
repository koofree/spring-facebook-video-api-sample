package kr.ac.korea.dilab.playing.fvs.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Koo Lee on 3/26/2015.
 */
@Configuration
@EnableWebMvc
public class ViewConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/start").setViewName("start");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.getParameterType().equals(PagingParameters.class);
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                try {
                    String limit = webRequest.getParameter("limit");
                    String offset = webRequest.getParameter("offset");
                    String since = webRequest.getParameter("since");
                    String until = webRequest.getParameter("until");
                    String after = webRequest.getParameter("after");
                    String before = webRequest.getParameter("before");
                    if (!since.equals("null")) {
                        return new PagingParameters(Integer.parseInt(limit), null, Long.parseLong(since), null);
                    } else if (!until.equals("null")) {
                        return new PagingParameters(Integer.parseInt(limit), null, null, Long.parseLong(until));
                    } else if (!before.equals("null")) {
                        return new PagingParameters(Integer.parseInt(limit), null, null, null, null, before);
                    } else if (!after.equals("null")) {
                        return new PagingParameters(Integer.parseInt(limit), null, null, null, after, null);
                    } else {
                        return null;
                    }
                } catch (NumberFormatException e) {
                    return null;
                } catch (NullPointerException e) {
                    return null;
                }
            }
        });
    }
}
