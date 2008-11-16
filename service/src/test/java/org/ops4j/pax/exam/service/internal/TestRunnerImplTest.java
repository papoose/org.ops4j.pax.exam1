/*
 * Copyright 2008 Toni Menzel
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.exam.service.internal;

import static org.easymock.EasyMock.*;
import org.junit.Test;
import org.ops4j.pax.exam.api.TestExecutionException;
import org.ops4j.pax.exam.api.TestRunner;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Toni Menzel (tonit)
 * @since Jun 20, 2008
 */
public class TestRunnerImplTest
{

    @Test( expected = IllegalArgumentException.class )
    public void createWithNull()
        throws Exception
    {
        new TestRunnerImpl( null );
    }

    @Test
    public void create()
        throws Exception
    {
        BundleContext bundleContext = createMock( BundleContext.class );
        replay( bundleContext );
        new TestRunnerImpl( bundleContext );
        verify( bundleContext );
    }

    @Test( expected = TestExecutionException.class )
    public void executeWithoutBundlesInstalled()
        throws Exception
    {
        BundleContext bundleContext = createMock( BundleContext.class );
        expect( bundleContext.getBundles() ).andReturn( new Bundle[0] );
        replay( bundleContext );
        TestRunnerImpl act = new TestRunnerImpl( bundleContext );
        act.execute();
        verify( bundleContext );
    }

    @Test( expected = TestExecutionException.class )
    public void executeWithoutValidBundles()
        throws Exception
    {
        BundleContext bundleContext = createMock( BundleContext.class );
        Bundle bundle = createMock( Bundle.class );
        Dictionary dict = new Hashtable();
        expect( bundle.getHeaders() ).andReturn( dict );
        expect( bundleContext.getBundles() ).andReturn( new Bundle[]{ bundle } );
        replay( bundleContext, bundle );
        TestRunnerImpl act = new TestRunnerImpl( bundleContext );
        act.execute();
        verify( bundleContext, bundle );
    }

    //@Test
    public void executeWithValidRecipeHostHeader()
        throws Exception
    {
        BundleContext bundleContext = createMock( BundleContext.class );
        Bundle bundle = createMock( Bundle.class );
        Dictionary dict = new Hashtable();
        dict.put( TestRunner.PROBE_TEST_CASE, "foo" );
        expect( bundle.getHeaders() ).andReturn( dict ).anyTimes();

        expect( bundleContext.getBundles() ).andReturn( new Bundle[]{ bundle } );

        Bundle loadingBundle = createMock( Bundle.class );
        ;
        expect( bundleContext.getBundle() ).andReturn( loadingBundle );

        replay( bundleContext, bundle );
        TestRunnerImpl act = new TestRunnerImpl( bundleContext );
        act.execute();
        verify( bundleContext, bundle );
    }
}