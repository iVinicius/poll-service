package com.ivinicius.pollservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "User is not able to vote.")
public class UserUnableToVoteException extends Exception{

}
