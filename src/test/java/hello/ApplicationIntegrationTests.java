/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import static org.assertj.core.api.Assertions.*;


import io.haos.gant.flights.GetFlightRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTests {

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @LocalServerPort
    private int port = 0;

    @Before
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetFlightRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    @Ignore
    public void testSendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetFlightRequest request = new GetFlightRequest();
        request.setFlightDate("Spain");
        request.setFlightDate("2018-12-10");

        assertThat(ws.marshalSendAndReceive("http://localhost:"
                + port + "/ws", request)).isNotNull();
    }
}