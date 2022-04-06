<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="messagesAndErrors" class="col-xs-12">
   <s:if test="hasActionErrors()">
      <div class="alert alert-danger struts2">
         <s:iterator value="actionErrors">
            <i class="fa fa-times-circle-o"></i>
            <s:property escape="false" />
            <br />
         </s:iterator>
         <s:fielderror />
      </div>
   </s:if>
   <s:if test="hasFieldErrors()">
      <div class="alert alert-danger struts2">
         <s:iterator value="fieldErrors">
            <i class="fa fa-times-circle-o"></i>
            <s:property escape="false" />
            <br />
         </s:iterator>
      </div>
   </s:if>
   <s:if test="hasActionMessages()">
      <div class="alert alert-info struts2">
         <s:iterator value="actionMessages">
            <i class="fa fa-info-circle"></i>
            <s:property escape="false" />
            <br />
         </s:iterator>
      </div>
   </s:if>
</div>