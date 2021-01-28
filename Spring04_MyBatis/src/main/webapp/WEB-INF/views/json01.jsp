<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
{"num": ${dto.num} ,"name": "${dto.name}" ,"addr": "${dto.addr}" }
<%--
문자열에 ""붙이는 것 잊지말기
--%>