/*
 * Copyright 2008 Alin Dreghiciu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.exam.container.def.options;

import static org.ops4j.lang.NullArgumentException.*;
import org.ops4j.pax.exam.options.ProvisionOption;
import org.ops4j.pax.exam.options.UrlProvisionOption;
import static org.ops4j.pax.runner.provision.ServiceConstants.*;
import static org.ops4j.pax.runner.scanner.dir.ServiceConstants.*;

/**
 * Option specifying provision form an Pax Runner File scanner.
 *
 * @author Alin Dreghiciu (adreghiciu@gmail.com)
 * @since 0.3.0, December 17, 2008
 */
public class FileScannerProvisionOption
    extends AbstractScannerProvisionOption<FileScannerProvisionOption>
{

    /**
     * Provision url (cannot be null or empty).
     */
    private final ProvisionOption m_url;

    /**
     * Constructor.
     *
     * @param url provision url (cannot be null or empty)
     *
     * @throws IllegalArgumentException - If url is null or empty
     */
    public FileScannerProvisionOption( final String url )
    {
        validateNotEmpty( url, true, "URL" );
        m_url = new UrlProvisionOption( url );
    }

    /**
     * Constructor.
     *
     * @param url provision url (cannot be null or a {@link ScannerProvisionOption})
     *
     * @throws IllegalArgumentException - If url is null or is an {@link ScannerProvisionOption}
     */
    public FileScannerProvisionOption( final ProvisionOption url )
    {
        validateNotNull( url, "URL" );
        if( url instanceof ScannerProvisionOption )
        {
            throw new IllegalArgumentException( "URL cannot be an " + ScannerProvisionOption.class.getSimpleName() );
        }
        m_url = url;
    }

    /**
     * {@inheritDoc}
     */
    public String getURL()
    {
        return new StringBuilder()
            .append( SCHEMA )
            .append( SEPARATOR_SCHEME )
            .append( m_url.getURL() )
            .append( getOptions() )
            .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append( "FileScannerProvisionOption" );
        sb.append( "{url='" ).append( getURL() ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    FileScannerProvisionOption itself()
    {
        return this;
    }

}