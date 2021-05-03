package zone.iread.ipoetry.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zone.iread.ipoetry.domain.Poem;
import zone.iread.ipoetry.domain.PoemType;
import zone.iread.ipoetry.service.PoemTypeService;
import zone.iread.ipoetry.utils.Consts;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 诗词类型管理控制类
 */
@RestController
@RequestMapping("/poemType")
public class PoemTypeController {

    @Autowired
    PoemTypeService poemTypeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addPoemType(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title").trim();
        String pic = "/img/poemTypePic/type.jpg"; //默认图片
        String introduction = request.getParameter("introduction");
        //保存到对象中
        PoemType poemType = new PoemType();
        poemType.setTitle(title);
        poemType.setPic(pic);
        poemType.setIntroduction(introduction);
        boolean flag = poemTypeService.insert(poemType);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updatePoemType(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String title = request.getParameter("title").trim();
        String introduction = request.getParameter("introduction");

        //保存到对象中
        PoemType poemType = new PoemType();
        poemType.setId(Integer.parseInt(id));
        poemType.setTitle(title);
        poemType.setIntroduction(introduction);
        boolean flag = poemTypeService.update(poemType);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
        }
        return jsonObject;
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deletePoemType(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        boolean flag = poemTypeService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 所有类型
     * @return
     */
    @RequestMapping(value = "/allPoemType",method = RequestMethod.GET)
    public Object allPoemType(){
        return poemTypeService.allPoemType();
    }

    /**
     * 根据诗词类型id查询
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return poemTypeService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 更新分类图片
     */
    @RequestMapping(value = "/updatePoemTypePic",method = RequestMethod.POST)
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
                +System.getProperty("file.separator")+"poemTypePic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库的是相对地址
        String storeAvatorPath = "/img/poemTypePic/"+fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PoemType poemType = new PoemType();
        poemType.setId(id);
        poemType.setPic(storeAvatorPath);
        boolean flag = poemTypeService.update(poemType);
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

    @RequestMapping("/typeCount")
    public Object userCount(){
        JSONObject jsonObject = new JSONObject();
        int count = poemTypeService.typeCount();
        jsonObject.put("data", count);
        return jsonObject;
    }


}
