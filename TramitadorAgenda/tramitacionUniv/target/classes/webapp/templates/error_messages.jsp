<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


   <s:if test="hasActionErrors()">
      <div class="alert alert-danger struts2 w-100">
         <em class="fas fa-times-circle"></em>
         <s:text name="error.guardar"/>
         <ul>
         <s:iterator value="actionErrors">
            <li>
            <s:property escape="false" />
            </li>
         </s:iterator>
         <s:iterator value="fieldErrors">
            <s:iterator value="value">
                <li>
                <s:property escape="false" />
                </li>
            </s:iterator>
         </s:iterator>
         </ul>
      </div>
   </s:if>
   <s:if test="hasActionMessages()">
      <div class="alert alert-info struts2">
         <s:iterator value="actionMessages">
            <em class="fas fa-info-circle"></em>
            <s:property escape="false" />
            <br />
         </s:iterator>
      </div>
   </s:if>