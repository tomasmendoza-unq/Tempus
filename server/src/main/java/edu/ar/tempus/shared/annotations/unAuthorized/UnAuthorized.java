package edu.ar.tempus.shared.annotations.unAuthorized;

import edu.ar.tempus.controller.exceptions.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponse(
        responseCode = "401",
        description = "Las credenciales proporcionadas son inválidas.",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDTO.class)
        )
)
public @interface UnAuthorized {
}
