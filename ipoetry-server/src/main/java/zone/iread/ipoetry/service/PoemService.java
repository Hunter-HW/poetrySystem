package zone.iread.ipoetry.service;

import zone.iread.ipoetry.domain.Poem;
import zone.iread.ipoetry.domain.Poet;

import java.util.List;

public interface PoemService {
    /**
     * 增加
     */
    public boolean insert(Poem poem);

    /**
     * 修改
     */
    public boolean update(Poem poem);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询对象
     */
    public Poem selectByPrimaryKey(Integer id);

    /**
     * 查询所有诗词
     */
    public List<Poem> allPoem();

    /**
     * 根据诗词名字模询
     */
    public List<Poem> poemOfName(String poemName);

    /*根据诗人id查询*/
    public List<Poem> poemOfPoetId(Integer id);

    /**
     * 诗词总数
     * @return
     */
    int poemCount();
}
