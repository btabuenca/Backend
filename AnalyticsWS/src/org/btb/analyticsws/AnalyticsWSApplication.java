/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class AnalyticsWSApplication extends ResourceConfig {

    public AnalyticsWSApplication() {
        register(JacksonFeature.class);
        packages("org.btb.analyticsws.jaxb.resources");
    }
}