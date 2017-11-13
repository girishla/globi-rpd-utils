package com.globi.rpd.cli;

public interface RpdCommandExecutor<R, T>  {

	String execute(T input) throws Exception;

}
