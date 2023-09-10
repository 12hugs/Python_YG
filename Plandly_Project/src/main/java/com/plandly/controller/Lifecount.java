package com.plandly.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Lifecount {

	public String counting(String time) {
		
		String liveDate = time;
        LocalDate birthDate = LocalDate.parse(liveDate); // 문자열을 LocalDate로 변환

        LocalDateTime birthDateTime = birthDate.atStartOfDay(); // 시작 날짜와 시간 설정
        LocalDateTime nowDateTime = LocalDateTime.now(); // 현재 날짜와 시간 가져오기

        Duration durationLived = Duration.between(birthDateTime, nowDateTime); // 두 날짜 사이의 기간(시간 단위)

        long totalSecondsLived = durationLived.getSeconds(); // 전체 초

        long daysLived = totalSecondsLived / (24 * 60 * 60);
        long hoursLived = (totalSecondsLived % (24 * 60 * 60)) / (60 * 60);
        long minutesLived = ((totalSecondsLived % (24 * 60 * 60)) % (60 * 60)) / 600;
		long secondsLived = ((totalSecondsLived % (24 * 60 * 60)) % (60 * 60)) / 600;

		System.out.println("살아온 시간 : " + daysLived + "일 " + hoursLived + "시 "
				+ minutesLived + "분 " + secondsLived + "초 ");

		long dieDays=31025;
		long dieHours=0; 
		long dieMinutes=0; 
		long dieSeconds=0; 
		
	    long totalDieInSeconds=((dieDays*24+dieHours)*3600)+(dieMinutes*3600)+dieSeconds; 
	    
	    if(totalDieInSeconds > totalSecondsLived){
	        Duration remainingDuration=Duration.ofMillis((totalDieInSeconds-totalSecondsLived)*1000);

			long remainingDaysPart = remainingDuration.toDaysPart();
			long remainingHoursPart = remainingDuration.toHoursPart();
			long remainingMinutesPart =
				((remainingDuration.getSeconds() % (24 * 3600)) % 3600) / 600;
			long remainingSecondsPart =
				((remainingDuration.getSeconds() % (24 * 3600)) % 3600) /600;

	        System.out.println("남은 시간: "+remainingDaysPart+"일 "+remainingHoursPart+"시 "
				          +(remainingMinutesPart)+"분 "+(remainingSecondsPart)+"초");
	        
	        String result = String.format("%d-%02d-%02d-%02d",
	        		remainingDaysPart,
	        	    remainingHoursPart,
	        	    remainingMinutesPart,
	        	    remainingSecondsPart);
	        
	        return result;
	    }else{
	       System.out.println("Time is over");
	       return "";
	    }
	}
	
}
