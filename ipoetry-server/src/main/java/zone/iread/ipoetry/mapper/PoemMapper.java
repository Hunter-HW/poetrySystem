package zone.iread.ipoetry.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import zone.iread.ipoetry.domain.Poem;
import zone.iread.ipoetry.domain.Poet;

import java.util.List;

/**
 * 诗词mapper
 */
@Repository
public interface PoemMapper {
    /**
     * 增加
     */
    public int insert(Poem poem);

    /**
     * 修改
     */
    public int update(Poem poem);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询对象
     */
    public Poem selectByPrimaryKey(Integer id);

    /**
     * 查询所有诗词
     */
    public List<Poem> allPoem();

    /**
     * 根据诗词名字精确查询
     */
    public List<Poem> poemOfName(String poemName);

//    // 根据诗词名字精确查
//    public Poem poemOfPoemName(String poemName);
    /*根据作者id查询*/
    public List<Poem> poemOfPoetId(Integer id);

    /**
     * 获取诗词的总数量
     * @return
     */
    public int poemCount();
}
