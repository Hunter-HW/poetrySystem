package zone.iread.ipoetry.service;

import com.alibaba.fastjson.JSONObject;
import zone.iread.ipoetry.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 增加一个用户
     * @param user
     * @return
     */
    public boolean insert(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 修改
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 根据用户id查找
     * @param id
     * @return
     */
    public User selectById(int id);

    /**
     * 所有用户
     * @return
     */
    public List<User> allUser();

    /**
     * 用户总数
     * @return
     */
    int userCount();

    /**
     * 手机号是否存在
     * @param phone
     * @return
     */
    boolean phoneIsExist(String phone);

    /**
     * 用户登录
     * @param user
     * @return
     */
    JSONObject userLogin(User user);
}
