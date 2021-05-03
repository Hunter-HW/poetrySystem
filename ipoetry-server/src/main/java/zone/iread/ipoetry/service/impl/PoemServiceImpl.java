package zone.iread.ipoetry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.iread.ipoetry.domain.Poem;
import zone.iread.ipoetry.domain.Poet;
import zone.iread.ipoetry.mapper.PoemMapper;
import zone.iread.ipoetry.service.PoemService;

import java.util.List;

@Service
public class PoemServiceImpl implements PoemService {

    @Autowired
    private PoemMapper poemMapper;
    /**
     * 增加
     *
     * @param poem
     */
    @Override
    public boolean insert(Poem poem) {
        return poemMapper.insert(poem)>0;
    }

    /**
     * 修改
     *
     * @param poem
     */
    @Override
    public boolean update(Poem poem) {
        return poemMapper.update(poem)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return poemMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象
     *
     * @param id
     */
    @Override
    public Poem selectByPrimaryKey(Integer id) {
        return poemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有诗词
     */
    @Override
    public List<Poem> allPoem() {
        return poemMapper.allPoem();
    }

    /**
     * 根据诗词名字查询
     *
     * @param poemName
     */
    @Override
    public List<Poem> poemOfName(String poemName) {
        return poemMapper.poemOfName(poemName);
    }


    @Override
    public List<Poem> poemOfPoetId(Integer id) {
        return poemMapper.poemOfPoetId(id);
    }

    /**
     * 诗词总数
     *
     * @return
     */
    @Override
    public int poemCount() {
        return poemMapper.poemCount();
    }
}
