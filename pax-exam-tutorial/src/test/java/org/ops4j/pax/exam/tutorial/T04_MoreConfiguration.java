package org.ops4j.pax.exam.tutorial;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Bundle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ops4j.pax.exam.Inject;
import org.ops4j.pax.exam.Option;
import static org.ops4j.pax.exam.container.def.PaxRunnerOptions.*;
import static org.ops4j.pax.exam.CoreOptions.*;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

/**
 * @author Toni Menzel (tonit)
 * @since Mar 3, 2009
 */
@RunWith( JUnit4TestRunner.class )
public class T04_MoreConfiguration
{

    @Inject
    BundleContext bundleContext = null;

    // you get that because you "installed" a log profile in configuration.
    public Log logger = LogFactory.getLog( T04_MoreConfiguration.class );

    /*
     * You can configure all kinds of stuff.
     * You will learn about most of it on the project wiki.
     * Here's a typical example:
     * - add a log service to your runtime
     * - add custom bundles via the mvn handler
     * 
     */
    @Configuration
    public static Option[] configure()
    {
        return options(
            // install log service using pax runners profile abstraction (there are more profiles, like DS)
            logProfile(),
          
            // a maven dependency. This must be a bundle already.
            mavenBundle().groupId( "org.ops4j.pax.url" ).artifactId( "pax-url-mvn" ).version( "0.4.0" ),

            // a maven dependency. OSGi meta data (pacakge exports/imports) are being generated by bnd automatically.
            wrappedBundle(
                mavenBundle().groupId( "org.ops4j.base" ).artifactId( "ops4j-base-util" ).version( "0.5.3" )
            )

        );
    }

    @Test
    public void helloAgain()
    {
        logger.trace( "******** This a trace from OSGi" );
        logger.debug( "******** This a debug from OSGi" );
        logger.info( "******** This a info from OSGi" );
        logger.warn( "******** This a warn from OSGi" );
        logger.error( "******** This a errory from OSGi" );

        System.out.println( "This is running inside Felix. With all configuration set up like you specified. " );
        for( Bundle b : bundleContext.getBundles() )
        {
            System.out.println( "Bundle " + b.getBundleId() + " : " + b.getSymbolicName() );
        }

    }
}
