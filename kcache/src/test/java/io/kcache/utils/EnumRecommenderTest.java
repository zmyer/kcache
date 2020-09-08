/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.kcache.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.config.ConfigDef.Recommender;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnumRecommenderTest {

    enum TestEnum {
        ONE, TWO, THREE;
    }

    @Test
    public void visible() {
        Recommender recommender = new EnumRecommender<>(TestEnum.class, String::toLowerCase);
        final List<Object> actual = recommender.validValues("asdf", ImmutableMap.of());
        final List<Object> expected = ImmutableList.copyOf(
            Arrays.stream(TestEnum.values())
                .map(Enum::toString)
                .map(String::toLowerCase)
                .toArray()
        );
        assertEquals(expected, actual);
    }
}