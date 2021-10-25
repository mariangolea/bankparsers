package org.mariangolea.fintrack.bank.parser.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractBankTransactionParser {

    private final AbstractBankParserFactory parserFactory;

    public AbstractBankTransactionParser(final AbstractBankParserFactory parserFactory) {
        this.parserFactory = Objects.requireNonNull(parserFactory);
    }

    public ParseResponse parseTransactions(final File file) {
        ParseResponse fileResponse = null;
        if (file == null) {
            return fileResponse;
        }
        List<String> lines = loadFile(file);

        AbstractBankParser parser = parserFactory.getParser(lines);
        if (parser != null) {
            fileResponse = parser.parseResponse(lines, file);
        } else {
            Logger.getLogger(AbstractBankTransactionParser.class.getName()).log(Level.SEVERE, null,
                    new Exception("No parser defined for file: " + file.getAbsolutePath()));
        }

        return fileResponse;
    }

    public List<String> loadFile(final File csvFile) {
        final List<String> response = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFile))) {
            String tempLine;
            while ((tempLine = csvReader.readLine()) != null) {
                response.add(tempLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(AbstractBankTransactionParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
}
