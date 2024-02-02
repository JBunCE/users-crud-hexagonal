package com.jbunce.userscrudhex.domain.services;

import com.jbunce.userscrudhex.application.dtos.request.UserRequest;
import com.jbunce.userscrudhex.application.dtos.response.BaseResponse;

public interface IUserService {

    public BaseResponse findAll();

    public BaseResponse findById(String id);

    public BaseResponse create(UserRequest userRequest);

    public BaseResponse update(String id, UserRequest userRequest);

    public BaseResponse delete(String id);

}
