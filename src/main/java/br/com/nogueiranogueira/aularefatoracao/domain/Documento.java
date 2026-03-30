package br.com.nogueiranogueira.aularefatoracao.domain;

/**
 * Sealed interface garante exaustividade em tempo de compilação.
 * Apenas Cpf, Curp e Ssn podem implementar Documento.
 * Records garantem imutabilidade e eliminam boilerplate.
 */
public sealed interface Documento permits Documento.Cpf, Documento.Cnpj, Documento.Curp, Documento.Ssn {

    String numero();
    boolean validar();

    // ------- Brasil -------
    record Cpf(String numero) implements Documento {
        // Compact constructor com validação eager
        public Cpf {
            if (numero == null || numero.isBlank())
                throw new IllegalArgumentException("CPF não pode ser vazio");
        }

        @Override
        public boolean validar() {
            String digits = numero.replaceAll("\\D", "");
            return digits.length() == 11 && digits.matches("\\d{11}");
        }
    }

    record Cnpj(String numero) implements Documento {
        public Cnpj {
            if (numero == null || numero.isBlank())
                throw new IllegalArgumentException("CNPJ não pode ser vazio");
        }

        @Override
        public boolean validar() {
            String digits = numero.replaceAll("\\D", "");
            return digits.length() == 14 && digits.matches("\\d{14}");
        }
    }

    // ------- México -------
    record Curp(String numero) implements Documento {
        public Curp {
            if (numero == null || numero.isBlank())
                throw new IllegalArgumentException("CURP não pode ser vazio");
        }

        @Override
        public boolean validar() {
            // CURP mexicano: 18 caracteres alfanuméricos
            return numero != null
                    && numero.length() == 18
                    && numero.matches("[A-Z]{4}\\d{6}[HM][A-Z]{5}[A-Z0-9]\\d");
        }
    }

    // ------- EUA -------
    record Ssn(String numero) implements Documento {
        public Ssn {
            if (numero == null || numero.isBlank())
                throw new IllegalArgumentException("SSN não pode ser vazio");
        }

        @Override
        public boolean validar() {
            String digits = numero.replaceAll("\\D", "");
            return digits.length() == 9 && digits.matches("\\d{9}");
        }
    }
}