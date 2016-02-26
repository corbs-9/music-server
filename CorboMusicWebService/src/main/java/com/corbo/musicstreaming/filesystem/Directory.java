package com.corbo.musicstreaming.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Directory {

	public Directory(String directory) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directory))) {
			directoryName = directory;
			stream.forEach((path) -> {
				File file = path.toFile();
				if (file.isDirectory()) {
					readable = file.canRead();
					writable = file.canWrite();
					getChildDirectories().add(new Directory(file.getPath()));
				} else if (file.isFile()) {
					getFiles().add(file);
				}
			});
		} catch (DirectoryIteratorException ex) {

		} catch (IOException e) {

		}
	}

	private String directoryName;
	private boolean readable;
	private boolean writable;
	private List<Directory> childDirectories;
	private List<File> files;

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public boolean isReadable() {
		return readable;
	}

	public void setReadable(boolean readable) {
		this.readable = readable;
	}

	public boolean isWritable() {
		return writable;
	}

	public void setWritable(boolean writable) {
		this.writable = writable;
	}

	public List<Directory> getChildDirectories() {
		if (childDirectories == null) {
			childDirectories = new ArrayList<Directory>();
		}
		return childDirectories;
	}

	public List<File> getFiles() {
		if (files == null) {
			files = new ArrayList<File>();
		}
		return files;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
