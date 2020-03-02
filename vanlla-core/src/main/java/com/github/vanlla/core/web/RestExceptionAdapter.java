package com.github.vanlla.core.web;


import com.github.vanlla.core.exception.RestException;

/**
 * RestExceptionAdapter
 *
 * @author Vanlla
 * @since 1.0
 */
public interface RestExceptionAdapter {


    void handle(RestException e);

}