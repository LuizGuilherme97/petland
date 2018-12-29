package br.com.petland.pet;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
	@Id
	private Long id;
	private String name;
	private int age;
	private String sex;

	public static Object fromJson(String json) {
		return new Gson().fromJson(json, Pet.class);
	}	

}
