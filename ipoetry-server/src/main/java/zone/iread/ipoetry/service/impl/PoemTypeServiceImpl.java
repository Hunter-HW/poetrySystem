package zone.iread.ipoetry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.iread.ipoetry.domain.PoemType;
import zone.iread.ipoetry.mapper.PoemTypeMapper;
import zone.iread.ipoetry.service.PoemTypeService;

import java.util.List;

@Service
public class PoemTypeServiceImpl implements PoemTypeService {
    @Autowired
    PoemTypeMapper poemTypeMapper;
    /**
     * 增加
     *
     * @param poemType
     */
    @Override
    public boolean insert(PoemType poemType) {
        return poemTypeMapper.insert(poemType)>0;
    }

    /**
     * 修改
     *
     * @param poemType
     */
    @Override
    public boolean update(PoemType poemType) {
        return poemTypeMapper.update(poemType)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return poemTypeMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象
     *
     * @param id
     */
    @Override
    public PoemType selectByPrimaryKey(Integer id) {
        return poemTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有诗词
     */
    @Override
    public List<PoemType> allPoemType() {
        return poemTypeMapper.allPoemType();
    }

    /**
     * 根据诗词名字模糊查询
     *
     * @param title
     */
    @Override
    public List<PoemType> poemOfTitle(String title) {
        return poemTypeMapper.poemTypeOfTitle(title);
    }

    /**
     * 类型总数
     *
     * @return
     */
    @Override
    public int typeCount() {
        return poemTypeMapper.typeCount();
    }
}
