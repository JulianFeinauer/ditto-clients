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
package org.eclipse.ditto.client.management;

import java.util.concurrent.CompletableFuture;

import org.eclipse.ditto.client.options.Option;
import org.eclipse.ditto.client.registration.FeatureChangeRegistration;
import org.eclipse.ditto.client.registration.ThingAttributeChangeRegistration;
import org.eclipse.ditto.client.registration.ThingChangeRegistration;
import org.eclipse.ditto.json.JsonFieldSelector;
import org.eclipse.ditto.model.things.Feature;
import org.eclipse.ditto.model.things.Features;
import org.eclipse.ditto.model.things.Thing;
import org.eclipse.ditto.model.things.WithThingId;

/**
 * A {@code ThingHandle} is the entry point to managing and monitoring a <em>specific</em> {@code Thing}. It can for
 * example be used to manage (create, modify and delete) a Thing's {@code Attributes} and {@code Features}.
 * <p>
 * Additionally, It provides the possibility to monitor a {@code Thing} by registering handlers to be notified about
 * {@code ThingAttributeChange}s and {@code ThingChange}s.
 * </p>
 * <p>
 * Note: All methods returning a {@link CompletableFuture} are executed non-blocking and asynchronously. Therefore,
 * these methods return a {@code CompletableFuture} object that will complete either successfully if the operation was
 * executed and confirmed, or exceptionally with a specific {@link org.eclipse.ditto.model.base.exceptions.DittoRuntimeException}
 * if it was executed but has failed.
 * </p>
 * Example:
 * <pre>
 * DittoClient client = ... ;
 * ThingHandle myThing = client.twin().forId("myThing");
 *
 * // Create a new attribute, define handler for success, and wait for completion
 * myThing.putAttribute(JsonFactory.newPointer("address/city"), "Berlin")
 *    .thenAccept(_void -&gt; LOGGER.info("New attribute created successfully."))
 *    .get(1, TimeUnit.SECONDS); // this will block the current thread!
 *
 * // Register for changes of the Thing
 * myThing.registerForThingChanges("myThingReg", change -&gt; LOGGER.info("change received: {}", change));
 * </pre>
 *
 * @param <F> the type of {@link FeatureHandle} for handling {@code Feature}s
 * @since 1.0.0
 */
public interface ThingHandle<F extends FeatureHandle> extends WithThingId, ThingAttributeManagement,
        ThingAttributeChangeRegistration,
        FeatureChangeRegistration, ThingChangeRegistration {

    /**
     * Creates a new instance of {@link FeatureHandle} which aggregates all operations of an already existing {@link
     * Feature} specified by the given identifier.
     *
     * @param featureId the identifier of the Feature to create the handle for.
     * @return the handle for the provided {@code featureId}.
     * @throws IllegalArgumentException if {@code featureId} is {@code null}.
     */
    F forFeature(String featureId);

    /**
     * Deletes the {@code Thing} object being handled by this {@code ThingHandle}.
     *
     * @param options options to be applied configuring behaviour of this method, see {@link
     * org.eclipse.ditto.client.options.Options}.
     * @return completable future providing the created Thing object or a specific {@link
     * org.eclipse.ditto.model.base.exceptions.DittoRuntimeException} if the operation failed
     */
    CompletableFuture<Void> delete(Option<?>... options);

    /**
     * Retrieve the {@code Thing} object being handled by this {@code ThingHandle}.
     *
     * @return completable future providing the requested {@link Thing} or a specific {@link
     * org.eclipse.ditto.model.base.exceptions.DittoRuntimeException} if the operation failed
     */
    CompletableFuture<Thing> retrieve();

    /**
     * Retrieve the {@code Thing} object being handled by this {@code ThingHandle}.
     *
     * @param fieldSelector a field selector object allowing to select a subset of fields on the Thing to be retrieved.
     * @return completable future providing the requested {@link Thing} or a specific {@link
     * org.eclipse.ditto.model.base.exceptions.DittoRuntimeException} if the operation failed
     */
    CompletableFuture<Thing> retrieve(JsonFieldSelector fieldSelector);

    /**
     * Sets the given {@code Features} to this Thing. All existing Features are replaced.
     *
     * @param features the Features to be set.
     * @param options options to be applied configuring behaviour of this method, see {@link
     * org.eclipse.ditto.client.options.Options}.
     * @return completable future for handling the result of the operation or a specific {@link
     * org.eclipse.ditto.model.base.exceptions.DittoRuntimeException} if the operation failed
     * @throws IllegalArgumentException if {@code features} is {@code null}.
     */
    CompletableFuture<Void> setFeatures(Features features, Option<?>... options);

    /**
     * Updates the given Feature of this Thing or creates a new one if it does not yet exist.
     *
     * @param feature Feature to be set.
     * @param options options to be applied configuring behaviour of this method, see {@link
     * org.eclipse.ditto.client.options.Options}.
     * @return completable future for handling the result of this operation or a specific {@link
     * org.eclipse.ditto.model.base.exceptions.DittoRuntimeException} if the operation failed
     * @throws IllegalArgumentException if {@code feature} is {@code null}.
     */
    CompletableFuture<Void> putFeature(Feature feature, Option<?>... options);

    /**
     * Deletes the Feature by the given identifier from this Thing.
     *
     * @param featureId the identifier of the Feature to be deleted.
     * @param options options to be applied configuring behaviour of this method, see {@link
     * org.eclipse.ditto.client.options.Options}.
     * @return completable future for handling the deletion or a specific {@link org.eclipse.ditto.model.base.exceptions.DittoRuntimeException}
     * if the operation failed
     * @throws IllegalArgumentException if {@code featureId} is {@code null}.
     */
    CompletableFuture<Void> deleteFeature(String featureId, Option<?>... options);

    /**
     * Deletes all Features from this Thing.
     *
     * @param options options to be applied configuring behaviour of this method, see {@link
     * org.eclipse.ditto.client.options.Options}.
     * @return completable future for handling the deletion or a specific {@link org.eclipse.ditto.model.base.exceptions.DittoRuntimeException}
     * if the operation failed
     */
    CompletableFuture<Void> deleteFeatures(Option<?>... options);
}
