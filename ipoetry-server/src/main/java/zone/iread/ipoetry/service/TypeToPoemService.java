package zone.iread.ipoetry.service;


import zone.iread.ipoetry.domain.Poet;
import zone.iread.ipoetry.domain.TypeToPoem;

import java.util.List;

public interface TypeToPoemService {

    /**
     * 增加
     * @param typeToPoem
     * @return
     */
    boolean insert(TypeToPoem typeToPoem);

    /**
     * 修改
     * @param typeToPoem
     * @return
     */
    boolean update(TypeToPoem typeToPoem);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 根据诗词id和类型id删除
     * @param poemId
     * @param typeId
     * @return
     */
    boolean deleteByPoemIdTypeId(int poemId, int typeId);

    /**
     * 根据诗词id删除
     * @param poemId
     * @return
     */
    boolean deleteByPoemId(int poemId);
    /**
     * 根据主键查询对象
     */
    public TypeToPoem selectByPrimaryKey(Integer id);

    /**
     * 查询所有诗词
     */
    public List<TypeToPoem> allTypeToPoem();

    /**
     * 根据类型id查找
     */
    public List<TypeToPoem> typeToPoemOfTypeId(int id);

}
