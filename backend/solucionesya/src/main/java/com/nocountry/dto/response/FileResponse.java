package com.nocountry.dto.response;

import lombok.Data;

@Data
public class FileResponse {

	private String id;
	private String originalName;
	private String fileName;
	private String path;
	private String creationDate;
	private String updateDate;
}