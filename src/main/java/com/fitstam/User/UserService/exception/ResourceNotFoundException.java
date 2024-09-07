package com.fitstam.User.UserService.exception;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not Found on server");
    }

    public ResourceNotFoundException(String message){
        super("Resource not Found on server"+message);
    }
}
