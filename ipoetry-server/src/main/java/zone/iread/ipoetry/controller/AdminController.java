package zone.iread.ipoetry.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zone.iread.ipoetry.service.AdminService;
import zone.iread.ipoetry.utils.Consts;
import zone.iread.ipoetry.utils.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 判断是否登录成功
     */

    /*public Result loginStatus(HttpServletRequest request, HttpSession session){
        String name = request.getParameter("name");
        String passsword = request.getParameter("password");
        Result result = adminService.verifyPassword(name, passsword);
        return  result;
    }*/
    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String passsword = request.getParameter("password");
        boolean flag = adminService.verifyPassword(name,passsword);
        System.out.println(flag);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            session.setAttribute(Consts.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }


}
