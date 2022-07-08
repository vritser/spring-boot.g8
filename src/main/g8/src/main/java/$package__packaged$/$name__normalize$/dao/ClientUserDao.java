package  $package$.$name;format="normalize"$.dao;

import java.util.Optional;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import $package$.$name;format="normalize"$.entity.ClientUser;

@Mapper
public interface ClientUserDao extends BaseMapper<ClientUser> {

    @Select("select * from client_users where openid = #{openid} and is_deleted = 0")
    Optional<ClientUser> findByOpenid(@Param("openid") String openid);

}
