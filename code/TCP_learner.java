import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import de.learnlib.algorithms.lstar.mealy.ExtensibleLStarMealyBuilder;
import de.learnlib.api.SUL;
import de.learnlib.api.algorithm.LearningAlgorithm.MealyLearner;
import de.learnlib.api.oracle.EquivalenceOracle.MealyEquivalenceOracle;
import de.learnlib.api.statistic.StatisticSUL;
import de.learnlib.drivers.reflect.AbstractMethodOutput;
import de.learnlib.drivers.reflect.MethodInput;
import de.learnlib.drivers.reflect.SimplePOJOTestDriver;
import de.learnlib.filter.cache.sul.SULCaches;
import de.learnlib.filter.statistic.sul.ResetCounterSUL;
import de.learnlib.oracle.equivalence.mealy.RandomWalkEQOracle;
import de.learnlib.oracle.membership.SULOracle;
import de.learnlib.util.Experiment.MealyExperiment;
import de.learnlib.util.statistics.SimpleProfiler;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.serialization.dot.GraphDOT;
import net.automatalib.visualization.Visualization;
import net.automatalib.words.Word;

import static de.learnlib.filter.cache.sul.SULCache.isThread;
import static de.learnlib.oracle.equivalence.mealy.RandomWalkEQOracle.isEq;
import static de.learnlib.oracle.equivalence.mealy.RandomWalkEQOracle.isWait;


public final class TCP_learner {

    private static final double RESET_PROBABILITY = 0.05;
    private static final int MAX_STEPS = 10000;
    private static final int RANDOM_SEED = 46346293;
//    static String HOST = "192.168.1.32"; // IP of the TCP server on the Raspberry Pi 3
    static String HOST = "localhost";
    static int PORT = 12345;
    static Socket socket;
    static PrintWriter out;
    static BufferedReader in;

    private TCP_learner() {
        // prevent instantiation
    }

