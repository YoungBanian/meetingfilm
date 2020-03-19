package com.zhengrz.meetingfilm.user.service;

import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;

public interface IUserService {


    String checkUserLogin(String username, String password) throws CommonServiceException;

}
