package com.zhengrz.meetingfilm.hall.controller.vo;

import lombok.Data;

@Data
public class DescribeFilmRespVO {
    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private String subAddress;
}
