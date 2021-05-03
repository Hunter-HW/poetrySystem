package zone.iread.ipoetry.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.iread.ipoetry.domain.User;
import zone.iread.ipoetry.mapper.UserMapper;
import zone.iread.ipoetry.service.UserService;
import zone.iread.ipoetry.utils.Consts;
import zone.iread.ipoetry.utils.JWTUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 增加一个用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean insert(User user) {
        return userMapper.insert(user)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        return userMapper.delete(id)>0;
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @Override
    public boolean update(User user) {
        return userMapper.update(user)>0;
    }

    /**
     * 根据用户id查找
     *
     * @param id
     * @return
     */
    @Override
    public User selectById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 所有用户
     *
     * @return
     */
    @Override
    public List<User> allUser() {
        return userMapper.allUser();
    }

    /**
     * 用户总数
     *
     * @return
     */
    @Override
    public int userCount() {
        return userMapper.userCount();
    }

    /**
     * 手机号是否存在
     *
     * @param phone
     * @return
     */
    @Override
    public boolean phoneIsExist(String phone) {
        return userMapper.phoneIsExist(phone)>0;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public JSONObject userLogin(User user) {
        JSONObject jsonObject = new JSONObject();
        int i = userMapper.phoneIsExist(user.getPhoneNum());
        if(i>0){// 说明手机号已存在
            User userInfo = userMapper.userLogin(user);
            if(userInfo != null){
                Map<String,String> payload = new HashMap<>();
                payload.put("userID", userInfo.getId()+"");
                payload.put("username",userInfo.getUsername());
                //生成jwt令牌
                String token = JWTUtils.getToken(payload);
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"登录成功");
                jsonObject.put(Consts.TOKEN,token);
            }else {
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"登录失败，密码错误");
            }
        }else {//手机号不存在
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"该手机号不存在，请注册");
        }
        return jsonObject;
    }
}
