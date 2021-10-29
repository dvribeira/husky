package org.ehealth_connector.emed.cda.utils.time;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.primitive.CommonTS;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.ehealth_connector.emed.cda.errors.InvalidEmedContentException;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * HL7 timestamps (data type DTM) with particular precision, normalized to UTC.
 *
 * <p>Original code from IPF (https://github.com/oehf/ipf) under Apache 2.0 licence, modified for the Emed module use.
 *
 * @author Dmytro Rud
 */
public class Hl7Dtm {

    /**
     * The enumeration of possible precisions.
     */
    public enum Precision {
        YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, FRAC_SECOND
    }

    /**
     * Formatters for the different patterns. It is only used when converting to HL7 DTM format, not for parsing.
     */
    private static final Map<Precision, DateTimeFormatter> FORMATTERS = new EnumMap<>(Precision.class);
    static {
        FORMATTERS.put(Precision.YEAR,        DateTimeFormatter.ofPattern("yyyy"));
        FORMATTERS.put(Precision.MONTH,       DateTimeFormatter.ofPattern("yyyyMM"));
        FORMATTERS.put(Precision.DAY,         DateTimeFormatter.ofPattern("yyyyMMdd"));
        FORMATTERS.put(Precision.HOUR,        DateTimeFormatter.ofPattern("yyyyMMddHHxxxx"));
        FORMATTERS.put(Precision.MINUTE,      DateTimeFormatter.ofPattern("yyyyMMddHHmmxxxx"));
        FORMATTERS.put(Precision.SECOND,      DateTimeFormatter.ofPattern("yyyyMMddHHmmssxxxx"));
        FORMATTERS.put(Precision.FRAC_SECOND, DateTimeFormatter.ofPattern("yyyyMMddHHmmss.SSSSxxxx"));
    }

    /**
     * Timestamp.
     */
    private OffsetDateTime dateTime;

    /**
     * Precision of the timestamp (smallest present element, e.g. YEAR for "1980").
     */
    private Precision precision;

    /**
     * Initializes a {@link Hl7Dtm} object with the given datetime and precision.
     */
    public Hl7Dtm(final OffsetDateTime dateTime, final Precision precision) {
        this.dateTime = Objects.requireNonNull(dateTime);
        this.precision = Objects.requireNonNull(precision);
    }

    /**
     * Creates a {@link Hl7Dtm} object from the given string.
     * @param s String of the pattern <code>YYYY[MM[DD[HH[MM[SS[.S[S[S[S]]]]]]]]][+/-ZZZZ]</code>. Milliseconds will
     *          be ignored.
     * @return a {@link Hl7Dtm} object.
     * @throws IllegalArgumentException if the DTM string is invalid.
     */
    public static Hl7Dtm fromHl7(final String s) {
        var pos = Math.max(Objects.requireNonNull(s).indexOf('-'), s.indexOf('+'));
        var len = (pos >= 0) ? pos : s.length();

        // determine precision
        Precision precision;
        if (len >= 16 && len <= 19) {
            precision = Precision.FRAC_SECOND;
        } else if (len == 14) {
            precision = Precision.SECOND;
        } else if (len == 12) {
            precision = Precision.MINUTE;
        } else if (len == 10) {
            precision = Precision.HOUR;
        } else if (len == 8) {
            precision = Precision.DAY;
        } else if (len == 6) {
            precision = Precision.MONTH;
        } else if (len == 4) {
            precision = Precision.YEAR;
        } else {
            throw new IllegalArgumentException("The passed HL7 DTM is invalid");
        }

        // parse timestamp
        return new Hl7Dtm(Objects.requireNonNull(toOffsetDateTime(s)), precision);
    }

    /**
     *
     * @return a {@link Hl7Dtm} with the current date-time in second precision.
     */
    public static Hl7Dtm now() {
        return new Hl7Dtm(OffsetDateTime.now(), Precision.SECOND);
    }

    /**
     * Creates a {@link OffsetDateTime} object from the given string.
     * @param s
     *      String of the pattern <code>YYYY[MM[DD[HH[MM[SS[.S[S[S[S]]]]]]]]][+/-ZZZZ]</code>.
     *      Milliseconds will be ignored.
     * @return
     *      a {@link OffsetDateTime} object, or <code>null</code> if the parameter is {@code null} or empty.
     * @throws InvalidEmedContentException if the DTM string is invalid.
     */
    public static OffsetDateTime toOffsetDateTime(String s) {
        if (s.isBlank()) {
            throw new IllegalArgumentException("The passed HL7 DTM is empty");
        }

        var pos = Math.max(s.indexOf('-'), s.indexOf('+'));

        // default time zone is UTC
        if (pos < 0) {
            s += "+0000";
        }

        // parse timestamp
        final CommonTS ts;
        try {
            ts = new CommonTS(s);
        } catch (final DataTypeException e) {
            throw new InvalidEmedContentException("Unparsable HL7 DTM format", e);
        }
        return OffsetDateTime.of(
                ts.getYear(),
                (ts.getMonth() == 0) ? 1 : ts.getMonth(),
                (ts.getDay() == 0) ? 1 : ts.getDay(),
                ts.getHour(),
                ts.getMinute(),
                ts.getSecond(),
                (int) Math.floor(ts.getFractSecond() * 1_000_000_000),
                ZoneOffset.ofHoursMinutes(
                        ts.getGMTOffset() / 100,
                        ts.getGMTOffset() % 100
                )
        );
    }

    public void setDateTime(final OffsetDateTime dateTime) {
        this.dateTime = Objects.requireNonNull(dateTime);
    }

    public OffsetDateTime getDateTime() {
        return this.dateTime;
    }

    public void setPrecision(final Precision precision) {
        this.precision = Objects.requireNonNull(precision);
    }

    public Precision getPrecision() {
        return this.precision;
    }

    public String toHl7() {
        return FORMATTERS.get(getPrecision()).format(getDateTime());
    }

    /**
     * Two HL7 timestamps are equal when they have the same values in the relevant fields
     * (i.e. in the ones covered by the precision).
     */
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var timestamp = (Hl7Dtm) o;
        return Objects.equals(this.toHl7(), timestamp.toHl7());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, precision);
    }

    @Override
    public String toString() {
        return "Timestamp(" +
                "dateTime=" + dateTime +
                ", precision=" + precision +
                ')';
    }
}
