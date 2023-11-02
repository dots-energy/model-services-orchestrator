// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lifecycle.proto

package nl.tno.simulation.externalmodel.protos;

/**
 * Protobuf type {@code ModelParameters}
 */
public final class ModelParameters extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ModelParameters)
    ModelParametersOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ModelParameters.newBuilder() to construct.
  private ModelParameters(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ModelParameters() {
    parametersDict_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ModelParameters();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ModelParameters(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            parametersDict_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return nl.tno.simulation.externalmodel.protos.Lifecycle.internal_static_ModelParameters_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return nl.tno.simulation.externalmodel.protos.Lifecycle.internal_static_ModelParameters_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            nl.tno.simulation.externalmodel.protos.ModelParameters.class, nl.tno.simulation.externalmodel.protos.ModelParameters.Builder.class);
  }

  public static final int PARAMETERS_DICT_FIELD_NUMBER = 1;
  private volatile java.lang.Object parametersDict_;
  /**
   * <code>string parameters_dict = 1;</code>
   * @return The parametersDict.
   */
  @java.lang.Override
  public java.lang.String getParametersDict() {
    java.lang.Object ref = parametersDict_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      parametersDict_ = s;
      return s;
    }
  }
  /**
   * <code>string parameters_dict = 1;</code>
   * @return The bytes for parametersDict.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getParametersDictBytes() {
    java.lang.Object ref = parametersDict_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      parametersDict_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(parametersDict_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, parametersDict_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(parametersDict_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, parametersDict_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof nl.tno.simulation.externalmodel.protos.ModelParameters)) {
      return super.equals(obj);
    }
    nl.tno.simulation.externalmodel.protos.ModelParameters other = (nl.tno.simulation.externalmodel.protos.ModelParameters) obj;

    if (!getParametersDict()
        .equals(other.getParametersDict())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PARAMETERS_DICT_FIELD_NUMBER;
    hash = (53 * hash) + getParametersDict().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static nl.tno.simulation.externalmodel.protos.ModelParameters parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(nl.tno.simulation.externalmodel.protos.ModelParameters prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ModelParameters}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ModelParameters)
      nl.tno.simulation.externalmodel.protos.ModelParametersOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return nl.tno.simulation.externalmodel.protos.Lifecycle.internal_static_ModelParameters_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return nl.tno.simulation.externalmodel.protos.Lifecycle.internal_static_ModelParameters_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              nl.tno.simulation.externalmodel.protos.ModelParameters.class, nl.tno.simulation.externalmodel.protos.ModelParameters.Builder.class);
    }

    // Construct using nl.tno.simulation.externalmodel.protos.ModelParameters.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      parametersDict_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return nl.tno.simulation.externalmodel.protos.Lifecycle.internal_static_ModelParameters_descriptor;
    }

    @java.lang.Override
    public nl.tno.simulation.externalmodel.protos.ModelParameters getDefaultInstanceForType() {
      return nl.tno.simulation.externalmodel.protos.ModelParameters.getDefaultInstance();
    }

    @java.lang.Override
    public nl.tno.simulation.externalmodel.protos.ModelParameters build() {
      nl.tno.simulation.externalmodel.protos.ModelParameters result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public nl.tno.simulation.externalmodel.protos.ModelParameters buildPartial() {
      nl.tno.simulation.externalmodel.protos.ModelParameters result = new nl.tno.simulation.externalmodel.protos.ModelParameters(this);
      result.parametersDict_ = parametersDict_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof nl.tno.simulation.externalmodel.protos.ModelParameters) {
        return mergeFrom((nl.tno.simulation.externalmodel.protos.ModelParameters)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(nl.tno.simulation.externalmodel.protos.ModelParameters other) {
      if (other == nl.tno.simulation.externalmodel.protos.ModelParameters.getDefaultInstance()) return this;
      if (!other.getParametersDict().isEmpty()) {
        parametersDict_ = other.parametersDict_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      nl.tno.simulation.externalmodel.protos.ModelParameters parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (nl.tno.simulation.externalmodel.protos.ModelParameters) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object parametersDict_ = "";
    /**
     * <code>string parameters_dict = 1;</code>
     * @return The parametersDict.
     */
    public java.lang.String getParametersDict() {
      java.lang.Object ref = parametersDict_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        parametersDict_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string parameters_dict = 1;</code>
     * @return The bytes for parametersDict.
     */
    public com.google.protobuf.ByteString
        getParametersDictBytes() {
      java.lang.Object ref = parametersDict_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        parametersDict_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string parameters_dict = 1;</code>
     * @param value The parametersDict to set.
     * @return This builder for chaining.
     */
    public Builder setParametersDict(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      parametersDict_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string parameters_dict = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearParametersDict() {
      
      parametersDict_ = getDefaultInstance().getParametersDict();
      onChanged();
      return this;
    }
    /**
     * <code>string parameters_dict = 1;</code>
     * @param value The bytes for parametersDict to set.
     * @return This builder for chaining.
     */
    public Builder setParametersDictBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      parametersDict_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:ModelParameters)
  }

  // @@protoc_insertion_point(class_scope:ModelParameters)
  private static final nl.tno.simulation.externalmodel.protos.ModelParameters DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new nl.tno.simulation.externalmodel.protos.ModelParameters();
  }

  public static nl.tno.simulation.externalmodel.protos.ModelParameters getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ModelParameters>
      PARSER = new com.google.protobuf.AbstractParser<ModelParameters>() {
    @java.lang.Override
    public ModelParameters parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ModelParameters(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ModelParameters> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ModelParameters> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public nl.tno.simulation.externalmodel.protos.ModelParameters getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

