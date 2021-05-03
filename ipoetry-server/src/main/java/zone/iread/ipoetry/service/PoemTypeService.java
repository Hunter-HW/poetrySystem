package zone.iread.ipoetry.service;

import zone.iread.ipoetry.domain.Poem;
import zone.iread.ipoetry.domain.PoemType;
import zone.iread.ipoetry.domain.Poet;

import java.util.List;

public interface PoemTypeService {
    /**
     * 增加
     */
    public boolean insert(PoemType poemType);

    /**
     * 修改
     */
    public boolean update(PoemType poemType);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询对象
     */
    public PoemType selectByPrimaryKey(Integer id);

    /**
     * 查询所有诗词
     */
    public List<PoemType> allPoemType();

    /**
     * 根据诗词名字模糊查询
     */
    public List<PoemType> poemOfTitle(String title);

    /**
     * 类型总数
     * @return
     */
    int typeCount();


}
