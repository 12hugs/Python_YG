package com.plandly.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Getter
 

public class Plandly_MemberVO {
    @NonNull
    private String email;
    private String pw;
	private String age; 
	private String gender;
	private String nickName;

}
