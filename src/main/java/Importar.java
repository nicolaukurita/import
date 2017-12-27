import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Importar {
    public static void main(String[] args) {
        try {

            File f = new File("src/main/resources/eqt.spr_spp_eqt_cal_preco_medio.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            System.out.println("Reading file using Buffered Reader");

            while ((readLine = b.readLine()) != null) {
                if (readLine.trim().equals("")) {

                } else {
                    if (readLine.startsWith("[")) {
                        //[@0,0:6='VERSION',<VERSION>,1:0]
                        String semChaves = retiraChaves(readLine);
                        //procura pela primeira virgula
                        Integer i = semChaves.indexOf(',');
                        Long sequencia = getNumber(semChaves, i);
                        semChaves = semChaves.substring(++i);

                        //procura pelo dois pontos
                        i = semChaves.indexOf(':');
                        Long posInicial = getNumber(semChaves, i);
                        semChaves = semChaves.substring(++i);

                        //procura pelo igual
                        i = semChaves.indexOf('=');
                        Long posFinal = getNumber(semChaves, i);
                        semChaves = semChaves.substring(++i);

                        //procurando pelo dois pontos, pelo fim
                        i = semChaves.lastIndexOf(':') + 1;
                        Long posicao = getNumberByEnd(semChaves, i);
                        semChaves = semChaves.substring(0, --i);

                        //procurando pela virgula pelo fim
                        i = semChaves.lastIndexOf(',') + 1;
                        Long linha = getNumberByEnd(semChaves, i);
                        semChaves = semChaves.substring(0, --i);

                        //procurando pela virgula pelo fim
                        i = semChaves.lastIndexOf(',',semChaves.length()-5) + 1;
                        String tokenType = semChaves.substring(i);
                        semChaves = semChaves.substring(0, --i);

                        semChaves = semChaves.substring(1, semChaves.length() - 1);

                        System.out.println("sequencia: " + sequencia
                                + " posInicial: " + posInicial
                                + " posFinal: " + posFinal
                                + " tokenType: " + tokenType
                                + " linha: " + linha
                                + " posicao: " + posicao
                                + " texto: [" + semChaves + "]"
                                + " readLine: " + readLine

                        );
                    } else {
                        System.out.println("não é token:" + readLine);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Long getNumberByEnd(String semChaves, Integer i) {
        String sequencia = semChaves.substring(i);
        return Long.parseLong(sequencia);
    }

    private static Long getNumber(String semChaves, Integer i) {
        String sequencia = semChaves.substring(0, i);
        return Long.parseLong(sequencia);
    }

    private static String retiraChaves(String readLine) {
        return readLine.substring(2, readLine.length() - 1);
    }
}
