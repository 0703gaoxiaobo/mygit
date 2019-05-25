package ${BasePackageName}${InterfacePackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import ${BasePackageName}${RequestPackageName}.${ClassName}Req;
import ${BasePackageName}${ResponsePackageName}.${ClassName}Res;
import ${BasePackageName}${QueryHelperPackageName}.${ClassName}QueryHelper;
import ${BasePackageName}base.PagesModel;
import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
public interface ${ClassName}Service {

    public int countBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper);

    public int deleteBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper);

    public int deleteByPrimaryKey(Long ${PrimaryKey});

    public int insert(${ClassName} ${EntityName});

    public int insertSelective(${ClassName} ${EntityName});

    public List<${ClassName}> selectBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper);

    public ${ClassName} selectByPrimaryKey(Long ${PrimaryKey});

    public int updateBy${ClassName}QueryHelperSelective(${ClassName} ${EntityName},${ClassName}QueryHelper ${EntityName}QueryHelper);

    public int updateBy${ClassName}QueryHelper(${ClassName} ${EntityName}, ${ClassName}QueryHelper ${EntityName}QueryHelper);

    public int updateByPrimaryKeySelective(${ClassName} ${EntityName});

    public int updateByPrimaryKey(${ClassName} ${EntityName});

    public void select${ClassName}List(PagesModel<${ClassName}Req, ${ClassName}Res> pagesModel);
}
