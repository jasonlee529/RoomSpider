package org.jboss.as.quickstarts.ejb_security_programmatic_auth;

/**
    * @Title: d
    * @Description: 
    * @author libo
    * @date 2020/8/4 13:43
    * @Version 1.0
    */
public interface SecuredEJBRemote {

    String getSecurityInformation();

    boolean administrativeMethod();

}
