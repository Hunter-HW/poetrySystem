package zone.iread.ipoetry.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zone.iread.ipoetry.domain.TypeToPoem;
import zone.iread.ipoetry.service.TypeToPoemService;
import zone.iread.ipoetry.utils.Consts;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/typeToPoem")
public class TypeToPoemController {
    @Autowired
    TypeToPoemService typeToPoemService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addPoem(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String typeId = request.getParameter("typeId").trim();
        String poemId= request.getParameter("poemId").trim();

        //保存到对象中
        TypeToPoem typeToPoem = new TypeToPoem();
        typeToPoem.setTypeId(Integer.parseInt(typeId));
        typeToPoem.setPoemId(Integer.parseInt(poemId));
        boolean flag = typeToPoemService.insert(typeToPoem);
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
     * 修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String typeId = request.getParameter("typeId").trim();
        String poemId= request.getParameter("poemId").trim();

        //保存到对象中
        TypeToPoem typeToPoem = new TypeToPoem();
        typeToPoem.setId(Integer.parseInt(id));
        typeToPoem.setTypeId(Integer.parseInt(typeId));
        typeToPoem.setPoemId(Integer.parseInt(poemId));
        boolean flag = typeToPoemService.update(typeToPoem);
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
     * 根据id删除诗词
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        boolean flag = typeToPoemService.delete(Integer.parseInt(id));
        return flag;
    }

    @RequestMapping(value = "/deleteByPoemIdTypeId",method = RequestMethod.GET)
    public Object deleteByPoemIdTypeId(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String poemId = request.getParameter("poemId").trim();
        String typeId = request.getParameter("typeId").trim();
        System.out.println("poemId"+poemId);
        System.out.println("typeId"+typeId);
        boolean flag = typeToPoemService.deleteByPoemIdTypeId(Integer.parseInt(poemId),Integer.parseInt(typeId));
        System.out.println(flag);
        return flag;
    }
    /**
     * 根据类型typeId查询
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object typeToPoemOfTypeId(HttpServletRequest request){
        String typeId = request.getParameter("typeId").trim();
        List<TypeToPoem> typeToPoems = typeToPoemService.typeToPoemOfTypeId(Integer.parseInt(typeId));
//        for (TypeToPoem typeToPoem : typeToPoems) {
//            System.out.println(typeToPoem.getId()+"/"+typeToPoem.getPoemId()+"/"+typeToPoem.getTypeId());
//        }
        return typeToPoems;
    }
}
