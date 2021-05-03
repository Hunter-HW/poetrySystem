package zone.iread.ipoetry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.iread.ipoetry.domain.Poet;
import zone.iread.ipoetry.domain.TypeToPoem;
import zone.iread.ipoetry.mapper.TypeToPoemMapper;
import zone.iread.ipoetry.service.TypeToPoemService;

import java.util.List;

@Service
public class TypeToPoemServiceImpl implements TypeToPoemService {

    @Autowired
    TypeToPoemMapper typeToPoemMapper;
    /**
     * 增加
     *
     * @param typeToPoem
     * @return
     */
    @Override
    public boolean insert(TypeToPoem typeToPoem) {
        return typeToPoemMapper.insert(typeToPoem)>0;
    }

    /**
     * 修改
     *
     * @param typeToPoem
     * @return
     */
    @Override
    public boolean update(TypeToPoem typeToPoem) {
        return typeToPoemMapper.update(typeToPoem)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        return typeToPoemMapper.delete(id)>0;
    }

    /**
     * 根据诗词id和类型id删除
     *
     * @param poemId
     * @param typeId
     * @return
     */
    @Override
    public boolean deleteByPoemIdTypeId(int poemId, int typeId) {
        return typeToPoemMapper.deleteByPoemIdTypeId(poemId, typeId)>0;
    }

    /**
     * 根据诗词id删除
     *
     * @param poemId
     * @return
     */
    @Override
    public boolean deleteByPoemId(int poemId) {
        return typeToPoemMapper.deleteByPoemId(poemId)>0;
    }

    /**
     * 根据主键查询对象
     *
     * @param id
     */
    @Override
    public TypeToPoem selectByPrimaryKey(Integer id) {
        return typeToPoemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有诗词
     */
    @Override
    public List<TypeToPoem> allTypeToPoem() {
        return typeToPoemMapper.allTypeToPoem();
    }

    /**
     * 根据类型id查找
     *
     * @param id
     */
    @Override
    public List<TypeToPoem> typeToPoemOfTypeId(int id) {
        return typeToPoemMapper.typeToPoemOfTypeId(id);
    }
}
