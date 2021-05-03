package zone.iread.ipoetry.mapper;

import org.springframework.stereotype.Repository;
import zone.iread.ipoetry.domain.User;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 增加用户
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 修改
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 所有用户
     * @return
     */
    List<User> allUser();

    /**
     * 用户总数
     * @return
     */
    int userCount();

    /**
     * 登录时或注册时判断该手机号码是否存在
     * @param phone
     * @return
     */
    int phoneIsExist(String phone);
    /**
     * 用户登录
     * @return
     */
    User userLogin(User user);
}
