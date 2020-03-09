package com.wizard.mapper;

import com.wizard.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User > {
}
