package com.court.manager.common;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;


@Component
public class Utils {

	public static LocalTime convertToLocalTime(String strTime) {
		return LocalTime.parse(strTime, DateTimeFormatter.ofPattern("hh:mm a"));
	}

	public static  LocalDate convertToLocalDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
}