    public static void main(String[] args) throws NoSuchMethodException, IOException {

        try {
            socket = new Socket(HOST, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        // instantiate test driver
        SimplePOJOTestDriver driver = new SimplePOJOTestDriver(TCP_mapper.class.getConstructor());

        // create learning alphabet and methods
        Method req = TCP_mapper.class.getMethod("mRequest", String.class, boolean.class, boolean.class, int.class);
        MethodInput SYNVV = driver.addInput("SYN(V,V)", req, "SYN", true, true, 0);
        driver.addInput("SYN(I,V)", req, "SYN", false, true, 0);
        driver.addInput("SYN(V,I)", req, "SYN", true, false, 0);
        driver.addInput("SYN(I,I)", req, "SYN", false, false, 0);
        driver.addInput("ACK(V,V)", req, "ACK", true, true, 0);
        driver.addInput("ACK(I,V)", req, "ACK", false, true, 0);
        driver.addInput("ACK(V,I)", req, "ACK", true, false, 0);
        driver.addInput("ACK(I,I)", req, "ACK", false, false, 0);
        driver.addInput("FIN(V,V)", req, "FIN", true, true, 0);
        driver.addInput("FIN(I,V)", req, "FIN", false, true, 0);
        driver.addInput("FIN(V,I)", req, "FIN", true, false, 0);
        driver.addInput("FIN(I,I)", req, "FIN", false, false, 0);
        driver.addInput("MSG(V,V)", req, "MSG", true, true, 30);


        // oracle for counting queries wraps sul
        StatisticSUL<MethodInput, AbstractMethodOutput> statisticSul =
                new ResetCounterSUL<>("membership queries", driver);

        SUL<MethodInput, AbstractMethodOutput> effectiveSul = statisticSul;
        // use caching in order to avoid duplicate queries
        effectiveSul = SULCaches.createCache(driver.getInputs(), effectiveSul);

        SULOracle<MethodInput, AbstractMethodOutput> mqOracle = new SULOracle<>(effectiveSul);

        // create initial set of suffixes
        List<Word<MethodInput>> suffixes = new ArrayList<>();
        suffixes.add(Word.fromSymbols(SYNVV));

        // construct L* instance (almost classic Mealy version)
        // almost: we use words (Word<String>) in cells of the table
        // instead of single outputs.
        MealyLearner<MethodInput, AbstractMethodOutput> lstar =
                new ExtensibleLStarMealyBuilder<MethodInput, AbstractMethodOutput>().withAlphabet(driver.getInputs()) // input alphabet
                        .withOracle(mqOracle) // membership oracle
                        .withInitialSuffixes(suffixes) // initial suffixes
                        .create();

        // create random walks equivalence test
        MealyEquivalenceOracle<MethodInput, AbstractMethodOutput> randomWalks =
                new RandomWalkEQOracle<>(driver, // system under learning
                        RESET_PROBABILITY, // reset SUL w/ this probability before a step
                        MAX_STEPS, // max steps (overall)
                        false, // reset step count after counterexample
                        new Random(RANDOM_SEED) // make results reproducible
                );

        // construct a learning experiment from
        // the learning algorithm and the random walks test.
        // The experiment will execute the main loop of
        // active learning
        MealyExperiment<MethodInput, AbstractMethodOutput> experiment =
                new MealyExperiment<>(lstar, randomWalks, driver.getInputs());

        // turn on time profiling
        experiment.setProfile(true);

        // enable logging of models
        experiment.setLogModels(true);

        // run experiment
        experiment.run();

        socket.close();

        // get learned model
        MealyMachine<?, MethodInput, ?, AbstractMethodOutput> result = experiment.getFinalHypothesis();

        // report results
        System.out.println("-------------------------------------------------------");

        // profiling
        System.out.println(SimpleProfiler.getResults());

        // learning statistics
        System.out.println(experiment.getRounds().getSummary());
        System.out.println(statisticSul.getStatisticalData().getSummary());

        // model statistics
        System.out.println("States: " + result.size());
        System.out.println("Sigma: " + driver.getInputs().size());

        // show model
        System.out.println();
        System.out.println("Model: ");

        GraphDOT.write(result, driver.getInputs(), System.out); // may throw IOException!
        Visualization.visualize(result, driver.getInputs());

        System.out.println("-------------------------------------------------------");
    }

    public static class TCP_mapper {
        int wrongNum;
        int clientSeq;
        int expectedServerSeq;
        String longMessage;

        public TCP_mapper() {
            wrongNum = 20000;
            clientSeq = 100;
            expectedServerSeq = 0;
            longMessage = "";
            out.println("7:reset");
            try {
                in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public synchronized String mRequest(String Flags, boolean SeqAbs, boolean AckAbs, int bytes) {
            int SeqNr, AckNr;
            String request;
            String res = "";
            String[] response;

            SeqNr = SeqAbs? clientSeq : wrongNum;
            AckNr = AckAbs? expectedServerSeq : wrongNum;

            if (isEq()) {
                if (isWait()) {
                    request = "e" + Flags + "/" + String.valueOf(SeqNr) + "/" + String.valueOf(AckNr) + "/" + String.valueOf(bytes);
                    request = String.valueOf(request.length()) + ":" + request;
                    longMessage += request;
                    return "";
                } else {
                    request = "f" + Flags + "/" + String.valueOf(SeqNr) + "/" + String.valueOf(AckNr) + "/" + String.valueOf(bytes);
                    request = String.valueOf(request.length()+2) + ":" + request;
                    longMessage += request;
                    out.println(longMessage);
                    longMessage = "";

                    try {
                        res = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String[] temp = res.split("-");
                    StringBuilder str = new StringBuilder();
                    for (String x: temp) {
                        if (x.equals("X")) {
                            str.append(x);
                        } else {
                            str.append(x.split("/")[0]);
                        }
                        str.append("-");
                    }
                    str.setLength(str.length() - 1);
                    return str.toString();
                }
            } else {
                request = (isThread()) ? "t":"m";
                request += Flags + "/" + String.valueOf(SeqNr) + "/" + String.valueOf(AckNr) + "/" + String.valueOf(bytes);

                request = String.valueOf(request.length()+2) + ":" + request;
                out.println(request);

                if (isThread()) {
                    return "";
                }

                try {
                    res = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (res.equals("") || res.equals("X"))
                    return "";

                response = res.split("/");
                String responseFlag = response[0];
                int responseSeq = Integer.valueOf(response[1]);
                int responseAck = Integer.valueOf(response[2]);

                clientSeq = responseAck;
                expectedServerSeq = responseSeq + 1;

                return responseFlag;
            }
        }
    }
}
