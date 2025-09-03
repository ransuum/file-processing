package org.acme.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import org.acme.*;

@GrpcService
public class FileService extends FileServiceGrpc.FileServiceImplBase {


    @Override
    public void downloadFile(FileDownloadRequest request, StreamObserver<FileMetaData> responseObserver) {
        super.downloadFile(request, responseObserver);
    }

    @Override
    public void uploadFile(FileUploadRequest request, StreamObserver<Empty> responseObserver) {
        super.uploadFile(request, responseObserver);
    }

    @Override
    public void deleteFile(FileDeleteRequest request, StreamObserver<Empty> responseObserver) {
        super.deleteFile(request, responseObserver);
    }

    @Override
    public void updateFile(FileUpdateRequest request, StreamObserver<Empty> responseObserver) {
        super.updateFile(request, responseObserver);
    }

    @Override
    public void getFiles(Empty request, StreamObserver<FilesList> responseObserver) {
        super.getFiles(request, responseObserver);
    }
}
