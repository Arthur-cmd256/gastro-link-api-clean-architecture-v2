package br.com.fiap.gastro_link_api_clean_architecture.infra.web.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {
}
