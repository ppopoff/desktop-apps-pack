package com.doingfp.desktop.texted;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileIO {

    public enum WriteStatus {
        SUCCEED, FAILED;

        public static WriteStatus failed(final String message) {
            return FAILED.withMessage(message);
        }

        public static WriteStatus succeed() {
            return SUCCEED.withMessage("Ok");
        }

        public WriteStatus withMessage(final String message) {
            this.message = Optional.of(message);
            return this;
        }

        public Optional<String> getMessage() {
            return message;
        }

        private Optional<String> message = Optional.empty();
    }


    public enum ReadStatus {
        SUCCEED, FAILED;

        public static ReadStatus failed(final String message) {
            return FAILED.withMessage(message);
        }

        public static ReadStatus succeed(final String text) {
            return SUCCEED.withMessage("Ok").withText(text);
        }

        public ReadStatus withMessage(final String message) {
            this.message = Optional.ofNullable(message);
            return this;
        }

        public ReadStatus withText(final String text) {
            this.text = Optional.ofNullable(text);
            return this;
        }

        public Optional<String> getMessage() {
            return message;
        }

        public Optional<String> getText() {
            return text;
        }

        private Optional<String> text = Optional.empty();
        private Optional<String> message = Optional.empty();
    }


    public static WriteStatus
    save(final String txt, final Path file) {
        return save(txt, Charset.forName("UTF-8"), file);
    }


    public static WriteStatus
    save(final String txt, final Charset cs, final Path file) {
        try {
            Files.write(file, txt.getBytes(cs));
            return WriteStatus.succeed();

        } catch (final IOException ioe) {
            // todo: add appropriate logging there
            System.out.println("Unable to write file");
            return WriteStatus.failed(ioe.getMessage());
        }
    }

    public static ReadStatus
    read(final Path file, final Charset cs) {
        try {
            final List<String> lines = Files.readAllLines(file, cs);
            return ReadStatus.succeed(
                lines.stream().collect(Collectors.joining("\n")));

        } catch (final IOException ioe) {
            // todo: add some logging there
            return ReadStatus.failed(ioe.getMessage());
        }
    }


    // Acts As singleton
    private FileIO() {
    }
}
