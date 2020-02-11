package com.cwd.Utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MapperPlugin<T> extends Mapper<T>, MySqlMapper<T> {
}
