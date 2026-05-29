package com.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentRequestDto {

    @Schema(example = "1")
    private Long patientId;

    @Schema(example = "1")
    private Long doctorId;

    @Schema(example = "2025-06-10T10:00:00")
    private LocalDateTime slotDateTime;
}
