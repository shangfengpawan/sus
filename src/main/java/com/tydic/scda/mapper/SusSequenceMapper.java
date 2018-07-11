package com.tydic.scda.mapper;

import com.tydic.scda.model.SusSequence;
import com.tydic.scda.model.SusSequenceExample;
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

public interface SusSequenceMapper {
    @SelectProvider(type=SusSequenceSqlProvider.class, method="countByExample")
    int countByExample(SusSequenceExample example);

    @DeleteProvider(type=SusSequenceSqlProvider.class, method="deleteByExample")
    int deleteByExample(SusSequenceExample example);

    @Delete({
        "delete from SUS_SEQUENCE",
        "where name = #{name,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String name);

    @Insert({
        "insert into SUS_SEQUENCE (name, current_value, ",
        "increment)",
        "values (#{name,jdbcType=VARCHAR}, #{currentValue,jdbcType=BIGINT}, ",
        "#{increment,jdbcType=DECIMAL})"
    })
    int insert(SusSequence record);

    @Update({
            "UPDATE SUS_SEQUENCE",
            "SET current_value = current_value + increment",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    int addCurrVal(String name);

    @InsertProvider(type=SusSequenceSqlProvider.class, method="insertSelective")
    int insertSelective(SusSequence record);

    @SelectProvider(type=SusSequenceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="current_value", property="currentValue", jdbcType=JdbcType.BIGINT),
        @Result(column="increment", property="increment", jdbcType=JdbcType.DECIMAL)
    })
    List<SusSequence> selectByExample(SusSequenceExample example);

    @Select({
        "select",
        "name, current_value, increment",
        "from SUS_SEQUENCE",
        "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="current_value", property="currentValue", jdbcType=JdbcType.BIGINT),
        @Result(column="increment", property="increment", jdbcType=JdbcType.DECIMAL)
    })
    SusSequence selectByPrimaryKey(String name);

    @UpdateProvider(type=SusSequenceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SusSequence record, @Param("example") SusSequenceExample example);

    @UpdateProvider(type=SusSequenceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SusSequence record, @Param("example") SusSequenceExample example);

    @UpdateProvider(type=SusSequenceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SusSequence record);

    @Update({
        "update SUS_SEQUENCE",
        "set current_value = #{currentValue,jdbcType=BIGINT},",
          "increment = #{increment,jdbcType=DECIMAL}",
        "where name = #{name,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SusSequence record);
}