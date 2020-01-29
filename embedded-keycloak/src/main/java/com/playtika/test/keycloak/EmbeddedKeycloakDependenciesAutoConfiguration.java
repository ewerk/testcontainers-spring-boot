/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Playtika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.playtika.test.keycloak;

import static com.playtika.test.keycloak.KeycloakProperties.BEAN_NAME_EMBEDDED_KEYCLOAK;

import com.playtika.test.common.spring.DependsOnPostProcessor;
import org.keycloak.adapters.springboot.KeycloakAutoConfiguration;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureOrder
@ConditionalOnClass(KeycloakAutoConfiguration.class)
@ConditionalOnProperty(name = "embedded.keycloak.enabled", matchIfMissing = true)
@AutoConfigureAfter(KeycloakAutoConfiguration.class)
public class EmbeddedKeycloakDependenciesAutoConfiguration {

    @Bean
    public BeanFactoryPostProcessor keycloakSpringBootConfigResolverDependencyPostProcessor() {
        return new DependsOnPostProcessor(KeycloakSpringBootConfigResolver.class,
            new String[]{BEAN_NAME_EMBEDDED_KEYCLOAK});
    }
}