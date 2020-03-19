package com.zhengrz.meetingfilm.user.controller.vo;

import com.zhengrz.meetingfilm.utils.common.vo.BaseRequestVO;
import com.zhengrz.meetingfilm.utils.exception.CommonServiceException;
import com.zhengrz.meetingfilm.utils.util.ToolUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginReqVO extends BaseRequestVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {
        if (ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)) {
            throw new CommonServiceException(404, "用户名/密码不能为空");
        }
    }
}
