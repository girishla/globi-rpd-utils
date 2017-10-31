package com.globi.rpd.cli;

public interface RpdObjectCommand<R, T>  {

	String execute(T input) throws Exception;

}
