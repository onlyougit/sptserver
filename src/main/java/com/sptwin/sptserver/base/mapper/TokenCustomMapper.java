package com.sptwin.sptserver.base.mapper;

import com.sptwin.sptserver.entity.Token;

public interface TokenCustomMapper {

    Token findByToken(String tokenString);
}