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
package cn.neucloud.server.actors.service.cluster.discovery;

import cn.neucloud.server.actors.service.cluster.ServerAddress;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.neurule.server.gen.discovery.ServerInstanceProtos;


@ToString
@EqualsAndHashCode(exclude = {"serverInfo", "serverAddress"})
public final class ServerInstance implements Comparable<ServerInstance> {

    @Getter(AccessLevel.PACKAGE)
    private final ServerInstanceProtos.ServerInfo serverInfo;
    @Getter
    private final String host;
    @Getter
    private final int port;
    @Getter
    private final ServerAddress serverAddress;

    public ServerInstance(ServerInstanceProtos.ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
        this.host = serverInfo.getHost();
        this.port = serverInfo.getPort();
        this.serverAddress = new ServerAddress(host, port);
    }

    @Override
    public int compareTo(ServerInstance o) {
        return this.serverAddress.compareTo(o.serverAddress);
    }
}
