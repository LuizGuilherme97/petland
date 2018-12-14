package br.com.petland.pet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pet {

	private int id;
	private String name;
	private int age;
	private String sex;	

}
