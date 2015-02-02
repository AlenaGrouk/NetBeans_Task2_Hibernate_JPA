<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>


<div class="menuheader">
    <h2 class="menuhead"><bean:message key="menu.header"/></h2>
</div>
<div class="menulist">
    <ul class="menulinks">
        <li class="menuitem"><html:link styleClass="disactive"  styleId="menuLinkList"
                   action="/newsaction.do?action=list">
                <bean:message key="menu.label.list"/></html:link></li>
        <li class="menuitem"><html:link styleClass="disactive" styleId="menuLinkAdd"
                   action="/newsaction.do?action=add">
                <bean:message key="menu.label.add"/></html:link></li>
    </ul>
</div>
