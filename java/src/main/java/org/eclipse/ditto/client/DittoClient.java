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
package org.eclipse.ditto.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.ditto.client.live.Live;
import org.eclipse.ditto.client.twin.Twin;
import org.eclipse.ditto.protocoladapter.Adaptable;

/**
 * The client API for Eclipse Ditto Client acting as the entry point for getting access to {@link Twin} and {@link Live}
 * singletons that provide the necessary functionality to manage and monitor various entities of Eclipse Ditto.
 *
 * @since 1.0.0
 */
public interface DittoClient {

    /**
     * Returns the client's {@link Twin} singleton which provides the necessary functionality to manage and monitor
     * {@link org.eclipse.ditto.model.things.Thing}s.
     *
     * @return the Twin handle
     */
    Twin twin();

    /**
     * Returns the client's {@link Live} singleton which provides the necessary functionality to manage and monitor
     * {@link org.eclipse.ditto.model.things.Thing}s.
     *
     * @return the Live handle
     */
    Live live();

    /**
     * Initiates termination of this {@code DittoClient} client and waits for its graceful shutdown.
     */
    void destroy();

    /**
     * Directly sends a Ditto Protocol {@link Adaptable} message to the established Ditto backend connection.
     *
     * @param dittoProtocolAdaptable the adaptable to send
     * @return a CompletableFuture containing the correlated response to the sent {@code dittoProtocolAdaptable}
     * @throws IllegalStateException when no twin/live connection was configured for this client
     */
    CompletableFuture<Adaptable> sendDittoProtocol(Adaptable dittoProtocolAdaptable);

}
