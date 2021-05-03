package zone.iread.ipoetry.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/*
管理员dao
 */
//@Repository
public interface AdminMapper {
    /**
     * 验证密码是否正确
     */
    @Select("SELECT COUNT(*) FROM admin WHERE admin.name = #{username} AND admin.password = #{password}")
    public int verifyPassword(@Param("username") String username, @Param("password")String password);

}
