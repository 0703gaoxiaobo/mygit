package ${BasePackageName}${ControllerPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import ${BasePackageName}${ServicePackageName}.${ClassName}Service;
import ${BasePackageName}${RequestPackageName}.${ClassName}Req;
import ${BasePackageName}${ResponsePackageName}.${ClassName}Res;
import ${BasePackageName}base.PagesModel;
import ${BasePackageName}base.controller.BaseController;
import ${BasePackageName}util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@RestController
@RequestMapping(value = "/${EntityName}")
public class ${ClassName}Controller extends BaseController<${ClassName}Req, ${ClassName}Res>{
    @Autowired
    private ${ClassName}Service ${EntityName}Service;

    /**
     * 显示列表信息
     * @param map
     * @param ${EntityName}Req
     * @return
     */
    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public String show${ClassName}List(ModelMap map, ${ClassName}Req ${EntityName}Req) {
        PagesModel<${ClassName}Req, ${ClassName}Res> pagesModel =new PagesModel<>(${EntityName}Req);
        this.setPagesToModel(pagesModel, ${EntityName}Req);
        //排序规则自定义
        pagesModel.setOrderBy("");
        ${EntityName}Service.select${ClassName}List(pagesModel);
        map.put("pagesModel",pagesModel);
        return "";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(ModelMap map,Long ${PrimaryKey}){
        ${ClassName} ${EntityName} = ${EntityName}Service.get(${PrimaryKey});
        map.put("${EntityName}",${EntityName});
        return "";
    }

    @RequestMapping("/toInsert")
    public String toInsert(ModelMap map,Long ${PrimaryKey}){
        return "";
    }

    /**
     * 新增(单条)
     * @param ${EntityName}
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> insert${ClassName}(${ClassName} ${EntityName}) {
        Result<Boolean> result = new Result<>();
        int res=${EntityName}Service.insert(${EntityName});
        if (res > 0) {
            result.setData(true);
        } else {
            result.setData(false);
        }
        return result;
    }

    /**
    * 单一删除
    * @param ${EntityName}
    * @return
    */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(Long ${PrimaryKey}) {
        Result<Boolean> result = new Result<>();
        int res=${EntityName}Service.deleteByPrimaryKey(${PrimaryKey});
        if (res > 0) {
            result.setData(true);
        } else {
            result.setData(false);
        }
        return result;
    }

    /**
     *批量删除
     *@param idList id集合
     *@return
     */
    @RequestMapping("/deleteArticleBatch")
    @ResponseBody
    public Result<Boolean> deleteArticleBatch(String idList){
        Result<Boolean> result = new Result<>();
        int res = ${EntityName}Service.deleteArticleBatch(parseStringIdList(idList));
        if(res > 0){
            result.setData(true);
        }else{
            result.setData(false);
        }
        return result;
    }

    /**
     *修改
     *@param ${EntityName}
     *@return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result<Boolean> updateNews(${ClassName} ${EntityName}){
        Result<Boolean> result = new Result<>();
        int res = ${EntityName}Service.updateByPrimaryKeySelective(${EntityName});
        if(res > 0){
            result.setData(true);
        }else{
            result.setData(false);
        }
        return result;
    }

}
