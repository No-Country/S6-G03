package com.nocountry.dto.response;

import lombok.Data;

@Data
public class ImageResponse {

	private String id;
	private String originalName;
	private String imageName;
	private String path;
	private String creationDate;
	private String updateDate;
}