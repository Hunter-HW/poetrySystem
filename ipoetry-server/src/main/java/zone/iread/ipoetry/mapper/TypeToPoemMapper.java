package zone.iread.ipoetry.mapper;

import org.springframework.stereotype.Repository;
import zone.iread.ipoetry.domain.TypeToPoem;

import java.util.List;

/**
 * 诗词对应类mapper
 */
@Repository
public interface TypeToPoemMapper {

    /**
     * 增加
     * @param typeToPoem
     * @return
     */
    public int insert(TypeToPoem typeToPoem);

    /**
     * 修改
     * @return
     */
    public int update(TypeToPoem typeToPoem);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(int id);

    /**
     * 查询所有
     * @return
     */
    public List<TypeToPoem> allTypeToPoem();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public TypeToPoem selectByPrimaryKey(int id);

    /**
     * 根据TypeId查找
     * @return
     */
    public List<TypeToPoem> typeToPoemOfTypeId(int id);

    /**
     * 根据诗词id和类型id做删除
     */
    public int deleteByPoemIdTypeId(int poemId,int typeId);

    /**
     * 根据诗词id删除
     */
    public int deleteByPoemId(int poemId);

}
