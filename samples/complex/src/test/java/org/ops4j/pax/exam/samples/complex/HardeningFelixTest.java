package org.ops4j.pax.exam.samples.complex;

import org.ops4j.pax.exam.api.DroneConnector;
import static org.ops4j.pax.exam.connector.paxrunner.GenericConnector.*;
import org.ops4j.pax.exam.connector.paxrunner.Platforms;

/**
 * @author Toni Menzel (tonit)
 * @since Oct 1, 2008
 */
public class HardeningFelixTest extends HardeningTest
{

    protected DroneConnector configure()
    {
        return create( createBundleProvision()
            .addBundle( "mvn:org.ops4j.pax.logging/pax-logging-api" )
            .addBundle( "mvn:org.ops4j.pax.logging/pax-logging-service" )
        ).setPlatform( Platforms.FELIX );
    }
}