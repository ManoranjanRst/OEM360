package com.sbi.oem.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class ByteArrayMultipartFile implements MultipartFile {
	private final byte[] content;
	private final String name;
	private final String originalFilename;
	private final String contentType;

	public ByteArrayMultipartFile(byte[] content, String name, String originalFilename, String contentType) {
		this.content = content;
		this.name = name;
		this.originalFilename = originalFilename;
		this.contentType = contentType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getOriginalFilename() {
		return originalFilename;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return content.length == 0;
	}

	@Override
	public long getSize() {
		return content.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return content;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(content);
	}

	@Override
	public void transferTo(java.nio.file.Path dest) throws IOException, IllegalStateException {
		// Implement this method if needed
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		// TODO Auto-generated method stub

	}

}