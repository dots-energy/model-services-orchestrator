// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lifecycle.proto

package nl.tno.simulation.externalmodel.protos;

public final class Lifecycle {
  private Lifecycle() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EnvironmentVariable_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EnvironmentVariable_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ModelConfiguration_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ModelConfiguration_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DeployModels_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DeployModels_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ReadyForProcessing_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ReadyForProcessing_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ModelsReady_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ModelsReady_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ModelParameters_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ModelParameters_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Parametrized_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Parametrized_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NewStep_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NewStep_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CalculationsDone_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CalculationsDone_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ErrorOccurred_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ErrorOccurred_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SimulationDone_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SimulationDone_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UnhealthyModel_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UnhealthyModel_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ModelHasTerminated_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ModelHasTerminated_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AllModelsHaveTerminated_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AllModelsHaveTerminated_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017lifecycle.proto\"2\n\023EnvironmentVariable" +
      "\022\014\n\004name\030\001 \001(\t\022\r\n\005value\030\002 \001(\t\"k\n\022ModelCo" +
      "nfiguration\022\017\n\007modelID\030\001 \001(\t\022\020\n\010imageUrl" +
      "\030\002 \001(\t\0222\n\024environmentVariables\030\003 \003(\0132\024.E" +
      "nvironmentVariable\"l\n\014DeployModels\022\023\n\013si" +
      "mulatorId\030\001 \001(\t\0220\n\023modelConfigurations\030\002" +
      " \003(\0132\023.ModelConfiguration\022\025\n\rkeepLogsHou" +
      "rs\030\003 \001(\001\"\024\n\022ReadyForProcessing\"\r\n\013Models" +
      "Ready\"*\n\017ModelParameters\022\027\n\017parameters_d" +
      "ict\030\001 \001(\t\"\016\n\014Parametrized\"\"\n\007NewStep\022\027\n\017" +
      "parameters_dict\030\001 \001(\t\"\022\n\020CalculationsDon" +
      "e\"&\n\rErrorOccurred\022\025\n\rerror_message\030\001 \001(" +
      "\t\"\020\n\016SimulationDone\"7\n\016UnhealthyModel\022%\n" +
      "\006status\030\001 \001(\0162\025.UnhealthyModelStatus\"J\n\022" +
      "ModelHasTerminated\022\"\n\006status\030\001 \001(\0162\022.Ter" +
      "minationStatus\022\020\n\010exitCode\030\002 \001(\005\"\031\n\027AllM" +
      "odelsHaveTerminated*&\n\024UnhealthyModelSta" +
      "tus\022\016\n\nNOPROGRESS\020\000*0\n\021TerminationStatus" +
      "\022\017\n\013SUCCESSFULL\020\000\022\n\n\006FAILED\020\001B5\n&nl.tno." +
      "simulation.externalmodel.protosB\tLifecyc" +
      "leP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_EnvironmentVariable_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EnvironmentVariable_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EnvironmentVariable_descriptor,
        new java.lang.String[] { "Name", "Value", });
    internal_static_ModelConfiguration_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ModelConfiguration_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ModelConfiguration_descriptor,
        new java.lang.String[] { "ModelID", "ImageUrl", "EnvironmentVariables", });
    internal_static_DeployModels_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_DeployModels_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeployModels_descriptor,
        new java.lang.String[] { "SimulatorId", "ModelConfigurations", "KeepLogsHours", });
    internal_static_ReadyForProcessing_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_ReadyForProcessing_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ReadyForProcessing_descriptor,
        new java.lang.String[] { });
    internal_static_ModelsReady_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_ModelsReady_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ModelsReady_descriptor,
        new java.lang.String[] { });
    internal_static_ModelParameters_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_ModelParameters_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ModelParameters_descriptor,
        new java.lang.String[] { "ParametersDict", });
    internal_static_Parametrized_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_Parametrized_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Parametrized_descriptor,
        new java.lang.String[] { });
    internal_static_NewStep_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_NewStep_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NewStep_descriptor,
        new java.lang.String[] { "ParametersDict", });
    internal_static_CalculationsDone_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_CalculationsDone_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CalculationsDone_descriptor,
        new java.lang.String[] { });
    internal_static_ErrorOccurred_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_ErrorOccurred_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ErrorOccurred_descriptor,
        new java.lang.String[] { "ErrorMessage", });
    internal_static_SimulationDone_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_SimulationDone_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SimulationDone_descriptor,
        new java.lang.String[] { });
    internal_static_UnhealthyModel_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_UnhealthyModel_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UnhealthyModel_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_ModelHasTerminated_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_ModelHasTerminated_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ModelHasTerminated_descriptor,
        new java.lang.String[] { "Status", "ExitCode", });
    internal_static_AllModelsHaveTerminated_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_AllModelsHaveTerminated_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AllModelsHaveTerminated_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}