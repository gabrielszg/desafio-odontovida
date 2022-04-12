package br.com.teste.util.date;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateValidator {
	
	public static LocalDate dateToLocalDate(Date dataNascimento) {
		return dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static int getAge(Date dataNascimento) {
		Period periodo = Period.between(dateToLocalDate(dataNascimento), LocalDate.now());
		return periodo.getYears();
	}
}
