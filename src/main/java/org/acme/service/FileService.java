package org.acme.service;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import org.acme.*;
import org.acme.entity.File;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@GrpcService
public class FileService extends FileServiceGrpc.FileServiceImplBase {

    @Override
    public void downloadFile(FileDownloadRequest request, StreamObserver<FileMetaData> responseObserver) {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            CompletableFuture.supplyAsync(() -> {
                        final Optional<File> fileOptional = File.findByIdOptional(request.getId());

                        return fileOptional
                                .map(file ->
                                        FileMetaData.newBuilder()
                                                .setData(com.google.protobuf.ByteString.copyFrom(file.getData()))
                                                .build())
                                .orElse(null);
                    }, executorService
            ).whenComplete(((fileMetaData, throwable) -> {
                if (throwable != null)
                    responseObserver.onError(
                            io.grpc.Status.INTERNAL
                                    .withDescription("Failed to download file")
                                    .withCause(throwable)
                                    .asRuntimeException()
                    );
                else if (fileMetaData != null) {
                    responseObserver.onNext(fileMetaData);
                    responseObserver.onCompleted();
                }
            }));
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
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
