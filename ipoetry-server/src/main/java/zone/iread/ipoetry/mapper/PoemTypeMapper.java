package zone.iread.ipoetry.mapper;

import org.springframework.stereotype.Repository;
import zone.iread.ipoetry.domain.Poem;
import zone.iread.ipoetry.domain.PoemType;
import zone.iread.ipoetry.domain.Poet;

import java.util.List;

@Repository
public interface PoemTypeMapper {

    /**
     * 增加
     */
    public int insert(PoemType poemType);

    /**
     * 修改
     */
    public int update(PoemType poemType);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询对象
     */
    public PoemType selectByPrimaryKey(Integer id);

    /**
     * 查询所有类型
     */
    public List<PoemType> allPoemType();

    /**
     * 根据名字模糊查询
     */
    public List<PoemType> poemTypeOfTitle(String title);

    /**
     * 查询类型总数
     * @return
     */
    public int typeCount();


}
