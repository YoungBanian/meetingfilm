package com.zhengrz.meetingfilm.film.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.zhengrz.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.zhengrz.meetingfilm.film.dao.entity.TbFilm;
import com.zhengrz.meetingfilm.film.dao.entity.TbFilmActor;
import com.zhengrz.meetingfilm.film.dao.entity.TbFilmInfo;
import com.zhengrz.meetingfilm.film.dao.mapper.TbActorMapper;
import com.zhengrz.meetingfilm.film.dao.mapper.TbFilmActorMapper;
import com.zhengrz.meetingfilm.film.dao.mapper.TbFilmInfoMapper;
import com.zhengrz.meetingfilm.film.dao.mapper.TbFilmMapper;
import com.zhengrz.meetingfilm.film.service.IFilmService;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import com.zhengrz.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class FilmService implements IFilmService {

    @Resource
    private TbActorMapper actorMapper;

    @Resource
    private TbFilmMapper filmMapper;

    @Resource
    private TbFilmInfoMapper filmInfoMapper;

    @Resource
    private TbFilmActorMapper filmActorMapper;

    @Override
    public IPage<DescribeActorsRespVO> describeActors(int current, int pageSize) throws CommonServiceException {
        return actorMapper.describeActors(new Page<>(current, pageSize));
    }

    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(int current, int pageSize) throws CommonServiceException {
        return filmMapper.describeFilms(new Page<>(current, pageSize));
    }

    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException {
        return filmMapper.descrbeFilmById(filmId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFilm(FilmSavedReqVO reqVO) throws CommonServiceException {
        try{
            // 保存电影主表
            TbFilm film = new TbFilm();
            film.setFilmName(reqVO.getFilmName());
            film.setFilmType(ToolUtils.str2Int(reqVO.getFilmTypeId()));
            film.setImgAddress(reqVO.getMainImgAddress());
            film.setFilmScore(reqVO.getFilmScore());
            film.setFilmPresalenum(ToolUtils.str2Int(reqVO.getPreSaleNum()));
            film.setFilmBoxOffice(ToolUtils.str2Int(reqVO.getBoxOffice()));
            film.setFilmSource(ToolUtils.str2Int(reqVO.getFilmSourceId()));
            film.setFilmCats(reqVO.getFilmCatIds());
            film.setFilmArea(ToolUtils.str2Int(reqVO.getAreaId()));
            film.setFilmDate(ToolUtils.str2Int(reqVO.getDateId()));
            film.setFilmTime(ToolUtils.str2LocalDateTime(reqVO.getFilmTime()+" 00:00:00"));
            film.setFilmStatus(ToolUtils.str2Int(reqVO.getFilmStatus()));

            filmMapper.insert(film);
            // 保存电影子表
            TbFilmInfo filmInfo = new TbFilmInfo();

            filmInfo.setFilmId(film.getUuid()+"");
            filmInfo.setFilmEnName(reqVO.getFilmEnName());
            filmInfo.setFilmScore(reqVO.getFilmScore());
            filmInfo.setFilmScoreNum(ToolUtils.str2Int(reqVO.getFilmScorers()));
            filmInfo.setFilmLength(ToolUtils.str2Int(reqVO.getFilmLength()));
            filmInfo.setBiography(reqVO.getBiography());
            filmInfo.setDirectorId(ToolUtils.str2Int(reqVO.getDirectorId()));
            filmInfo.setFilmImgs(reqVO.getFilmImgs());


            filmInfoMapper.insert(filmInfo);

            String[] actorId = reqVO.getActIds().split("#");
            String[] roleNames = reqVO.getRoleNames().split("#");
            if(actorId.length != roleNames.length){
                throw new CommonServiceException(500, "演员和角色名数量不匹配");
            }

            for(int i=0;i<actorId.length;i++){
                // 保存演员映射表
                TbFilmActor filmActor = new TbFilmActor();

                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(ToolUtils.str2Int(actorId[i]));
                filmActor.setRoleName(roleNames[i]);


                filmActorMapper.insert(filmActor);
            }
        }catch (Exception e){
            throw new CommonServiceException(500, e.getMessage());
        }
    }
}
