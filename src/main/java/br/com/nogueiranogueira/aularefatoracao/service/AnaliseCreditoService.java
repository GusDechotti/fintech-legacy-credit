package br.com.nogueiranogueira.aularefatoracao.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnaliseCreditoService {

    public boolean analisarSolicitacao(String cliente, double valor, int score, boolean negativado, String tipoConta) {
        System.out.println("Iniciando análise para: " + cliente);
        if (valor > 0) {
            if (!negativado) {
                if (score > 500) {
                    // boas práticas, consulta externas eu utilizo um try catch para tratar exceções que possam ocorrer na comunicação
                    try {
                        System.out.println("Consultando Bureau de Crédito Externo...");
                        Thread.sleep(2000); // Simula 2 segundos de espera da consulta de crédito externo a instituição
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (tipoConta.equals("PF")) {
                        if (valor > 5000 && score < 800) {
                            System.out.println("Reprovado: Valor alto para PF com score médio");
                            return false;
                        } else {
                            if (new Date().getDay() == 0 || new Date().getDay() == 6) { // Fim de semana? (Uso de Date depreciado)
                                System.out.println("Aprovação manual necessária no fim de semana");
                                return false;
                            }
                            System.out.println("Aprovado PF");
                            return true;
                        }
                    } else if (tipoConta.equals("PJ")) {
                        if (valor > 50000 && score < 700) {
                            System.out.println("Reprovado: Risco PJ");
                            return false;
                        }
                        System.out.println("Aprovado PJ");
                        return true;
                    } else {
                        System.out.println("Tipo de conta desconhecido");
                        return false;
                    }

                } else {
                    System.out.println("Score baixo");
                    return false;
                }
            } else {
                System.out.println("Cliente negativado");
                return false;
            }
        } else {
            System.out.println("Valor inválido");
            return false;
        }
    }

    public void processarLote(List<String> clientes) {
        for (String c : clientes) {
            analisarSolicitacao(c, 1000.0, 600, false, "PF");
        }
    }
}