package $package$.$name;format="normalize"$.core.annotation;

import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import $package$.$name;format="normalize"$.utils.JwtUtil;

/**
 * AuthorizedResolver
 */
public class AuthorizedResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Authorized.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        var loginUserAnnotation = parameter.getParameterAnnotation(Authorized.class);
        if (loginUserAnnotation != null) {
            var token = webRequest.getHeader(loginUserAnnotation.header());
            return jwtUtil.verify(token).get();
        }

        return null;
    }

}
