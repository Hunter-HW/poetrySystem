package zone.iread.ipoetry.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zone.iread.ipoetry.domain.Poem;
import zone.iread.ipoetry.domain.Poet;
import zone.iread.ipoetry.service.PoemService;
import zone.iread.ipoetry.service.TypeToPoemService;
import zone.iread.ipoetry.utils.Consts;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 诗词controller
 */
@RestController
@RequestMapping("/poem")
public class PoemController {
    @Autowired
    private PoemService poemService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addPoem(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String poetId = request.getParameter("poetId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction");
        String content = request.getParameter("content");
        String pic = "/img/poemPic/poem.jpg"; //默认图片
        String view = request.getParameter("view");

        //保存到对象中
        Poem poem = new Poem();
        poem.setPoetId(Integer.parseInt(poetId));
        poem.setName(name);
        poem.setIntroduction(introduction);
        poem.setContent(content);
        poem.setPic(pic);
        poem.setView(Integer.parseInt(view));
        boolean flag = poemService.insert(poem);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }

    /**
     * 根据诗人id查询诗词
     */
    @RequestMapping(value = "/poet/detail",method = RequestMethod.GET)
    public Object poemOfPoetId(HttpServletRequest request){
        String poetId = request.getParameter("poetId");
        List<Poem> poems = poemService.poemOfPoetId(Integer.parseInt(poetId));
//        System.out.println(poems);
        return poems;
    }
    /**
     * 根据id查询诗词
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object detail(HttpServletRequest request){
        String poemId = request.getParameter("poemId");
        Poem poem = poemService.selectByPrimaryKey(Integer.parseInt(poemId));
//        System.out.println(poem.getId()+poem.getName());
        return poem;
    }

    /**
     * 根据诗词名字精确查询
     */
    @RequestMapping(value = "/poemOfPoemName",method = RequestMethod.GET)
    public Object poemOfPoemName(HttpServletRequest request){
        String poemName = request.getParameter("poemName");
        System.out.println(poemName);
        List<Poem> poems = poemService.poemOfName(poemName);
        JSONObject jsonObject = new JSONObject();
        if(poems.size() == 0){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG,"没有该诗词");
        }else {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put("poemData",poems);
        }
        return jsonObject;
    }
    /**
     * 修改诗词
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction");
        String content = request.getParameter("content");
        String view = request.getParameter("view");

        //保存到对象中
        Poem poem = new Poem();
        poem.setId(Integer.parseInt(id));
        poem.setName(name);
        poem.setIntroduction(introduction);
        poem.setContent(content);
        poem.setView(Integer.parseInt(view));
        boolean flag = poemService.update(poem);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
        }
        return jsonObject;
    }

    @Autowired
    TypeToPoemService typeToPoemService;
    /**
     * 删除诗词
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();

        boolean flag = poemService.delete(Integer.parseInt(id));
        // 删除诗词的时候将诗词类型表中的诗词一块消除,首先做一个判断在typeToPoemService中是否存在该
        typeToPoemService.deleteByPoemId(Integer.parseInt(id));
        return flag;
    }

    /**
     * 更新诗词图片
     */
    @RequestMapping(value = "/updatePoemPic",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间的毫秒加上文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"poemPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库的是相对地址
        String storeAvatorPath = "/img/poemPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Poem poem = new Poem();
        poem.setId(id);
        poem.setPic(storeAvatorPath);
        boolean flag = poemService.update(poem);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"上传成功");
            jsonObject.put("pic",storeAvatorPath);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
        }
        return jsonObject;
    }

    @RequestMapping("/poemCount")
    public Object poemCount(){
        JSONObject jsonObject = new JSONObject();
        int count = poemService.poemCount();
        jsonObject.put("data", count);
        return jsonObject;
    }

    /**
     * 所有诗词
     * @return
     */
    @RequestMapping("/allPoem")
    public Object allPoem(){
        return poemService.allPoem();
    }


}
