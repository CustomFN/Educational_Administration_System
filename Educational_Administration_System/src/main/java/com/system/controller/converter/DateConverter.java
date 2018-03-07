package com.system.controller.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 参数绑定 字符串日期转换器
 */
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		 //实现 将日期串转成日期类型(格式是yyyy-MM-dd)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//转成直接返回
			return sdf.parse(source);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//如果参数绑定失败返回null
		return null;
	}

}
