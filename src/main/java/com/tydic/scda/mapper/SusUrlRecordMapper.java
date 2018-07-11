package com.tydic.scda.mapper;

import com.tydic.scda.model.SusUrlRecord;
import com.tydic.scda.model.SusUrlRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SusUrlRecordMapper {
    @SelectProvider(type=SusUrlRecordSqlProvider.class, method="countByExample")
    int countByExample(SusUrlRecordExample example);

    @DeleteProvider(type=SusUrlRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(SusUrlRecordExample example);

    @Delete({
        "delete from SUS_URL_RECORD",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into SUS_URL_RECORD (ID, SHORT_URL, ",
        "SRC_URL, CREATE_TIME)",
        "values (#{id,jdbcType=BIGINT}, #{shortUrl,jdbcType=VARCHAR}, ",
        "#{srcUrl,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(SusUrlRecord record);

    @InsertProvider(type=SusUrlRecordSqlProvider.class, method="insertSelective")
    int insertSelective(SusUrlRecord record);

    @SelectProvider(type=SusUrlRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="SHORT_URL", property="shortUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="SRC_URL", property="srcUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SusUrlRecord> selectByExample(SusUrlRecordExample example);

    @Select({
        "select",
        "ID, SHORT_URL, SRC_URL, CREATE_TIME",
        "from SUS_URL_RECORD",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="SHORT_URL", property="shortUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="SRC_URL", property="srcUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SusUrlRecord selectByPrimaryKey(Long id);

    @UpdateProvider(type=SusUrlRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SusUrlRecord record, @Param("example") SusUrlRecordExample example);

    @UpdateProvider(type=SusUrlRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SusUrlRecord record, @Param("example") SusUrlRecordExample example);

    @UpdateProvider(type=SusUrlRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SusUrlRecord record);

    @Update({
        "update SUS_URL_RECORD",
        "set SHORT_URL = #{shortUrl,jdbcType=VARCHAR},",
          "SRC_URL = #{srcUrl,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SusUrlRecord record);
}