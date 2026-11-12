package com.example.projeto_escola.configs.advices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.projeto_escola.configs.exceptions.RegraNegocioException;
import com.example.projeto_escola.dtos.ApiErrorDTO;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {
    @ExceptionHandler(RegraNegocioException.class)
    public ApiErrorDTO handleRegraNegocioException(
            RegraNegocioException ex) {
        String msg = ex.getMessage();
        return new ApiErrorDTO(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorDTO handleBeanValidationException(
            MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(
                        Collectors.toList());

        return new ApiErrorDTO(erros);
    }

}
