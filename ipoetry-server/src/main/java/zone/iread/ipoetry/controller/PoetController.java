package zone.iread.ipoetry.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zone.iread.ipoetry.domain.Poet;
import zone.iread.ipoetry.service.PoetService;
import zone.iread.ipoetry.utils.Consts;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/poet")
public class PoetController {
    @Resource
    PoetService poetService;


    /**
     * 添加诗人
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addPoet(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();//trim();去掉前后的空格
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String period = request.getParameter("period").trim();
        String introduction = request.getParameter("introduction").trim();
        //保存到对象中
        Poet poet = new Poet(name,period,introduction,pic,new Byte(sex));
        boolean flag = poetService.insert(poet);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();//trim();去掉前后的空格
        String sex = request.getParameter("sex").trim();
        String period = request.getParameter("period").trim();
        String introduction = request.getParameter("introduction").trim();

        //保存到对象中
        Poet poet = new Poet(Integer.parseInt(id),name,period,introduction,new Byte(sex));
        boolean flag = poetService.update(poet);
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
     * 删除诗人
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();

        boolean flag = poetService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * id诗人
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        return poetService.selectByPrimaryKey(Integer.parseInt(id));
    }
    /**
     * 所有诗人
     */
    @RequestMapping(value = "/allPoet",method = RequestMethod.GET)
    public Object allSinger(HttpServletRequest request){

        return poetService.allPoet();
    }

    /**
     * 更新诗人图片
     */
    @RequestMapping(value = "/updatePoetPic",method = RequestMethod.POST)
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
                +System.getProperty("file.separator")+"poetPic";
        //如果文件路径不存在，新增该路径
        System.out.println(fileName);
        System.out.println(filePath);
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库的是相对地址
        String storeAvatorPath = "/img/poetPic/"+fileName;
        System.out.println(storeAvatorPath);

        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Poet poet = new Poet();
        poet.setId(id);
        poet.setPic(storeAvatorPath);
        boolean flag = poetService.update(poet);
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
        /*try {
            avatorFile.transferTo(dest);
            Poet poet = new Poet();
            poet.setId(id);
            System.out.println(id+"                    444444444444444444444");
            poet.setPic(storeAvatorPath);
            System.out.println("+==========================");
            boolean flag = poetService.update(poet);
            System.out.println("=======");
            System.out.println(flag);
            System.out.println("=======");
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
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
            System.out.println(e);
            return jsonObject;
        }finally {
            return jsonObject;
        }*/

    }

    @RequestMapping("/poetCount")
    public Object poetCount(){
        JSONObject jsonObject = new JSONObject();
        int count = poetService.poetCount();
        jsonObject.put("data", count);
        return jsonObject;
    }

}
