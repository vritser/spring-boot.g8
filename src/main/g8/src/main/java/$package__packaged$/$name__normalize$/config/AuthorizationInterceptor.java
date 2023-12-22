package $package$.$name;format="normalize"$.config;

import io.vavr.control.Try;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import $package$.$name;format="normalize"$.core.annotation.PassAuth;
import $package$.$name;format="normalize"$.core.model.Resp;
import $package$.$name;format="normalize"$.core.model.ErrorCode;
import $package$.$name;format="normalize"$.utils.JwtUtil;
import $package$.$name;format="normalize"$.utils.JsonUtil;

/**
 * AuthorizationInterceptor
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JsonUtil jsonUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        if (handler instanceof HandlerMethod) {

            var method = ((HandlerMethod) handler);
            var passAuthAnnotation = method.getMethodAnnotation(PassAuth.class);
            if (passAuthAnnotation == null) {
                var token = request.getHeader("Authorization");

                var isAdminPath = request.getRequestURI().startsWith("/api/v1/admin");
                var result = jwtUtil.verify(token)
                    .filterOrElse(user -> (user.getUt() == 1 && !isAdminPath) || (user.getUt() == 2 && isAdminPath),
                                  __   -> ErrorCode.PermissionDenied);

                if (result.isLeft()) {
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json; charset=utf-8");
                    Try.of(response::getWriter)
                        .flatMap(out ->
                            jsonUtil.stringify(Resp.fail(result.getLeft()))
                                .andThen(out::write)
                        );
                    return false;
                }
            }

        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
