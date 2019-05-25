<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${BasePackageName}${DaoPackageName}.${ClassName}Mapper" >
    <resultMap id="BaseResultMap" type="${BasePackageName}${EntityPackageName}.${ClassName}" >
    <#list Properties as x>
    <#if x.ifPrimaryKey?string("true","false")=="true">
        <id column="${PrimaryKey}" property="${x.propertyName}" jdbcType="${x.jdbcType}" javaType="${x.javaType}" />
    <#else>
        <result column="${x.columnName}" property="${x.propertyName}" jdbcType="${x.jdbcType}" javaType="${x.javaType}" />
    </#if>
    </#list>
    </resultMap>
    <#noparse>
    <sql id="Example_Where_Clause" >
        <where >
            <foreach collection="oredCriteria" item="criteria" separator="or" >
                <if test="criteria.valid" >
                    <trim prefix="(" suffix=")" prefixOverrides="and" >
                        <foreach collection="criteria.criteria" item="criterion" >
                            <choose >
                                <when test="criterion.noValue" >
                                    and ${criterion.condition}
                                </when>
                                <when test="criterion.singleValue" >
                                    and ${criterion.condition} #{criterion.value}
                                </when>
                                <when test="criterion.betweenValue" >
                                    and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                                </when>
                                <when test="criterion.listValue" >
                                    and ${criterion.condition}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")" separator="," >
                                    #{listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    <sql id="Update_By_Example_Where_Clause" >
        <where >
            <foreach collection="example.oredCriteria" item="criteria" separator="or" >
                <if test="criteria.valid" >
                    <trim prefix="(" suffix=")" prefixOverrides="and" >
                        <foreach collection="criteria.criteria" item="criterion" >
                            <choose >
                                <when test="criterion.noValue" >
                                    and ${criterion.condition}
                                </when>
                                <when test="criterion.singleValue" >
                                    and ${criterion.condition} #{criterion.value}
                                </when>
                                <when test="criterion.betweenValue" >
                                    and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                                </when>
                                <when test="criterion.listValue" >
                                    and ${criterion.condition}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")" separator="," >
                                    #{listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    </#noparse>
    <sql id="Base_Column_List" >
     <#list Properties as x>
     <#if x_has_next>
         ${x.columnName},
     <#else>
         ${x.columnName}
     </#if>
     </#list>
    </sql>
    <select id="selectBy${ClassName}QueryHelper" resultMap="BaseResultMap" parameterType="${BasePackageName}${QueryHelperPackageName}.${ClassName}QueryHelper" >
        select
        <if test="distinct" >
            distinct
        </if>
        <include refid="Base_Column_List" />
        from mall_article
        <if test="_parameter != null" >
            <include refid="Example_Where_Clause" />
        </if>
        <if test="orderByClause != null" >
        <#noparse>
            order by ${orderByClause}
        </#noparse>
        </if>
    </select>
    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Long" >
        select
        <include refid="Base_Column_List" />
        from ${TableName}
        where ${PrimaryKey} = ${r"#{"}${PrimaryKey},jdbcType=BIGINT}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long" >
        delete from ${TableName}
        where ${PrimaryKey} = ${r"#{"}${PrimaryKey},jdbcType=BIGINT}
    </delete>
    <delete id="deleteBy${ClassName}QueryHelper" parameterType="${BasePackageName}${QueryHelperPackageName}.${ClassName}QueryHelper" >
        delete from ${TableName}
        <if test="_parameter != null" >
            <include refid="Example_Where_Clause" />
        </if>
    </delete>
    <insert id="insert" parameterType="${BasePackageName}${EntityPackageName}.${ClassName}" >
        insert into ${TableName} (
            <#list Properties as x>
                <#if x_has_next>
                    ${x.columnName},
                <#else>
                    ${x.columnName}
                </#if>
            </#list>
        )
        values (
            <#list Properties as x>
                <#if x_has_next>
                    ${r"#{"}${x.propertyName},jdbcType=${x.jdbcType}},
                <#else>
                    ${r"#{"}${x.propertyName},jdbcType=${x.jdbcType}}
                </#if>
            </#list>
        )
    </insert>
    <insert id="insertSelective" parameterType="${BasePackageName}${EntityPackageName}.${ClassName}" >
        insert into ${TableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <#list Properties as x>
                <if test="${x.columnName} != null" >
                    ${x.columnName},
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
            <#list Properties as x>
            <if test="${x.propertyName} != null" >
            ${r"#{"}${x.propertyName},jdbcType=${x.jdbcType}},
            </if>
            </#list>
        </trim>
    </insert>
    <select id="countBy${ClassName}QueryHelper" parameterType="${BasePackageName}${QueryHelperPackageName}.${ClassName}QueryHelper" resultType="java.lang.Integer" >
        select count(*) from ${TableName}
        <if test="_parameter != null" >
            <include refid="Example_Where_Clause" />
        </if>
    </select>
    <update id="updateBy${ClassName}QueryHelperSelective" parameterType="map" >
        update ${TableName}
        <set >
            <#list Properties as x>
            <if test="${EntityName}.${x.propertyName} != null" >
                ${x.columnName} = ${r"#{"}${EntityName}.${x.propertyName},jdbcType=${x.jdbcType}},
            </if>
            </#list>
        </set>
        <if test="_parameter != null" >
            <include refid="Update_By_Example_Where_Clause" />
        </if>
    </update>
    <update id="updateBy${ClassName}QueryHelper" parameterType="map" >
        update ${TableName}
        set
        <#list Properties as x>
            ${x.columnName} = ${r"#{"}${EntityName}.${x.propertyName},jdbcType=${x.jdbcType}},
        </#list>
        <if test="_parameter != null" >
            <include refid="Update_By_Example_Where_Clause" />
        </if>
    </update>
    <update id="updateByPrimaryKeySelective" parameterType="${BasePackageName}${EntityPackageName}.${ClassName}" >
        update ${TableName}
        <set >
       <#list Properties as x>
       <#if x.ifPrimaryKey?string("true","false")!="true">
          <if test="${x.propertyName} != null" >
            ${x.columnName} = ${r"#{"}${x.propertyName},jdbcType=${x.jdbcType}},
          </if>
       </#if>
       </#list>
        </set>
        where ${PrimaryKey} = ${r"#{"}${PrimaryKey},jdbcType=BIGINT}
    </update>
    <update id="updateByPrimaryKey" parameterType="${BasePackageName}${EntityPackageName}.${ClassName}">
        update
           ${TableName}
        set
        <#list Properties as x>
           <#if x.ifPrimaryKey?string("true","false")!="true">
           ${x.columnName} = ${r"#{"}${x.propertyName},jdbcType=${x.jdbcType}},
           </#if>
        </#list>
        where ${PrimaryKey} = ${r"#{"}${PrimaryKey},jdbcType=BIGINT}
    </update>

    <select id="select${ClassName}List" resultType="${BasePackageName}${ResponsePackageName}.${ClassName}Res">
    <#noparse>
    select *
    <include refid="condition"></include>
    <if test="orderBy != null">
        ORDER BY ${orderBy}
    </if>
    <if test="doPage">
        LIMIT #{limitStart,jdbcType=INTEGER},
        #{limitNum,jdbcType=INTEGER}
    </if>
    </select>
    </#noparse>
    <select id="count${ClassName}List" resultType="int">
        select count(1)
        <include refid="condition"></include>
    </select>

    <sql id="condition">
        from ${TableName}
    </sql>
</mapper>