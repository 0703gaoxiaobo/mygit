package ${BasePackageName}${DaoPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import ${BasePackageName}${QueryHelperPackageName}.${ClassName}QueryHelper;
import ${BasePackageName}${RequestPackageName}.${ClassName}Req;
import ${BasePackageName}${ResponsePackageName}.${ClassName}Res;
import ${BasePackageName}base.PagesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@Mapper
public interface ${ClassName}Mapper {

    int countBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper);

    int deleteBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper);

    int deleteByPrimaryKey(Long ${PrimaryKey});

    int insert(${ClassName} ${EntityName});

    int insertSelective(${ClassName} ${EntityName});

    List<${ClassName}> selectBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper);

    ${ClassName} selectByPrimaryKey(Long ${PrimaryKey});

    int updateBy${ClassName}QueryHelperSelective(@Param("${EntityName}") ${ClassName} ${EntityName}, @Param("${EntityName}QueryHelper") ${ClassName}QueryHelper ${EntityName}QueryHelper);

    int updateBy${ClassName}QueryHelper(@Param("${EntityName}") ${ClassName} ${EntityName}, @Param("${EntityName}QueryHelper") ${ClassName}QueryHelper ${EntityName}QueryHelper);

    int updateByPrimaryKeySelective(${ClassName} ${EntityName});

    int updateByPrimaryKey(${ClassName} ${EntityName});

    List<${ClassName}Res> select${ClassName}List(PagesModel<${ClassName}Req, ${ClassName}Res> pagesModel);

    int count${ClassName}List(PagesModel<${ClassName}Req, ${ClassName}Res> pagesModel);


}