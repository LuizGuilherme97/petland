package br.com.petland.pet;

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

}
