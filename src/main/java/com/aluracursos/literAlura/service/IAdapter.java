package com.aluracursos.literAlura.service;

public interface IAdapter {
    <T> T getDataFromJson(String json, Class<T> clase);

}
