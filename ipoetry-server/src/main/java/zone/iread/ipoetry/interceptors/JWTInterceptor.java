package zone.iread.ipoetry.interceptors;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import zone.iread.ipoetry.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 一般会将token信息放在请求头中，所以要先获取请求头中的token信息
        String token = request.getHeader("token");
        Map<String,Object> map = new HashMap<>();
        // 如果token不为空
        if(token != null){
            try{
                // 验证令牌
                JWTUtils.verify(token);
                // 验证成功放行请求
                return true;
            }catch (SignatureVerificationException e){
                e.printStackTrace();
                map.put("msg","无效签名");
            }catch (TokenExpiredException e){
                e.printStackTrace();
                map.put("msg","token过期");
            }catch (Exception e){
                e.printStackTrace();
                map.put("msg", "token无效");
            }
        }else {
            map.put("msg","未登录");
        }
        map.put("code",0);
        // 将map转为json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
