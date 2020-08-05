package cn.infisa.tools.elytron;

import lombok.extern.slf4j.Slf4j;
import org.jboss.as.quickstarts.ejb_security_programmatic_auth.SecuredEJBRemote;
import org.junit.Test;
import org.wildfly.security.WildFlyElytronProvider;
import org.wildfly.security.auth.client.AuthenticationConfiguration;
import org.wildfly.security.auth.client.AuthenticationContext;
import org.wildfly.security.auth.client.MatchRule;
import org.wildfly.security.sasl.SaslMechanismSelector;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.security.Provider;
import java.util.Hashtable;
import java.util.concurrent.Callable;

/**
    * @Title: TestClients
    * @Description: 
    * @author libo
    * @date 2020/8/3 20:59
    * @Version 1.0
    */
@Slf4j
public class TestClients {
    @Test
    public void test1(){
        AuthenticationConfiguration adminConfig =
                AuthenticationConfiguration.EMPTY
                        .useProviders(() -> new Provider[] { new WildFlyElytronProvider() })
//                        .allowSaslMechanisms("DIGEST-MD5")
                        .useRealm("ManagementRealm")
                        .useName("administrator")
                        .usePassword("password1!");
        //create your authentication configuration
        AuthenticationConfiguration commonConfig =
                AuthenticationConfiguration.EMPTY
                        .useProviders(() -> new Provider[] { new WildFlyElytronProvider() })
//                        .allowSaslMechanisms("DIGEST-MD5")
                        .useRealm("ManagementRealm");

        AuthenticationConfiguration administrator =
                commonConfig
                        .useName("administrator")
                        .usePassword("password1!");


        AuthenticationConfiguration monitor =
                commonConfig
                        .useName("monitor")
                        .usePassword("password1!");


        //create your authentication context
        AuthenticationContext context = AuthenticationContext.empty();
        context = context.with(MatchRule.ALL.matchHost("192.168.1.217"), administrator);
        context = context.with(MatchRule.ALL.matchHost("192.168.1.217"), monitor);
        log.info(context.toString());
        Runnable runnable =
                new Runnable() {
                    public void run() {
                        try {
                            //Establish your connection and do some work

                            log.info(this.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

        //use your authentication context to run your client
        context.run(runnable);
    }
    @Test
    public void test2() throws Exception {
        AuthenticationConfiguration common = AuthenticationConfiguration.empty().setSaslMechanismSelector(SaslMechanismSelector.NONE.addMechanism("DIGEST-MD5"));
        AuthenticationConfiguration quickstartUser = common.useName("quickstartUser").usePassword("quickstartPwd1!");
        final AuthenticationContext authCtx1 = AuthenticationContext.empty().with(MatchRule.ALL, quickstartUser);

        System.out.println(authCtx1.runCallable(callable));

        AuthenticationConfiguration superUser = common.useName("quickstartAdmin").usePassword("adminPwd1!");
        final AuthenticationContext authCtx2 = AuthenticationContext.empty().with(MatchRule.ALL, superUser);

        System.out.println(authCtx2.runCallable(callable));
    }
    /**WildFlyInitialContextFactory
     * A {@code Callable} that looks up the remote EJB and invokes its methods.
     */
    static final Callable<String> callable = () -> {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://192.168.1.217:8080");
        final Context context = new InitialContext(jndiProperties);

        SecuredEJBRemote reference = (SecuredEJBRemote) context.lookup("ejb:/ejb-security-programmatic-auth/SecuredEJB!"
                + SecuredEJBRemote.class.getName());

        StringBuilder builder = new StringBuilder();
        builder.append("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");
        builder.append("Called secured bean, caller principal " + reference.getSecurityInformation());
        boolean hasAdminPermission = false;
        try {
            hasAdminPermission = reference.administrativeMethod();
        } catch (EJBAccessException e) {
        }
        builder.append("\n\nPrincipal has admin permission: " + hasAdminPermission);
        builder.append("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");
        return builder.toString();
    };


}
