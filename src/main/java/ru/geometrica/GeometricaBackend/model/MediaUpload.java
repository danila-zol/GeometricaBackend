package ru.geometrica.GeometricaBackend.model;

public class MediaUpload extends FileUpload {
	public long[] illustrates;

	public MediaUpload(String file, String name, String type, long[] illustrates) {
		this.file = file;
		this.name = name;
		this.type = type;
		this.illustrates = illustrates;
	}
}
