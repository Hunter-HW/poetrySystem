package zone.iread.ipoetry.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import zone.iread.ipoetry.domain.Poet;

import java.util.List;

@Repository
public interface PoetMapper{

    /**
     * 增加
     */
    @Insert(" <script>" +
            "insert into poet(" +
            "<if test = \" name != null\">name,</if>" +
            "<if test = \" period != null\">period,</if>" +
            "<if test = \" introduction != null\">introduction,</if>" +
            "<if test = \" pic != null\">pic,</if>" +
            "<if test = \" sex != null\">sex</if>" +
            ") value(" +
            "<if test = \" name != null\">#{name},</if>" +
            "<if test = \" period != null\">#{period},</if>" +
            "<if test = \" introduction != null\">#{introduction},</if>" +
            "<if test = \" pic != null\">#{pic},</if>" +
            "<if test = \" sex != null\">#{sex}</if>" +
            ")" +
            "</script>")
    public int insert(Poet poet);

    /**
     * 修改
     */
    public int update(Poet poet);

    /**
     * 删除
     */
    @Delete("delete from poet where id=#{id}")
    public int delete(Integer id);

    /**
     * 根据主键查询对象
     */
    @Select("select * from poet where id=#{id}")
    public Poet selectByPrimaryKey(Integer id);

    /**
     * 查询所有诗人
     */
    @Select("SELECT * FROM poet")
    public List<Poet> allPoet();

    /**
     * 根据诗人名字模糊查询
     */
    public List<Poet> poetOfName(String name);

    /*根据朝代查询*/
    public List<Poet> poetOfPeriod(Integer period);

    /**
     * 诗人总数
     * @return
     */
    public int poetCount();
}
