/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.emed.ch.models.common;

import org.husky.common.hl7cdar2.PQ;
import org.husky.emed.ch.enums.UnitCode;

import java.util.Objects;

/**
 * A physical quantity (PQ, QTY) with a unit of presentation.
 *
 * @author Quentin Ligier
 */
public class QuantityWithUnitCode {

    /**
     * The quantity value.
     */
    private String value;

    /**
     * The quantity unit.
     */
    private UnitCode unit;

    /**
     * Constructor.
     *
     * @param value The quantity value.
     * @param unit The quantity unit.
     */
    public QuantityWithUnitCode(final String value,
                                final UnitCode unit) {
        this.value = Objects.requireNonNull(value);
        this.unit = Objects.requireNonNull(unit);
    }

    /**
     * Constructs a new instance from an HL7 PQ instance.
     *
     * @param qty The {@link PQ} to convert.
     */
    public static QuantityWithUnitCode fromPq(final PQ qty) {
        Objects.requireNonNull(qty, "qty shall not be null in fromPq()");
        if (!UnitCode.isInValueSet(qty.getUnit())) {
            throw new IllegalArgumentException("The quantity unit '" + qty.getUnit() + "' is invalid");
        }
        return new QuantityWithUnitCode(
                Objects.requireNonNull(qty.getValue()),
                Objects.requireNonNull(UnitCode.getEnum(qty.getUnit()))
        );
    }

    public String getValue() {
        return this.value;
    }

    public UnitCode getUnit() {
        return this.unit;
    }

    public void setValue(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public void setUnit(final UnitCode unit) {
        this.unit = Objects.requireNonNull(unit);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final QuantityWithUnitCode that)) return false;
        return Objects.equals(value, that.value)
                && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return "QuantityWithUnitCode{" +
                "value='" + this.value + '\'' +
                ", unit=" + this.unit +
                '}';
    }
}