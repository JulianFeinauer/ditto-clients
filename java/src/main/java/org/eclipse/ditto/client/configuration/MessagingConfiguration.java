/*
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.ditto.client.configuration;

import java.net.URI;
import java.util.Optional;

import org.eclipse.ditto.model.base.json.JsonSchemaVersion;

/**
 * Provides configuration for messaging.
 *
 * @since 1.0.0
 */
public interface MessagingConfiguration {

    /**
     * Returns the JSON schema version to use for messaging.
     *
     * @return the schema version.
     */
    JsonSchemaVersion getJsonSchemaVersion();

    /**
     * Returns the Ditto endpoint URI to use for messaging.
     *
     * @return the URI.
     */
    URI getEndpointUri();

    /**
     * @return {@code true} if client should try to reconnect when connection is lost.
     */
    boolean isReconnectEnabled();

    /**
     * Returns the proxy configuration.
     *
     * @return the configuration or an empty optional.
     */
    Optional<ProxyConfiguration> getProxyConfiguration();

    /**
     * Returns the trust store configuration.
     *
     * @return the configuration or an empty optional.
     */
    Optional<TrustStoreConfiguration> getTrustStoreConfiguration();

    /**
     * Builder for creating an instance of {@code MessagingConfiguration} by utilizing Object Scoping and Method
     * Chaining.
     */
    interface Builder {

        /**
         * Sets the {@code JSON schema version}.
         * <p>
         * Default is {@link org.eclipse.ditto.model.base.json.JsonSchemaVersion#LATEST}.
         *
         * @param jsonSchemaVersion the schema version to set.
         * @return this builder.
         */
        Builder jsonSchemaVersion(JsonSchemaVersion jsonSchemaVersion);

        /**
         * Sets the {@code endpoint}.
         *
         * @param endpoint the endpoint to set.
         * @return this builder.
         */
        Builder endpoint(String endpoint);

        /**
         * Sets if {@code reconnectEnabled}.
         * <p> Default is enabled. If a connection was established once, the client tries to reconnect <em>every 5
         * seconds</em>.
         *
         * @param reconnectEnabled enables/disables reconnect.
         * @return this builder.
         */
        Builder reconnectEnabled(boolean reconnectEnabled);

        /**
         * Sets the {@code proxyConfiguration}.
         *
         * @param proxyConfiguration the proxy configuration to set.
         * @return this builder.
         */
        Builder proxyConfiguration(ProxyConfiguration proxyConfiguration);

        /**
         * Sets the {@code trustStoreConfiguration}.
         *
         * @param trustStoreConfiguration the trust store configuration to set.
         * @return this builder.
         */
        Builder trustStoreConfiguration(TrustStoreConfiguration trustStoreConfiguration);

        /**
         * Creates a new instance of {@code MessagingConfiguration}.
         *
         * @return a new {@code MessagingConfiguration} object based on the arguments provided to this builder.
         */
        MessagingConfiguration build();

    }

}
