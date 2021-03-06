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
package com.github.rulegin.dao.timeseries;

import com.github.rulegin.common.data.id.EntityId;
import com.github.rulegin.common.data.kv.TsKvEntry;
import com.github.rulegin.common.data.kv.TsKvQuery;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.List;

/**
 * @author Andrew Shvayka
 */
public interface TimeseriesService {

    ListenableFuture<List<TsKvEntry>> findAll(EntityId entityId, List<TsKvQuery> queries);

    ListenableFuture<List<TsKvEntry>> findLatest(EntityId entityId, Collection<String> keys);

    ListenableFuture<List<TsKvEntry>> findAllLatest(EntityId entityId);

    ListenableFuture<List<Void>> save(EntityId entityId, TsKvEntry tsKvEntry);

    ListenableFuture<List<Void>> save(EntityId entityId, List<TsKvEntry> tsKvEntry, long ttl);
}
