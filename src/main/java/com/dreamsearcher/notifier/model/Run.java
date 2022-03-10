package com.dreamsearcher.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Run
{
    private String runId;
    private Timestamp dateTime;
    private String shopName;
    private Boolean isProcessed;
}
