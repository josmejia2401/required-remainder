package com.josmejia2401.requiredremainder.adapter.in.web;

import com.josmejia2401.requiredremainder.adapter.in.web.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    @Test
    void testHandleValidationException() {

        FieldError fieldError1 = new FieldError("obj", "field1", "must not be null");
        FieldError fieldError2 = new FieldError("obj", "field2", "must be valid");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError1, fieldError2));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("uri=/api/test");

        GlobalExceptionHandler handler = new GlobalExceptionHandler();


        ResponseEntity<ErrorResponse> response = handler.handleValidationException(ex, webRequest);


        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage())
                .contains("field1: must not be null", "field2: must be valid");
        assertThat(response.getBody().getPath()).isEqualTo("/api/test");
    }

    @Test
    void testHandleGenericException() {

        Exception ex = new Exception("Some error");
        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("uri=/api/generic");

        GlobalExceptionHandler handler = new GlobalExceptionHandler();


        ResponseEntity<ErrorResponse> response = handler.handleGenericException(ex, webRequest);


        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("Some error");
        assertThat(response.getBody().getPath()).isEqualTo("/api/generic");
    }
}
