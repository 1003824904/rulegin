/**
 * Copyright © 2016-2017 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tb.rulegin.server.actors.service.cluster.discovery;

import lombok.extern.slf4j.Slf4j;
import tb.rulegin.server.gen.discovery.ServerInstanceProtos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tb.rulegin.server.dao.util.MiscUtils;

import javax.annotation.PostConstruct;


@Service
@Slf4j
public class CurrentServerInstanceService implements ServerInstanceService {

    @Value("${rpc.bind_host}")
    private String rpcHost;
    @Value("${rpc.bind_port}")
    private Integer rpcPort;

    private ServerInstance self;

    @PostConstruct
    public void init() {
        log.info("java Initializing..."+this.getClass().getName());
        Assert.hasLength(rpcHost, MiscUtils.missingProperty("rpc.bind_host"));
        Assert.notNull(rpcPort, MiscUtils.missingProperty("rpc.bind_port"));

        self = new ServerInstance(ServerInstanceProtos.ServerInfo.newBuilder().setHost(rpcHost).setPort(rpcPort).setTs(System.currentTimeMillis()).build());
        log.info("Current server instance: [{};{}]", self.getHost(), self.getPort());
    }

    @Override
    public ServerInstance getSelf() {
        return self;
    }
}
