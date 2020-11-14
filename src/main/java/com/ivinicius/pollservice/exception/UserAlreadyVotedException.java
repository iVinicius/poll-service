package com.ivinicius.pollservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "User already voted.")
public class UserAlreadyVotedException extends Exception{

}
