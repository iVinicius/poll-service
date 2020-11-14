package com.ivinicius.pollservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "User Tax Id not found.")
public class UserTaxIdNotFoundException extends Exception{

}
