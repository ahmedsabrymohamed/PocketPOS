package com.pocket.pos.util.mapper.entitymapper;

import com.pocket.pos.entity.User;
import com.pocket.pos.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    UserModel mapUserEntityToModel(User user);
}
