import daikon.*;
import daikon.inv.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// compile with: javac -cp $DAIKONDIR/daikon.jar DaikonCleanDiff.java
// run with: java -cp .:$DAIKONDIR/daikon.jar DaikonCleanDiff daikon-pass.inv.gz daikon-fail.inv.gz
// or both at the same time: javac -cp $DAIKONDIR/daikon.jar DaikonCleanDiff.java && java -cp .:$DAIKONDIR/daikon.jar DaikonCleanDiff daikon-pass.inv.gz daikon-fail.inv.gz
public class DaikonCleanDiff {
    private static String RESET = "";
    private static String RED = "";
    private static String YELLOW = "";
    private static String GREEN = "";
    private static String BLUE = "";
    private static String CYAN = "";

    // to allow for redirection to a file
    static {
        if (System.console() != null) {
            RESET = "\u001B[0m";
            RED = "\u001B[31m";
            YELLOW = "\u001B[33m";
            GREEN = "\u001B[32m";
            BLUE = "\u001B[34m";
            CYAN = "\u001B[36m";
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: DaikonCleanDiff daikon-pass.inv.gz daikon-fail.inv.gz (--debug)");
            System.exit(1);
        }

        PptMap pass = getPptMap(new File(args[0]));
        PptMap fail = getPptMap(new File(args[1]));
        boolean debug = args.length > 2 && args[2].equals("--debug");

        if (debug) {
            String debugPrelude = RED + "!!! DEBUG: " + RESET;
            System.out.println(debugPrelude + "Passing PptMap");
            debugPrintPptMap(pass);
            System.out.println("\n\n\n");
            System.out.println(debugPrelude + "Failing PptMap");
            debugPrintPptMap(fail);
            System.out.println("\n\n\n");
        }

        cleanDiff(pass, fail);
    }

    private static PptMap getPptMap(File file) {
        try {
            return FileIO.read_serialized_pptmap(file, true);
        } catch (Throwable e) {
            System.err.println("Error reading serialized pptmap");
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    private static void debugPrintPptMap(PptMap pptMap) {
        for (PptTopLevel pptTopLevel : pptMap.pptIterable()) {
            List<Invariant> invariants = pptTopLevel.getInvariants().stream()
                    .filter((invariant) -> invariant.isWorthPrinting()).collect(Collectors.toList());

            if (invariants.size() == 0)
                continue;
            System.out.println(
                    BLUE + "===========================================================================" + RESET);
            System.out.println(YELLOW + pptTopLevel.name() + RESET + "  \t" + CYAN + "vars: "
                    + pptTopLevel.var_names() + RESET);
            for (Invariant invariant : invariants) {
                System.out.println(invariant);
            }
        }
    }

    private static void cleanDiff(PptMap pass, PptMap fail) {
        for (PptTopLevel passPptTopLevel : pass.pptIterable()) {
            if (passPptTopLevel.num_samples() == 0)
                continue;
            PptTopLevel failPptTopLevel = fail.get(passPptTopLevel.ppt_name);
            if (failPptTopLevel == null || failPptTopLevel.num_samples() == 0)
                continue;

            List<String> passInvariants = getStringifiedInvariantsOf(passPptTopLevel);
            List<String> failInvariants = getStringifiedInvariantsOf(failPptTopLevel);

            boolean differentInvariantsExist = false;
            ArrayList<String> invariantsInPassButNotInFail = new ArrayList<>();
            ArrayList<String> invariantsInFailButNotInPass = new ArrayList<>();

            for (String passInvariant : passInvariants) {
                if (!failInvariants.contains(passInvariant)) {
                    if (!differentInvariantsExist)
                        differentInvariantsExist = true;

                    invariantsInPassButNotInFail.add(passInvariant);
                }
            }

            for (String failInvariant : failInvariants) {
                if (!passInvariants.contains(failInvariant)) {
                    if (!differentInvariantsExist)
                        differentInvariantsExist = true;

                    invariantsInFailButNotInPass.add(failInvariant);
                }
            }

            if (differentInvariantsExist) {
                System.out.println(
                        BLUE + "===========================================================================" + RESET);
                System.out.println(YELLOW + passPptTopLevel.name() + RESET);

                for (String invariant : invariantsInPassButNotInFail) {
                    System.out.println(GREEN + "p> " + RESET + invariant + " " + GREEN + "(pass only)" + RESET);
                }
                for (String invariant : invariantsInFailButNotInPass) {
                    System.out.println(RED + "f> " + RESET + invariant + " " + RED + "(fail only)" + RESET);
                }
            }
        }
    }

    private static List<String> getStringifiedInvariantsOf(PptTopLevel pptTopLevel) {
        return pptTopLevel.getInvariants().stream()
                .filter((invariant) -> invariant.isWorthPrinting()).map((invariant) -> invariant.toString()).sorted()
                .collect(Collectors.toList());
    }
}
