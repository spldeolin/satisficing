package com.spldeolin.satisficing.app.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.apache.commons.lang3.mutable.MutableObject;
import com.google.common.collect.Lists;
import cn.idev.excel.ExcelWriter;
import cn.idev.excel.FastExcel;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import cn.idev.excel.read.listener.PageReadListener;

/**
 * @author Deolin 2025-10-01
 */
public class ExcelUtils {

    private static final int defaultBatchCount = 500;

    /**
     * 分批读取EXCEL
     */
    public static <T> void readInBatches(InputStream excel, Consumer<List<T>> rowsConsumer, Class<T> rowType) {
        readInBatches(excel, rowsConsumer, rowType, defaultBatchCount);
    }

    /**
     * 分批读取EXCEL
     */
    public static <T> void readInBatches(InputStream excel, Consumer<List<T>> rowsConsumer, Class<T> rowType,
            int batchCount) {
        FastExcel.read(excel, rowType, new PageReadListener<>(rowsConsumer, batchCount)).sheet().doRead();
    }

    /**
     * 一次读取EXCEL所有记录，只适用于数据量较小的场景
     */
    public static <T> List<T> readAll(InputStream excel, Class<T> rowType) {
        return FastExcel.read(excel).head(rowType).sheet().doReadSync();
    }

    /**
     * 分批读取EXCEL
     */
    public static void readInBatches(InputStream excel, Consumer<List<Map<Integer, String>>> rowsConsumer) {
        readInBatches(excel, rowsConsumer, defaultBatchCount);
    }

    /**
     * 分批读取EXCEL
     */
    public static void readInBatches(InputStream excel, Consumer<List<Map<Integer, String>>> rowsConsumer,
            int batchCount) {
        final MutableObject<List<Map<Integer, String>>> cachedRows = new MutableObject<>(
                Lists.newArrayListWithExpectedSize(batchCount));

        FastExcel.read(excel, new AnalysisEventListener<Map<Integer, String>>() {

            @Override
            public void invoke(Map<Integer, String> row, AnalysisContext analysisContext) {
                cachedRows.getValue().add(row);
                if (cachedRows.getValue().size() >= batchCount) {
                    rowsConsumer.accept(cachedRows.getValue());
                    cachedRows.setValue(Lists.newArrayListWithExpectedSize(batchCount));
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("所有数据读取完成！");
            }
        }).sheet().doRead();
    }

    /**
     * 一次读取Excel所有行，只适用于数据量较小的场景
     */
    public static List<Map<Integer, String>> readAll(InputStream excel) {
        return FastExcel.read(excel).sheet().doReadSync();
    }

    /**
     * 分批写入同一Excel
     */
    public static <T> void writeInBatches(File excel, RowsSupplier<T> rowsSupplier, Class<T> rowType) {
        try (ExcelWriter excelWriter = FastExcel.write(excel, rowType).build()) {
            while (!rowsSupplier.over()) {
                excelWriter.write(rowsSupplier.get(), FastExcel.writerSheet().build());
            }
        }
    }

    /**
     * 所有数据一次写入Excel，只适用于数据量较小的场景
     */
    public static <T> void writeAll(File excel, List<T> rows, Class<T> rowType) {
        FastExcel.write(excel, rowType).sheet().doWrite(rows);
    }

    public static void writeInBatches(File excel, List<String> heads, RowsSupplier<List<String>> rowsSupplier) {
        List<List<String>> head = heads.stream().map(Lists::newArrayList).collect(Collectors.toList());
        try (ExcelWriter excelWriter = FastExcel.write(excel).head(head).build()) {
            while (!rowsSupplier.over()) {
                excelWriter.write(rowsSupplier.get(), FastExcel.writerSheet().build());
            }
        }
    }

    public interface RowsSupplier<T> {

        List<T> get();

        boolean over();

    }

}
