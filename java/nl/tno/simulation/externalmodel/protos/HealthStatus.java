// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: healthcheck.proto

package nl.tno.simulation.externalmodel.protos;

/**
 * Protobuf enum {@code HealthStatus}
 */
public enum HealthStatus
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>HEALTHY = 0;</code>
   */
  HEALTHY(0),
  /**
   * <code>UNHEALTHY = 1;</code>
   */
  UNHEALTHY(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>HEALTHY = 0;</code>
   */
  public static final int HEALTHY_VALUE = 0;
  /**
   * <code>UNHEALTHY = 1;</code>
   */
  public static final int UNHEALTHY_VALUE = 1;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static HealthStatus valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static HealthStatus forNumber(int value) {
    switch (value) {
      case 0: return HEALTHY;
      case 1: return UNHEALTHY;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<HealthStatus>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      HealthStatus> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<HealthStatus>() {
          public HealthStatus findValueByNumber(int number) {
            return HealthStatus.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return nl.tno.simulation.externalmodel.protos.Healthcheck.getDescriptor().getEnumTypes().get(0);
  }

  private static final HealthStatus[] VALUES = values();

  public static HealthStatus valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private HealthStatus(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:HealthStatus)
}

