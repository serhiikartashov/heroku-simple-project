package org.example;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.realm.JDBCRealm;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.scan.StandardJarScanFilter;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Launcher {

    private static Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) throws Exception {

        File root = getRootFolder();
        System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
        Tomcat tomcat = new Tomcat();
        Path tempPath = Files.createTempDirectory("tomcat-base-dir");
        tomcat.setBaseDir(tempPath.toString());

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.parseInt(webPort));
        // connector configurations
        // https://www.eclipse.org/virgo/documentation/virgo-documentation-3.6.4.RELEASE/docs/virgo-user-guide/html/ch13s07.html
        tomcat.getConnector();

        File webContentFolder = new File(root.getAbsolutePath(), "target/classes");
        if (!webContentFolder.exists()) {
            webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
        }
        StandardContext ctx = (StandardContext) tomcat.addWebapp("", webContentFolder.getAbsolutePath());
        //Set execution independent of current thread context classloader (compatibility with exec:java mojo)
        ctx.setParentClassLoader(Launcher.class.getClassLoader());
        // hide warning message
        StandardJarScanFilter jarScanFilter = new StandardJarScanFilter();
        jarScanFilter.setDefaultPluggabilityScan(false);
        jarScanFilter.setDefaultTldScan(false);
        StandardJarScanner standardJarScanner = new StandardJarScanner();
        standardJarScanner.setJarScanFilter(jarScanFilter);
        ctx.setJarScanner(standardJarScanner);
        log.info("configuring app with basedir: " + webContentFolder.getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClassesFolder = new File(root.getAbsolutePath(), "target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);

        WebResourceSet resourceSet;
        if (additionWebInfClassesFolder.exists()) {
            resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClassesFolder.getAbsolutePath(), "/");
            log.info("loading WEB-INF resources from as '" + additionWebInfClassesFolder.getAbsolutePath() + "'");
        } else {
            resourceSet = new EmptyResourceSet(resources);
        }
        resources.addPreResources(resourceSet);
        ctx.setResources(resources);

        serverConfiguration(ctx, tomcat);

        tomcat.start();
        tomcat.getServer().await();
    }

    /** YOUR CHANGES HERE **/
    private static void serverConfiguration(StandardContext ctx, Tomcat tomcat) throws URISyntaxException {
        // server configs
        tomcat.getConnector().setAttribute("maxThreads", 200);

        // https://tomcat.apache.org/tomcat-9.0-doc/realm-howto.html
        // https://tomcat.apache.org/tomcat-9.0-doc/config/realm.html
        // jdbc realm configuration
        JDBCRealm jdbcRealm = new JDBCRealm();
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        log.debug(dbUri.toString());
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String connectionURL = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        log.debug(connectionURL);
        jdbcRealm.setConnectionURL(connectionURL);
        jdbcRealm.setConnectionName(username);
        jdbcRealm.setConnectionPassword(password);
        jdbcRealm.setDriverName("org.postgresql.Driver");
        jdbcRealm.setUserTable("users");
        jdbcRealm.setUserNameCol("user_name");
        jdbcRealm.setUserCredCol("user_passwd");
        jdbcRealm.setUserRoleTable("user_roles");
        jdbcRealm.setRoleNameCol("role_name");

        // custom realm
        // https://dzone.com/articles/how-to-implement-a-new-realm-in-tomcat

        // LockOutRealm - provide a user lock out mechanism if there are
        // too many failed authentication attempts in a given period of time.
        ctx.setRealm(jdbcRealm);

        // https://docs.oracle.com/cd/E19226-01/820-7627/bncbn/index.html
        LoginConfig loginConfig = new LoginConfig();
        // FORM (jsp) - https://stackoverflow.com/questions/11382159/tomcat-7-form-based-authentification
        // FORM (html) - https://docs.oracle.com/cd/E19226-01/820-7627/bncby/index.html
        // Digest - https://docs.oracle.com/cd/E19226-01/820-7627/bncbw/index.html
        // CLIENT-CERT - https://docs.oracle.com/cd/E19226-01/820-7627/bncbs/index.html
        loginConfig.setAuthMethod("BASIC");
//        loginConfig.setLoginPage("");
//        loginConfig.setErrorPage("");
        ctx.setLoginConfig(loginConfig);

        //

        // context configs
        ctx.setSessionTimeout(30);
    }

    private static File getRootFolder() {
        try {
            File root;
            String runningJarPath = Launcher.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
            int lastIndexOf = runningJarPath.lastIndexOf("/target/");
            if (lastIndexOf < 0) {
                root = new File("");
            } else {
                root = new File(runningJarPath.substring(0, lastIndexOf));
            }
            log.info("application resolved root folder: " + root.getAbsolutePath());
            return root;
        } catch (URISyntaxException ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}
