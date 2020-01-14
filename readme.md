# lscode常用工具类

[![](https://travis-ci.org/damiancritchfield/lscode-commons-utils.svg?branch=master)](https://travis-ci.org/damiancritchfield/lscode-commons-utils)

## 1.发布jar包
```$xslt
mvn clean deploy -P release -Dmaven.test.skip=true
mvn deploy F:\private\projects\java\lscode-commons-utils\deploy\settings.xml -DperformRelease=true -DskipTests=true
```

## 2.CommonTreeUtils使用的数据列表
```xml
<select id="getDeviceTypeTree" resultMap="BaseResultMap">
  WITH RECURSIVE r AS (
      SELECT
          device_type_id,
          device_type_name,
          device_type_pid,
          device_type_code,
          device_category_id,
          device_type_name||'' full_path,
          0 "level"
      from device_type WHERE delete_flag = 0 and device_type_pid is NULL
      UNION ALL
      SELECT
          t.device_type_id,
          t.device_type_name,
          t.device_type_pid,
          t.device_type_code,
          t.device_category_id,
          r.full_path||'/'||t.device_type_name full_path,
          r."level"+1 "level"
      FROM device_type t, r WHERE delete_flag = 0 and t.device_type_pid=r.device_type_id
  )
  SELECT
      device_type_id "id",
      device_type_name "name",
      device_type_pid pid,
      full_path fullPath,
      "level",
      device_type_code,
      device_category_id
  FROM r ORDER BY full_path, "level"
</select>

<resultMap id="BaseResultMap" type="com.le.business.informationMsg.vo.DefaultTree">
    <result property="id" column="id" />
    <result property="name" column="name" />
    <result property="pid" column="pid" />
    <result property="fullPath" column="fullPath" />
    <result property="level" column="level" />
    <association property="assets" javaType="java.util.HashMap">
      <result property="deviceTypeCode" column="device_type_code" />
      <result property="deviceCategoryId" column="device_category_id" />
    </association>
</resultMap>
```