/*
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.pricer.common;

import java.io.Serializable;
import java.time.Period;
import java.util.Map;
import java.util.NoSuchElementException;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaBean;
import org.joda.beans.MetaProperty;
import org.joda.beans.gen.BeanDefinition;
import org.joda.beans.gen.ImmutablePreBuild;
import org.joda.beans.gen.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.beans.impl.direct.DirectPrivateBeanBuilder;

import com.opengamma.strata.collect.tuple.Pair;
import com.opengamma.strata.market.option.Strike;
import com.opengamma.strata.market.param.ParameterMetadata;

/**
 * Surface node metadata for a generic volatility surface node with a specific period to expiry and strike.
 */
@BeanDefinition(builderScope = "private")
public final class GenericVolatilitySurfacePeriodParameterMetadata
    implements ParameterMetadata, ImmutableBean, Serializable {

  /**
   * The period of the surface node.
   * <p>
   * This is the period to expiry that the node on the surface is defined as.
   * There is not necessarily a direct relationship with a date from an underlying instrument.
   */
  @PropertyDefinition
  private final Period period;
  /**
   * The strike of the surface node.
   * <p>
   * This is the strike that the node on the surface is defined as.
   */
  @PropertyDefinition(validate = "notNull")
  private final Strike strike;
  /**
   * The label that describes the node.
   */
  @PropertyDefinition(validate = "notEmpty", overrideGet = true)
  private final String label;

  //-------------------------------------------------------------------------
  /**
   * Creates node metadata using period and strike.
   * 
   * @param period  the period
   * @param strike  the strike
   * @return node metadata 
   */
  public static GenericVolatilitySurfacePeriodParameterMetadata of(
      Period period,
      Strike strike) {

    String label = Pair.of(period, strike.getLabel()).toString();
    return new GenericVolatilitySurfacePeriodParameterMetadata(period, strike, label);
  }

  /**
   * Creates node using period, strike and label.
   * 
   * @param period  the period
   * @param strike  the strike
   * @param label  the label to use
   * @return the metadata
   */
  public static GenericVolatilitySurfacePeriodParameterMetadata of(
      Period period,
      Strike strike,
      String label) {

    return new GenericVolatilitySurfacePeriodParameterMetadata(period, strike, label);
  }

  @ImmutablePreBuild
  private static void preBuild(Builder builder) {
    if (builder.label == null && builder.strike != null) {
      builder.label = Pair.of(builder.period, builder.strike.getLabel()).toString();
    }
  }

  @Override
  public Pair<Period, Strike> getIdentifier() {
    return Pair.of(period, strike);
  }

  //------------------------- AUTOGENERATED START -------------------------
  /**
   * The meta-bean for {@code GenericVolatilitySurfacePeriodParameterMetadata}.
   * @return the meta-bean, not null
   */
  public static GenericVolatilitySurfacePeriodParameterMetadata.Meta meta() {
    return GenericVolatilitySurfacePeriodParameterMetadata.Meta.INSTANCE;
  }

  static {
    MetaBean.register(GenericVolatilitySurfacePeriodParameterMetadata.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  private GenericVolatilitySurfacePeriodParameterMetadata(
      Period period,
      Strike strike,
      String label) {
    JodaBeanUtils.notNull(strike, "strike");
    JodaBeanUtils.notEmpty(label, "label");
    this.period = period;
    this.strike = strike;
    this.label = label;
  }

  @Override
  public GenericVolatilitySurfacePeriodParameterMetadata.Meta metaBean() {
    return GenericVolatilitySurfacePeriodParameterMetadata.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the period of the surface node.
   * <p>
   * This is the period to expiry that the node on the surface is defined as.
   * There is not necessarily a direct relationship with a date from an underlying instrument.
   * @return the value of the property
   */
  public Period getPeriod() {
    return period;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the strike of the surface node.
   * <p>
   * This is the strike that the node on the surface is defined as.
   * @return the value of the property, not null
   */
  public Strike getStrike() {
    return strike;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the label that describes the node.
   * @return the value of the property, not empty
   */
  @Override
  public String getLabel() {
    return label;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      GenericVolatilitySurfacePeriodParameterMetadata other = (GenericVolatilitySurfacePeriodParameterMetadata) obj;
      return JodaBeanUtils.equal(period, other.period) &&
          JodaBeanUtils.equal(strike, other.strike) &&
          JodaBeanUtils.equal(label, other.label);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(period);
    hash = hash * 31 + JodaBeanUtils.hashCode(strike);
    hash = hash * 31 + JodaBeanUtils.hashCode(label);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("GenericVolatilitySurfacePeriodParameterMetadata{");
    buf.append("period").append('=').append(JodaBeanUtils.toString(period)).append(',').append(' ');
    buf.append("strike").append('=').append(JodaBeanUtils.toString(strike)).append(',').append(' ');
    buf.append("label").append('=').append(JodaBeanUtils.toString(label));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code GenericVolatilitySurfacePeriodParameterMetadata}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code period} property.
     */
    private final MetaProperty<Period> period = DirectMetaProperty.ofImmutable(
        this, "period", GenericVolatilitySurfacePeriodParameterMetadata.class, Period.class);
    /**
     * The meta-property for the {@code strike} property.
     */
    private final MetaProperty<Strike> strike = DirectMetaProperty.ofImmutable(
        this, "strike", GenericVolatilitySurfacePeriodParameterMetadata.class, Strike.class);
    /**
     * The meta-property for the {@code label} property.
     */
    private final MetaProperty<String> label = DirectMetaProperty.ofImmutable(
        this, "label", GenericVolatilitySurfacePeriodParameterMetadata.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "period",
        "strike",
        "label");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -991726143:  // period
          return period;
        case -891985998:  // strike
          return strike;
        case 102727412:  // label
          return label;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends GenericVolatilitySurfacePeriodParameterMetadata> builder() {
      return new GenericVolatilitySurfacePeriodParameterMetadata.Builder();
    }

    @Override
    public Class<? extends GenericVolatilitySurfacePeriodParameterMetadata> beanType() {
      return GenericVolatilitySurfacePeriodParameterMetadata.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code period} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Period> period() {
      return period;
    }

    /**
     * The meta-property for the {@code strike} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Strike> strike() {
      return strike;
    }

    /**
     * The meta-property for the {@code label} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> label() {
      return label;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -991726143:  // period
          return ((GenericVolatilitySurfacePeriodParameterMetadata) bean).getPeriod();
        case -891985998:  // strike
          return ((GenericVolatilitySurfacePeriodParameterMetadata) bean).getStrike();
        case 102727412:  // label
          return ((GenericVolatilitySurfacePeriodParameterMetadata) bean).getLabel();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code GenericVolatilitySurfacePeriodParameterMetadata}.
   */
  private static final class Builder extends DirectPrivateBeanBuilder<GenericVolatilitySurfacePeriodParameterMetadata> {

    private Period period;
    private Strike strike;
    private String label;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -991726143:  // period
          return period;
        case -891985998:  // strike
          return strike;
        case 102727412:  // label
          return label;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -991726143:  // period
          this.period = (Period) newValue;
          break;
        case -891985998:  // strike
          this.strike = (Strike) newValue;
          break;
        case 102727412:  // label
          this.label = (String) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public GenericVolatilitySurfacePeriodParameterMetadata build() {
      preBuild(this);
      return new GenericVolatilitySurfacePeriodParameterMetadata(
          period,
          strike,
          label);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("GenericVolatilitySurfacePeriodParameterMetadata.Builder{");
      buf.append("period").append('=').append(JodaBeanUtils.toString(period)).append(',').append(' ');
      buf.append("strike").append('=').append(JodaBeanUtils.toString(strike)).append(',').append(' ');
      buf.append("label").append('=').append(JodaBeanUtils.toString(label));
      buf.append('}');
      return buf.toString();
    }

  }

  //-------------------------- AUTOGENERATED END --------------------------
}
