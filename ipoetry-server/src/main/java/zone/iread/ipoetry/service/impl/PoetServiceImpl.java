package zone.iread.ipoetry.service.impl;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import zone.iread.ipoetry.domain.Poet;
import zone.iread.ipoetry.mapper.PoetMapper;
import zone.iread.ipoetry.service.PoetService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PoetServiceImpl implements PoetService {

    @Resource
    PoetMapper poetMapper;

    /**
     * 增加
     *
     * @param poet
     */
    @Override
    public boolean insert(Poet poet) {
        return poetMapper.insert(poet)>0;
    }

    /**
     * 修改
     *
     * @param poet
     */
    @Override
    public boolean update(Poet poet) {
        return poetMapper.update(poet)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return poetMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象
     *
     * @param id
     */
    @Override
    public Poet selectByPrimaryKey(Integer id) {
        return null;
    }

    /**
     * 查询所有诗人
     */
    @Override
    public List<Poet> allPoet() {
        return poetMapper.allPoet();
    }

    /**
     * 根据诗人名字模糊查询
     *
     * @param name
     */
    @Override
    public List<Poet> singerOfName(String name) {
        return null;
    }

    @Override
    public List<Poet> singerOfPeriod(Integer period) {
        return null;
    }

    /**
     * 诗人总数
     *
     * @return
     */
    @Override
    public int poetCount() {
        return poetMapper.poetCount();
    }
}
