package $package$.$name;format="normalize"$.core.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import $package$.$name;format="normalize"$.utils.JwtUtil;

/**
 * QueryAliasResolver
 */
public class QueryAliasResolver implements HandlerMethodArgumentResolver {

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

        var targetClass = parameter.getParameterType();
        var target = targetClass.getConstructor().newInstance();
        var wrapper = PropertyAccessorFactory.forBeanPropertyAccess(target);

        var fields = targetClass.getDeclaredFields();
        var superFields = targetClass.getSuperclass().getDeclaredFields();
        var allFields = new ArrayList<Field>() {
            {
                addAll(Arrays.asList(fields));
                addAll(Arrays.asList(superFields));
            }
        };

        for (Field field : allFields) {
            var aliasAnnotation = field.getAnnotation(QueryAlias.class);
            var paramName = field.getName();
            if (aliasAnnotation != null) {
                paramName = aliasAnnotation.value();
            }
            var value = webRequest.getParameter(paramName);
            if (value != null) {
                if (field.getType() == Optional.class) {
                    var type = field.getGenericType();

                    if (type instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) type;

                        for (var arg : pt.getActualTypeArguments()) {
                            if (arg.getTypeName() == "java.lang.Integer") {
                                wrapper.setPropertyValue(field.getName(), Optional.ofNullable(Integer.valueOf(value)));
                            }
                        }

                        continue;
                    }

                    wrapper.setPropertyValue(field.getName(), Optional.ofNullable(value));
                    continue;
                }
                wrapper.setPropertyValue(field.getName(), value);
                continue;
            }
        }

        return target;
    }

}
