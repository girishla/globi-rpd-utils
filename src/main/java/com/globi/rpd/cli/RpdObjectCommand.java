package com.globi.rpd.cli;

public interface RpdObjectCommand<R, T> {

	R execute(T input);

}
