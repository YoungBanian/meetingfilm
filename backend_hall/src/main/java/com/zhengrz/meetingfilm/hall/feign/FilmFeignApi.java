package com.zhengrz.meetingfilm.hall.feign;

import com.zhengrz.meetingfilm.apis.film.FilmFeignApis;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "film-service")
public interface FilmFeignApi extends FilmFeignApis {
}
