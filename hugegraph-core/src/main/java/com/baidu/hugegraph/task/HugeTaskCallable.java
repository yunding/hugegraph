/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.task;

import java.util.concurrent.Callable;

public abstract class HugeTaskCallable<V> implements Callable<V> {

    private HugeTaskScheduler scheduler = null;
    private HugeTask<V> task = null;

    public HugeTaskCallable() {
        // pass
    }

    protected void scheduler(HugeTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public HugeTaskScheduler scheduler() {
        return this.scheduler;
    }

    protected void task(HugeTask<V> task) {
        this.task = task;
    }

    public HugeTask<V> task() {
        return this.task;
    }

    @SuppressWarnings("unchecked")
    public static <V> HugeTaskCallable<V> fromClass(String className) throws
        ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        return (HugeTaskCallable<V>) clazz.newInstance();
    }

    public static <V> HugeTaskCallable<V> empty(Exception e) {
        return new HugeTaskCallable<V>() {

            @Override
            public V call() throws Exception {
                throw e;
            }
        };
    }
}