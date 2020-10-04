package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Data;

@Data
public class File {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private byte[] filedata;

    public File(Integer fileId, String filename, String contenttype, String filesize, Integer userid, byte[] filedata) {
        this.contenttype = contenttype;
        this.fileId = fileId;
        this.filename = filename;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

}
