package com.example.account.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metric {
    private List<MetricsEndpoint.Sample> measurements;


}
