package org.ops4j.pax.exam.samples.simple;

import org.ops4j.pax.exam.api.TestRunnerConnector;
import static org.ops4j.pax.exam.connector.paxrunner.GenericConnector.create;
import static org.ops4j.pax.exam.connector.paxrunner.GenericConnector.createBundleProvision;
import org.ops4j.pax.exam.connector.paxrunner.Platforms;
import org.ops4j.pax.exam.spi.junit.MultiConnectorPaxExamTestCase;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;

/**
 * This test compares felix and equinox directly in terms of compatibilty with pax logging support.
 *
 * @author Toni Menzel (tonit)
 * @since Nov 11, 2008
 */
public class FelixVsEquinoxPaxLoggingTest extends MultiConnectorPaxExamTestCase
{

    protected TestRunnerConnector[] configure()
    {
        return new TestRunnerConnector[]{
            create( createBundleProvision()
                .addBundle( "mvn:org.ops4j.pax.logging/pax-logging-api" )
                .addBundle( "mvn:org.ops4j.pax.logging/pax-logging-service" )
            ).setPlatform( Platforms.FELIX ),
            create( createBundleProvision()
                .addBundle( "mvn:org.ops4j.pax.logging/pax-logging-api" )
                .addBundle( "mvn:org.ops4j.pax.logging/pax-logging-service" )
            ).setPlatform( Platforms.EQUINOX )
        };
    }

    public void testServiceExposed()
    {
        ServiceReference ref = bundleContext.getServiceReference( LogService.class.getName() );
        assertNotNull( "LogService should be exposed", ref );

    }

}