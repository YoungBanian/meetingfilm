package com.zhengrz.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.zhengrz.meetingfilm.film.dao.entity.TbActor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author zhengrz
 * @since 2020-03-11
 */
public interface TbActorMapper extends BaseMapper<TbActor> {

    IPage<DescribeActorsRespVO> describeActors(Page<DescribeActorsRespVO> page);

}
