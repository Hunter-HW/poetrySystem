package zone.iread.ipoetry.service;

import org.apache.ibatis.annotations.Select;
import zone.iread.ipoetry.domain.Poet;

import java.util.List;

public interface PoetService {
    /**
     * 增加
     */
    public boolean insert(Poet poet);

    /**
     * 修改
     */
    public boolean update(Poet poet);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询对象
     */
    public Poet selectByPrimaryKey(Integer id);

    /**
     * 查询所有诗人
     */
    public List<Poet> allPoet();

    /**
     * 根据诗人名字模糊查询
     */
    public List<Poet> singerOfName(String name);

    /*根据朝代查询*/
    public List<Poet> singerOfPeriod(Integer period);

    /**
     * 诗人总数
     * @return
     */
    int poetCount();
}
