package  $package$.$name;format="normalize"$.dao;

import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import $package$.$name;format="normalize"$.entity.User;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("select * from users where mobile = #{mobile} and is_deleted = 0")
    Optional<User> findByMobile(@Param("mobile") String mobile);

    @Select("select * from users \${ew.customSqlSegment}")
    IPage<User> findPage(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> query);

}
