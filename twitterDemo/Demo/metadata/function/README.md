The json type file for the core udfs (trunk/Pmml/PmmlUdfs) generated by the method extractor scripts is found here.
The following command was used to do this (from the trunk):

developer@pepper:~/github/Med/Fatafat/trunk$ extractUdfLibMetadata.scala  --sbtProject PmmlUdfs --fullObjectPath com.ligadata.pmml.udfs.Udfs --namespace pmml --versionNumber 100 --typeDefsPath ~/github/Med/Fatafat/trunk/SampleApplication/Medical/Types/coreUdfTypeDefs.json --fcnDefsPath ~/github/Med/Fatafat/trunk/SampleApplication/Medical/Functions/coreUdfFcnDefs.json

