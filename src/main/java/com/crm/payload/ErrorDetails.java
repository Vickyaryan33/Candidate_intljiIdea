package com.crm.payload;

import lombok.Data;
import java.util.Date;

@Data
public class ErrorDetails {
    private String message;
    private String request;
    private Date date;

    public ErrorDetails(Date date, String message , String request) {
        this .date =date ;
        this.message = message;
        this.request=request;
    }


}
