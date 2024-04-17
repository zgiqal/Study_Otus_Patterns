package org.common;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayArgumentProvider implements ArgumentsProvider, AnnotationConsumer<ArraySources> {
    private Map<double[], double[]> arguments;

    public void accept(ArraySources source) {
        List<ArraySource> arrays = Arrays.asList(source.arrays());

        this.arguments = arrays.stream()
                .collect(Collectors.toMap(ArraySource::inputs, ArraySource::outputs));
    }

    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return arguments.entrySet().stream().map(entry -> Arguments.of(entry.getKey(), entry.getValue()));
    }
}
