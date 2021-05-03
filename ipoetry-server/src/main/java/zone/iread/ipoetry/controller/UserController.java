package zone.iread.ipoetry.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zone.iread.ipoetry.domain.Poet;
import zone.iread.ipoetry.domain.User;
import zone.iread.ipoetry.service.UserService;
import zone.iread.ipoetry.utils.Consts;
import zone.iread.ipoetry.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/user")
@Api(value = "用户接口")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 添加一个新用户
     * @param request
     * @return
     */
    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String introduction = request.getParameter("introduction");
        String avator = request.getParameter("avator");
        // 先对生日数据进行处理，将其转化为date类型
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date();
        try {
            birthday = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到对象中
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(Integer.parseInt(sex));
        user.setPhoneNum(phoneNum);
        user.setEmail(email);
        user.setBirth(birthday);
        user.setIntroduction(introduction);
        user.setAvator(avator);

        boolean flag = userService.insert(user);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"注册成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"注册失败");
        }
        return jsonObject;
    }

    /**
     * 修改用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String introduction = request.getParameter("introduction");
        // 先对生日数据进行处理，将其转化为date类型
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date();
        try {
            birthday = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到对象中
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(Integer.parseInt(sex));
        user.setPhoneNum(phoneNum);
        user.setEmail(email);
        user.setBirth(birthday);
        user.setIntroduction(introduction);

        boolean flag = userService.update(user);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
        }
        return jsonObject;
    }

    /**
     * 根据id查询用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    public Object selectById(HttpServletRequest request){
        String id = request.getParameter("id");
        return userService.selectById(Integer.parseInt(id));
    }

    /**
     * 根据id删除用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        String id = request.getParameter("id");
        return userService.delete(Integer.parseInt(id));
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/allUser")
    public Object allUser(){
        return userService.allUser();
    }

    /**
     * 更新用户头像
     */
    @RequestMapping(value = "/updateUserAvator",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间的毫秒加上文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"userPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库的是相对地址
        String storeAvatorPath = "/img/userPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setId(id);
        user.setAvator(storeAvatorPath);
        boolean flag = userService.update(user);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"上传成功");
            jsonObject.put("pic",storeAvatorPath);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
        }
        return jsonObject;
    }

    @RequestMapping("/userCount")
    public Object userCount(){
        JSONObject jsonObject = new JSONObject();
        int count = userService.userCount();
        jsonObject.put("data", count);
        return jsonObject;
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public Object userLogin(HttpServletRequest request){
        String phone = request.getParameter("mobile");
        String password = request.getParameter("password");

        User user = new User();
        user.setPhoneNum(phone);
        user.setPassword(password);

        JSONObject jsonObject = userService.userLogin(user);
        return jsonObject;
    }

}
