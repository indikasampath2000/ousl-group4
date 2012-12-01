package ousl.group4.webapp.interceptor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ousl.group4.common.Constants;
import ousl.group4.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllPageInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        request.setAttribute(Constants.User_Obj, user);

        if (user != null && !user.isAdmin()) {
            request.getSession(false).setAttribute(Constants.USER_ID, user.getId());
        }

        return true;
    }


}
