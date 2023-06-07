//package com.example.lostbox;
//
//import android.content.Context;
//import android.content.res.AssetFileDescriptor;
//import android.content.res.AssetManager;
//import org.tensorflow.lite.Interpreter;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//
//public class ClassificationImage {
//    private Interpreter interpreter;
//
//    public ClassificationImage(Context context) throws IOException {
//        interpreter = new Interpreter(loadModelFile(context));
//    }
//
//    private MappedByteBuffer loadModelFile(Context context) throws IOException {
//        AssetManager assetManager = context.getAssets();
//        AssetFileDescriptor fileDescriptor = assetManager.openFd("my_model.tflite");
//        FileInputStream fileInputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
//        FileChannel fileChannel = fileInputStream.getChannel();
//        long startOffset = fileDescriptor.getStartOffset();
//        long declaredLength = fileDescriptor.getDeclaredLength();
//        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
//    }
//
//    public float[] classify(float[] input) {
//        float[] output = new float[224];
//        ByteBuffer inputBuffer = ByteBuffer.allocateDirect(224 * Float.SIZE / Byte.SIZE);
//        inputBuffer.order(ByteOrder.nativeOrder());
//        inputBuffer.asFloatBuffer().put(input);
//        interpreter.run(inputBuffer, output);
//        return output;
//    }
//
//    // 사용 예시
//    public static void main(String[] args) {
//        try {
//            ClassificationImage classifier = new ClassificationImage(context);
//
//            // 입력 데이터 준비
//            float[] input =
//            float[] output = classifier.classify(input);
//
//            // 결과 처리
//            processOutput(output);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}