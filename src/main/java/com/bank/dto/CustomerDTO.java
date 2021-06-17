package com.bank.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	@NotNull
	@Min(1)
	private Integer custId;

	@NotEmpty
	@Size(min=4,max=255)
	private String address;
	@NotEmpty
	@Size(min=4,max=255)
	private String email;
	@NotEmpty
	@Size(min=1,max=255)
	private String enable;
	@NotEmpty
	@Size(min=4,max=255)
	private String name;
	@NotEmpty
	@Size(min=4,max=255)
	private String phone;

	private String token;
	
	@NotNull
	private Integer dotyId;
	
}
