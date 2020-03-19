package com.zhengrz.meetingfilm.hall.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengrz.meetingfilm.hall.controller.vo.HallsReqVO;
import com.zhengrz.meetingfilm.hall.controller.vo.HallsRespVO;
import com.zhengrz.meetingfilm.hall.dao.entity.TbField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author zhengrz
 * @since 2020-03-11
 */
public interface TbFieldMapper extends BaseMapper<TbField> {

    IPage<HallsRespVO> describeHalls(Page<HallsReqVO> page, @Param("ew") Wrapper wrapper);

}
