package com.jbunce.userscrudhex.domain.services;

import com.jbunce.userscrudhex.application.dtos.request.UserRequest;
import com.jbunce.userscrudhex.application.dtos.response.BaseResponse;

public interface IUserService {

    public BaseResponse findAll();

    public BaseResponse findById(Long id);

    public BaseResponse create(UserRequest userRequest);

    public BaseResponse update(Long id, UserRequest userRequest);

    public BaseResponse delete(Long id);

}
