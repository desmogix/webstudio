package wseds.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wseds.util.Misc;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ClientInterceptor implements HandlerInterceptor {
   
    // Called before request.
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        System.out.println(ClientInterceptor.class.getName() + ".preHandle() method called: " + request.getMethod() +  ": " + 
                           request.getRequestURI() + "?" + request.getQueryString() + " at " + Misc.getDateAndTimeAsString() + ".");
        
        // Perform any auditing here.
        
        return true;
    }

    // Called after request.
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        
        System.out.println(ClientInterceptor.class.getName() + ".postHehandle() method executing: " + request.getMethod() +  ": " +
                           request.getRequestURI() + "?" + request.getQueryString() + " at " + Misc.getDateAndTimeAsString() + ".");
        
         // Perform any auditing here.        
    }

    // Called after response renders the view.
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception e) throws Exception {
            
        System.out.println(ClientInterceptor.class.getName() + ".afterCompletion() method completed: " + request.getMethod() +  ": " + 
                           request.getRequestURI() + "?" + request.getQueryString() + " at " + Misc.getDateAndTimeAsString() + 
                           " ended with HTTP code: " + response.getStatus() + ".");
        
         // Perform any auditing here.
    }
}