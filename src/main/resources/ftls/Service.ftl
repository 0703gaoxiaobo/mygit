package ${BasePackageName}${ServicePackageName};

import ${BasePackageName}${DaoPackageName}.${ClassName}Mapper;
import ${BasePackageName}${EntityPackageName}.${ClassName};
import ${BasePackageName}${ServicePackageName}.${ClassName}Service;
import ${BasePackageName}${RequestPackageName}.${ClassName}Req;
import ${BasePackageName}${ResponsePackageName}.${ClassName}Res;
import ${BasePackageName}${QueryHelperPackageName}.${ClassName}QueryHelper;
import ${BasePackageName}base.PagesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@Service
public class ${ClassName}ServiceImpl implements ${ClassName}Service{
    @Autowired
    private ${ClassName}Mapper ${EntityName}Mapper;

    @Override
    public int countBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper){
        return ${EntityName}Mapper.countBy${EntityName}QueryHelper(${ClassName}QueryHelper);
    }

    @Override
    public int deleteBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper){
        return deleteBy${ClassName}QueryHelper(${EntityName}QueryHelper);
    }

    @Override
    public int deleteByPrimaryKey(Long ${PrimaryKey}){
        return deleteByPrimaryKey(${PrimaryKey});
    }

    @Override
    public int insert(${ClassName} ${EntityName}){
        return insert(${EntityName});
    }

    @Override
    public int insertSelective(${ClassName} ${EntityName}){
        return insertSelective(${EntityName});
    }

    @Override
    public List<${ClassName}> selectBy${ClassName}QueryHelper(${ClassName}QueryHelper ${EntityName}QueryHelper){
        return selectBy${ClassName}QueryHelper(${EntityName}QueryHelper);
    }


    @Override
    public ${ClassName} selectByPrimaryKey(Long ${PrimaryKey}){
        return selectByPrimaryKey(${PrimaryKey});
    }

    @Override
    public int updateBy${ClassName}QueryHelperSelective(${ClassName} ${EntityName},${ClassName}QueryHelper ${EntityName}QueryHelper){
        return updateBy${ClassName}QueryHelperSelective(${EntityName},${EntityName}QueryHelper);
    }

    @Override
    public int updateBy${ClassName}QueryHelper(${ClassName} ${EntityName}, ${ClassName}QueryHelper ${EntityName}QueryHelper){
        return updateBy${ClassName}QueryHelper(${EntityName},${EntityName}QueryHelper);
    }

    @Override
    public int updateByPrimaryKeySelective(${ClassName} ${EntityName}){
        return ${EntityName}Mapper.updateByPrimaryKeySelective(${EntityName});
    }

    @Override
    public int updateByPrimaryKey(${ClassName} ${EntityName}){
        return ${EntityName}Mapper.updateByPrimaryKey(${EntityName});
    }

    @Override
    public void select${ClassName}List(PagesModel<${ClassName}Req, ${ClassName}Res> pagesModel){
        pagesModel.setResults(${EntityName}Mapper.select${ClassName}List(pagesModel));
        pagesModel.setTotal(${EntityName}Mapper.count${ClassName}List(pagesModel));
    }
}
