package ${BasePackageName}${ResponsePackageName};

import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import ${BasePackageName}base.BaseRes;

/**
 * Author ${Author}
 * Date  ${Date}
 */
// 生成无参构造方法/getter/setter/hashCode/equals/toString
@Data
// 生成所有参数构造方法
@AllArgsConstructor
/* @AllArgsConstructor会导致@Data不生成无参构造方法，
 * 需要手动添加@NoArgsConstructor，如果没有无参构造方法，
 * 可能会导致比如com.fasterxml.jackson在序列化处理时报错
 */
@NoArgsConstructor
public class ${ClassName}Res extends BaseRes implements Serializable {
      private static final long serialVersionUID = 1L;
<#list Properties as x>
      private ${x.javaType} ${x.propertyName};
</#list>

<#--
    public ${ClassName}(){
    }

    ${Methods}
-->
}